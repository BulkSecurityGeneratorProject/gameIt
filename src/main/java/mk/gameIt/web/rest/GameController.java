package mk.gameIt.web.rest;

import mk.gameIt.domain.*;
import mk.gameIt.domain.comparators.GameNumberOfViewsComparator;
import mk.gameIt.repository.GameRepository;
import mk.gameIt.repository.UserRepository;
import mk.gameIt.service.CommentGameService;
import mk.gameIt.service.GameService;
import mk.gameIt.service.UserService;
import mk.gameIt.web.dto.CommentGameObject;
import mk.gameIt.web.dto.DeleteCommentObject;
import mk.gameIt.web.dto.GameObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Stefan on 24.03.2016.
 */

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class GameController {

    private final Logger log = LoggerFactory.getLogger(GameController.class);
    @Autowired
    private GameService gameService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentGameService commentGameService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GameRepository gameRepository;


    @RequestMapping(value = "/games", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Game> getAllGames(@RequestParam(value = "page", required = false) Integer page,
                                  @RequestParam(value = "size", required = false) Integer size) throws SQLException, UnsupportedEncodingException {
        List<Game> list = new ArrayList<>();
        if (page != null && size != null) {
            Pageable pageable = new PageRequest(page, size);
            list = gameService.findAll(pageable).getContent();
            return list;
        } else {
            list = gameService.findAll();
            return list;
        }
    }

    @RequestMapping(value = "/games/{id}", method = RequestMethod.PUT)
    public ResponseEntity updateGame(@RequestBody GameObject gameObject) {
        try {
            Game game = gameService.updateGame(gameObject);
            return new ResponseEntity(HttpStatus.OK).ok(game);
        } catch(Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/games/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteGame(@PathVariable Long id) {
        try{
            gameService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/games", method = RequestMethod.POST)
    public Game saveNewGame(@RequestBody GameObject gameObject) throws IOException, SQLException {
        return gameService.save(gameObject);
    }

    @RequestMapping(value = "/games/{id}", method = RequestMethod.GET)
    public Game getGame(@PathVariable Long id) throws UnsupportedEncodingException, SQLException {
        Game game = gameService.findOne(id);
        game = gameService.incrementNumberOfViews(game);

        return game;
    }

    @RequestMapping(value = "/games/{id}/rate", method = RequestMethod.POST)
    public ResponseEntity rateGame(@PathVariable Long id, @RequestBody Integer rating) {
        try {
            User user = userService.currentLoggedInUser();
            Game game = gameService.findOne(id);
            game = gameService.rate(game, user, rating);
            return new ResponseEntity(HttpStatus.OK).ok(game);
        }
        catch(Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/games/sortByViews", method = RequestMethod.GET)
    public ResponseEntity<List<Game>> sortGamesByViews() {
        try {
            List<Game> games = gameService.findAll();
            Collections.sort(games, new GameNumberOfViewsComparator());
            if (games.size() > 5) {
                games = games.subList(0, 5);
            }

            return new ResponseEntity<List<Game>>(HttpStatus.OK).ok(games);
        } catch (Exception e) {
            return new ResponseEntity<List<Game>>(HttpStatus.NO_CONTENT);
        }
    }
//
//    @RequestMapping(value = "/games/{id}/wishlist", method = RequestMethod.POST)
//    public ResponseEntity putGameToWishlist(@PathVariable Long id) {
//        Game game  = gameService.findOne(id);
//        userService.addToWishlist(game);
//    }

    @RequestMapping(value = "/games/comment", method = RequestMethod.POST)
    public Game commentGame(@RequestBody CommentGameObject object) {
        User user = userService.currentLoggedInUser();
        if (user != null) {
            CommentGame commentGame = commentGameService.save(object.getGameId(), object.getCommentText(), user);

            Game game = commentGame.getGameId();
            game.getComments().add(commentGame);
            gameRepository.save(game);

            user.getCommentsGame().add(commentGame);
            userRepository.save(user);
            Collections.sort(game.getComments());
            return game;
        } else {
            return null;
        }
    }
    @RequestMapping(value = "/games/{id}/comment", method = RequestMethod.POST)
    public ResponseEntity deleteComment(@PathVariable Long id, @RequestBody DeleteCommentObject commentGame) {
        try {
            Game game = gameService.findOne(id);
            commentGameService.delete(game, commentGame);
            return new ResponseEntity(HttpStatus.OK).ok(game);
        }
        catch(Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
