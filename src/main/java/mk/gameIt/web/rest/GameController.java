package mk.gameIt.web.rest;

import mk.gameIt.domain.Game;
import mk.gameIt.domain.comparators.GameNumberOfViewsComparator;
import mk.gameIt.service.GameService;
import mk.gameIt.web.dto.GameObject;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @RequestMapping(value = "/games", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GameObject> getAllGames(@RequestParam(value = "page", required = false) Integer page,
                                        @RequestParam(value = "size", required = false) Integer size) throws SQLException, UnsupportedEncodingException {
        List<GameObject> list = new ArrayList<>();
        if (page != null && size != null) {
            Pageable pageable = new PageRequest(page, size);
            List<Game> returnedList = gameService.findAll(pageable).getContent();
            list = getGamesWithPictures(returnedList);
            System.out.println(list);
            return list;
        } else {
            List<Game> returnedList = gameService.findAll();
            list = getGamesWithPictures(returnedList);
            return list;
        }
    }

    private List<GameObject> getGamesWithPictures(List<Game> returnedList) throws UnsupportedEncodingException, SQLException {
        List<GameObject> list = new ArrayList<>();
        for (Game game : returnedList) {
            String base64Encoded = null;
            if (game.getGamePicture() != null) {
                byte[] encodeBase64 = Base64.encode(game.getGamePicture().getBytes(1, (int) game.getGamePicture().length()));
                base64Encoded = new String(encodeBase64, "UTF-8");
            }
            GameObject gameObject = new GameObject(game.getGameId(), game.getGameName(), game.getGameReleaseYear(), base64Encoded, game.getGameDescription(), game.getGameMinimalPerformance(), game.getGameOptimalPerformance(), game.getGameNumberOfViews(), game.getGameGradeSum());
            list.add(gameObject);
        }
        return list;
    }

    @RequestMapping(value = "/games/{id}", method = RequestMethod.GET)
    public Game getGame(@PathVariable Long id) {
        Game game = gameService.findOne(id);
        game = gameService.incrementNumberOfViews(game);
        return game;
    }

    @RequestMapping(value = "/games/{id}/picture", method = RequestMethod.GET)
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
    }

    @RequestMapping(value="/games/sortByViews", method = RequestMethod.GET)
    public ResponseEntity<List<Game>> sortGamesByViews() {
        try {
            List<Game> games = gameService.findAll();
            Collections.sort(games, new GameNumberOfViewsComparator());
            games = games.subList(0, 5);
            return new ResponseEntity<List<Game>>(HttpStatus.OK).ok(games);
        }
        catch(Exception e) {
            return new ResponseEntity<List<Game>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
