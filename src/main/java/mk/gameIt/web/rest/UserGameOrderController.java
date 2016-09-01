package mk.gameIt.web.rest;

import com.stripe.model.Charge;
import mk.gameIt.domain.Game;
import mk.gameIt.domain.UserGameOrder;
import mk.gameIt.service.GameService;
import mk.gameIt.service.UserGameOrderService;
import mk.gameIt.web.dto.CreditCardObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Stefan on 31.8.2016.
 */
@RequestMapping("/api")
@RestController
public class UserGameOrderController {
    @Autowired
    private UserGameOrderService userGameOrderService;

    @Autowired
    private GameService gameService;

    @RequestMapping(value = "/games/{id}/order", method = RequestMethod.POST)
    public ResponseEntity makePayment(@PathVariable Long id, @RequestBody CreditCardObject creditCardObject) {
      try {
          String token = creditCardObject.getToken();
          Game game = gameService.findOne(id);
          Charge charge = userGameOrderService.placeOrder(token, game);
          return new ResponseEntity(HttpStatus.OK).ok(charge);
      } catch(Exception e) {
          return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
}
