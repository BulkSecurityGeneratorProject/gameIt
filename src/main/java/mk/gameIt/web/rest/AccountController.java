package mk.gameIt.web.rest;

import mk.gameIt.domain.User;
import mk.gameIt.repository.UserRepository;
import mk.gameIt.web.dto.UserObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by Stefan on 26.03.2016.
 */
@RestController
@RequestMapping(value = "/api")
public class AccountController {
    private final Logger log = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public void createNewAccount(@RequestBody UserObject userObject) {
//styytyhtyh
    }

    @RequestMapping("/authenticate")
    public Principal user(Principal user) {
        return user;
    }
}
