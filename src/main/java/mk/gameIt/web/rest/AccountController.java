package mk.gameIt.web.rest;

import mk.gameIt.web.dto.UserObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Stefan on 26.03.2016.
 */
@RestController
@RequestMapping(value = "/api",produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {


    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public void createNewAccount(@RequestBody UserObject userObject){

    }
}
