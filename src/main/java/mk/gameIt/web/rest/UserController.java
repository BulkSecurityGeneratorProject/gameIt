package mk.gameIt.web.rest;

import mk.gameIt.domain.Game;
import mk.gameIt.domain.User;
import mk.gameIt.web.dto.UserObject;
import org.apache.catalina.connector.Response;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import mk.gameIt.service.UserService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
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
        User user = userService.findOne(id);
        if (user == null) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<?> createNewUser(@RequestBody UserObject userObject) throws IOException, SQLException {
        User user = userService.createNewUser(userObject);
        final URI location = ServletUriComponentsBuilder.
                fromCurrentServletMapping().path("/users/{id}").build()
                .expand(user.getUserId()).toUri();
        return ResponseEntity.created(location).body(user);
    }

    @RequestMapping(value = "/users/{username}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@PathVariable String username, @RequestBody UserObject userObject) {
        User user = userService.updateUser(username, userObject);
        return ResponseEntity.ok(user);
    }

    @RequestMapping(value = "/users/{id}/numcomments", method = RequestMethod.GET)
    public ResponseEntity<?> numberOfCommentsFromUser(@PathVariable Long id) {
        User user = userService.findOne(id);
        if (user != null) {
            Integer numberOfComments = user.getCommentsGame().size();
            return ResponseEntity.ok(numberOfComments);
        }
        return ResponseEntity.notFound().build();
    }



    @RequestMapping(value = "/account/upload", method = RequestMethod.POST)
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) {
        byte[] bytes;
        if (!file.isEmpty()) {
            try {
                userService.updateUserImage(file);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (IOException e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            } catch (SQLException e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
