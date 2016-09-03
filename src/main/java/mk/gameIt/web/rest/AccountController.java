package mk.gameIt.web.rest;

import com.github.mkopylec.recaptcha.RecaptchaProperties;
import com.github.mkopylec.recaptcha.validation.RecaptchaValidator;
import com.github.mkopylec.recaptcha.validation.ValidationResult;
import mk.gameIt.domain.Role;
import mk.gameIt.domain.User;
import mk.gameIt.repository.UserRepository;
import mk.gameIt.service.UserService;
import mk.gameIt.web.dto.ExceptionObject;
import mk.gameIt.web.dto.UserObject;
import mk.gameIt.web.exceptions.EmailExistsException;
import mk.gameIt.web.exceptions.UsernameExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.security.Principal;
import java.sql.SQLException;

/**
 * Created by Stefan on 26.03.2016.
 */
@RestController
@RequestMapping(value = "/api")
public class AccountController {
    private final Logger log = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private RecaptchaValidator recaptchaValidator;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity createNewAccount(@RequestBody UserObject userObject) {
        User newUser = null;
        try {
            newUser = userService.createNewUser(userObject);
            return new ResponseEntity<User>(HttpStatus.CREATED).created(URI.create("api/user/" + newUser.getUserId())).body(newUser);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
          //  e.printStackTrace();
            System.out.println(e.getMessage());
        } catch (UsernameExistsException e) {
            return ResponseEntity.badRequest().body(new ExceptionObject(e.getMessage()));
        } catch (EmailExistsException e) {
          return ResponseEntity.badRequest().body(new ExceptionObject(e.getMessage()));

        }
        System.out.println(userObject);
        return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping("/me")
    public ResponseEntity<UserObject> getLoggedInUser() throws SQLException, UnsupportedEncodingException {
        User user = userService.currentLoggedInUser();
        if (user != null) {
            String base64Encoded = null;
            if (user.getProfileImage() != null) {
                byte[] encodeBase64 = Base64.encode(user.getProfileImage().getBytes(1, (int) user.getProfileImage().length()));
                base64Encoded = new String(encodeBase64, "UTF-8");
            }
            UserObject userObject = new UserObject(user.getUsername(), null, user.getUsername(), user.getLastName(), user.getEmail(), base64Encoded, user.getRole());
            return new ResponseEntity<User>(HttpStatus.OK).ok(userObject);
        }

        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    @RequestMapping("/authenticate")
    public Principal user(Principal user) {
            return user;
    }

    @RequestMapping(value = "/lang", method = RequestMethod.POST)
    public ResponseEntity changeLanguage(@RequestBody String lang) {
        User currentUser =  userService.currentLoggedInUser();
        if (currentUser != null){
            userService.changeLangKey(lang, currentUser.getUsername());
            return new ResponseEntity(HttpStatus.OK).ok().build();
        }

        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    @RequestMapping(value = "/lang", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getLanguage() {
        User currentUser = userService.currentLoggedInUser();
        if (currentUser != null) {
            return "{\"langKey\": " + "\"" + currentUser.getLangKey() + "\"" + '}';
        }

        return null;
    }
}
