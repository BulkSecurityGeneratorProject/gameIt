package mk.gameIt.web.rest;

import mk.gameIt.domain.CommentGame;
import mk.gameIt.domain.Game;
import mk.gameIt.domain.User;
import mk.gameIt.domain.comparators.GameNumberOfViewsComparator;
import mk.gameIt.repository.GameRepository;
import mk.gameIt.repository.UserRepository;
import mk.gameIt.service.CommentGameService;
import mk.gameIt.service.GameService;
import mk.gameIt.service.UserService;
import mk.gameIt.web.dto.CommentGameObject;
import mk.gameIt.web.dto.GameObject;
import mk.gameIt.web.dto.UserObject;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
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

    @RequestMapping(value = "/games", method = RequestMethod.POST)
    public Game saveNewGame(@RequestBody GameObject gameObject) throws IOException, SQLException {

        return gameService.save(gameObject);
    }


    @RequestMapping(value = "/games/{id}", method = RequestMethod.GET)
    public Game getGame(@PathVariable Long id) throws UnsupportedEncodingException, SQLException {
        Game game = gameService.findOne(id);
        game = gameService.incrementNumberOfViews(game);

        //  GameObject gameObject = new GameObject(game.getGameId(), game.getGameName(), game.getGameReleaseYear(), base64Encoded, game.getGameDescription(), game.getGameMinimalPerformance(), game.getGameOptimalPerformance(), game.getGameNumberOfViews(), game.getGameGradeSum());

        return game;
    }

  /*  @RequestMapping(value = "/games/{id}/picture", method = RequestMethod.GET)
    public void gamePicture(HttpServletResponse response, @PathVariable Long id) throws IOException, SQLException {
        OutputStream out = response.getOutputStream();
        Game game = gameService.findOne(id);
        if (game == null || game.getGamePicture() == null) {
            return;
        }
        String contentDisposition = String.format("inline;filename=\"%s\"",
                game.getGameName() + ".png?gameId=" + game.getGameId());
        response.setHeader("Content-Disposition", contentDisposition);
        response.setContentType("image/png");
        response.setContentLength((int) game.getGamePicture().length());
        IOUtils.copy(game.getGamePicture().getBinaryStream(), out);
        out.flush();
        out.close();
    }*/

    @RequestMapping(value = "/games/sortByViews", method = RequestMethod.GET)
    public ResponseEntity<List<Game>> sortGamesByViews() {
        try {
            List<Game> games = gameService.findAll();
            Collections.sort(games, new GameNumberOfViewsComparator());
            games = games.subList(0, 5);
            return new ResponseEntity<List<Game>>(HttpStatus.OK).ok(games);
        } catch (Exception e) {
            return new ResponseEntity<List<Game>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/games/comment", method = RequestMethod.POST)
    public Game commentGame(@RequestBody CommentGameObject object) { //} Long gameId, @RequestParam String commentText) {
        User user = userService.currentLoggedInUser();
        if (user != null) {
            CommentGame commentGame = commentGameService.save(object.getGameId(), object.getCommentText(), user);
            Game game = commentGame.getGameId();
            game.getComments().add(commentGame);
            gameRepository.save(game);
            user.getCommentsGame().add(commentGame);
            userRepository.save(user);
            return game;
        } else {
            return null;
        }
    }
}
