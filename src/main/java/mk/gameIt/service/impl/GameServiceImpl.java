package mk.gameIt.service.impl;

import mk.gameIt.domain.Game;
import mk.gameIt.repository.GameRepository;
import mk.gameIt.service.GameService;
import mk.gameIt.web.dto.GameObject;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Stefan on 24.03.2016.
 */

@Service
@Transactional
public class GameServiceImpl implements GameService {
    private final Logger log = org.slf4j.LoggerFactory.getLogger(GameServiceImpl.class);

    @Autowired
    private GameRepository gameRepository;


    @Override
    public List<Game> findAll() {

        List<Game> gameList = gameRepository.findAll();
        //Setting the comments to null since we don't need them
        //when we show the games to the user
        for (Game game : gameList) {
            game.setComments(null);
        }
        return gameList;
    }

    /**
     * Game finder intended for infinite scroll functionality
     *
     * @param pageable
     * @return
     */
    @Override
    public Page<Game> findAll(Pageable pageable) {
        Page<Game> gameList = gameRepository.findAll(pageable);
        //Setting the comments to null since we don't need them
        //when we show the games to the user
        for (Game game : gameList) {
            game.setComments(null);
        }
        return gameList;
    }

    @Override
    @Transactional
    public synchronized Game incrementNumberOfViews(Game game) {
        game.setGameNumberOfViews(game.getGameNumberOfViews()+1);
        return gameRepository.save(game);
    }

    @Override
    public Game findOne(Long id) {
        return gameRepository.findOne(id);
    }

    @Override
    @Transactional
    public Game save(GameObject gameObject) throws IOException, SQLException {
        Game game = gameObject.getGame();
        game = gameRepository.save(game);
        log.debug("Created Game: {}", game);
        return game;
    }

    @Override
    @Transactional
    public void delete(Long id) {
     //   Game game = gameRepository.findOne(id);
     //   log.debug("Deleted Game: {}", game);
        gameRepository.delete(id);

    }
}
