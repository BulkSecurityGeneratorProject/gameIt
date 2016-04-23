package mk.gameIt.web.rest;

import mk.gameIt.domain.Game;
import mk.gameIt.service.GameService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
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

    @RequestMapping(value = "/games", method = RequestMethod.GET)
    public List<Game> getAllGames(){
        return gameService.findAll();
    }

    @RequestMapping(value = "/games/{id}", method = RequestMethod.GET)
    public Game getGame(@PathVariable Long id){
        Game game =  gameService.findOne(id);
        game = gameService.incrementNumberOfViews(game);
        return game;
    }

    @RequestMapping(value = {"/games/{id}/picture"}, method = RequestMethod.GET)
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
}
