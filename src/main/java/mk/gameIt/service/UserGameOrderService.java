package mk.gameIt.service;

import com.stripe.model.Charge;
import mk.gameIt.domain.Game;
import mk.gameIt.domain.UserGameOrder;
import mk.gameIt.web.dto.CreditCardObject;

/**
 * Created by Stefan on 30.8.2016.
 */
public interface UserGameOrderService {
    Charge placeOrder(String creditCardObject, Game game);
}
