package mk.gameIt.service.impl;

import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.Charge;
import mk.gameIt.domain.Game;
import mk.gameIt.domain.User;
import mk.gameIt.domain.UserGameOrder;
import mk.gameIt.repository.UserGameOrderRepository;
import mk.gameIt.service.MailSender;
import mk.gameIt.service.UserGameOrderService;
import mk.gameIt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Stefan on 30.8.2016.
 */
@Service
public class UserGameOrderServiceImpl implements UserGameOrderService {
    @Autowired
    private UserGameOrderRepository userGameOrderRepository;
    @Autowired
    private MailSender mailSender;

    @Autowired
    private UserService userService;

    public static final String STRIPE_KEY_PREFIX = "stripe.";
    public static final String TEST_PRIVATE_KEY = "testpk";

    @Autowired
    Environment environment;

    private RelaxedPropertyResolver stripeConfig;

    public void initPropertyResolver() {
        stripeConfig = new RelaxedPropertyResolver(environment, STRIPE_KEY_PREFIX);
    }

    @Override
    public Charge placeOrder(String creditCardObject, Game game) {
        initPropertyResolver();
        // Set your secret key: remember to change this to your live secret key in production
        // See your keys here: https://dashboard.stripe.com/account/apikeys
        Stripe.apiKey = stripeConfig.getProperty(TEST_PRIVATE_KEY);

        // Get the credit card details submitted by the form
        User buyer = userService.currentLoggedInUser();

        Double price = game.getGamePrice()*100;
        // Create a charge: this will charge the user's card
        try {
            Map<String, Object> chargeParams = new HashMap<String, Object>();
            chargeParams.put("amount", price.intValue()); // Amount in cents
            chargeParams.put("currency", "usd");
                chargeParams.put("source", creditCardObject);
            chargeParams.put("description", "Charge for game");

            Charge charge = Charge.create(chargeParams);

            UserGameOrder userGameOrder = new UserGameOrder();
            userGameOrder.setUser(buyer);
            userGameOrder.setGame(game);
            userGameOrder.setStripeOrderId(charge.getId());

            userGameOrderRepository.save(userGameOrder);


            return charge;
        } catch (CardException e) {
            // The card has been declined
        } catch (APIException e) {
            e.printStackTrace();
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        return null;
    }

}
