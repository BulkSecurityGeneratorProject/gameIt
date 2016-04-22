package mk.gameIt.web.rest;

import mk.gameIt.domain.User;
import mk.gameIt.repository.UserRepository;
import mk.gameIt.service.UserService;
import mk.gameIt.web.dto.UserObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public void createNewAccount(@RequestBody UserObject userObject) {
//styytyhtyh
    }

    @RequestMapping("/authenticate")
    public Principal user(Principal user) {
        return user;
    }

    @RequestMapping(value="/lang",method = RequestMethod.POST)
    public ResponseEntity changeLanguage(@RequestBody String lang){
        String username = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.isAuthenticated()){
            Object principal = authentication.getPrincipal();
            if(principal instanceof UserDetails){
                username = ((UserDetails)principal).getUsername();
            }
            else{
                username = principal.toString();
            }
            userService.changeLangKey(lang, username);
            return new ResponseEntity(HttpStatus.OK).ok().build();
        }
        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }
}
