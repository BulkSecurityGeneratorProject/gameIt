//package mk.gameIt.web.rest;
//
//import mk.gameIt.search.GameSearch;
//import mk.gameIt.service.UserService;
//import mk.gameIt.web.dto.GameObject;
//import org.apache.catalina.connector.Response;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.*;
//
//import java.security.Principal;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by Stefan on 24.03.2016.
// */
//@RestController
//@RequestMapping("/api")
//public class MainController {
//    private final Logger log = LoggerFactory.getLogger(MainController.class);
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private GameSearch gameSearch;
//
//    @RequestMapping("/search")
//    public ResponseEntity<List<GameObject>> search(@RequestBody String query) {
//        List searchResults = null;
//        try {
//            searchResults = gameSearch.search(query);
//            return new ResponseEntity<List<GameObject>>(HttpStatus.OK).ok(searchResults);
//        }
//        catch (Exception ex) {
//            // here you should handle unexpected errors
//            // ...
//            // throw ex;
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//}
