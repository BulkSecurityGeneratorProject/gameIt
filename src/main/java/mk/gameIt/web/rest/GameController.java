package mk.gameIt.web.rest;

import mk.gameIt.domain.Game;
import mk.gameIt.service.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
