package mk.gameIt.web.rest;

import mk.gameIt.domain.User;
import mk.gameIt.web.dto.UserObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import mk.gameIt.service.UserService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Stefan on 24.03.2016.
 */
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<?> getUsers() {
        List<User> users = userService.findAll();
        ResponseEntity<?> responseEntity;
        if (users.size() > 0) {
            responseEntity = ResponseEntity.ok(users);
        } else {
            responseEntity = ResponseEntity.noContent().build();
        }
        return responseEntity;
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getOneUser(@PathVariable Long id) {
        User user = userService.findOne(id);
        ResponseEntity<?> responseEntity;
        if (user != null) {
            responseEntity = ResponseEntity.ok(user);
        } else {
            responseEntity = ResponseEntity.noContent().build();
        }
        return responseEntity;
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteOneUser(@PathVariable Long id) {
        userService.deleteOne(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<?> createNewUser(@RequestBody UserObject userObject) throws IOException, SQLException {
        User user = userService.createNewUser(userObject);
        final URI location = ServletUriComponentsBuilder.
                fromCurrentServletMapping().path("/users/{id}").build()
                .expand(user.getUserId()).toUri();
        return ResponseEntity.created(location).body(user);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserObject userObject) {
        User user = userService.updateUser(id, userObject);
        return ResponseEntity.ok(user);
    }
}
