package mk.gameIt.web.rest;

import mk.gameIt.domain.Location;
import mk.gameIt.domain.User;
import mk.gameIt.service.LocationService;
import mk.gameIt.service.UserService;
import mk.gameIt.web.dto.LocationObject;
import mk.gameIt.web.dto.UserObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stefan on 20.8.2016.
 */
@RestController
@RequestMapping(value = "/api")
public class LocationController {
    @Autowired
    private LocationService locationService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/location", method = RequestMethod.GET)
    public List<Location> getAllUserLocations() {
        User user = userService.currentLoggedInUser();
        if (user != null) {
            return user.getLocations();
        }

        return null;
    }

    @RequestMapping(value = "/location", method = RequestMethod.POST)
    public ResponseEntity<Location> saveNewLocation(@RequestBody LocationObject locationObject) throws URISyntaxException {
        Location savedLocation = locationService.save(locationObject);
        return ResponseEntity.created(new URI("api/location/" + savedLocation.getLocationId())).body(savedLocation);
    }

    @RequestMapping(value = "/location/{username}", method = RequestMethod.DELETE)
    public void deleteAllUserLocations(@PathVariable String username) {
        User user = userService.findOneByUsername(username);
        locationService.deleteByUser(user);
        user.getLocations().clear();
    }
}
