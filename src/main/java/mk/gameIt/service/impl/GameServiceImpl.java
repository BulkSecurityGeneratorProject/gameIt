package mk.gameIt.service.impl;

import mk.gameIt.domain.Game;
import mk.gameIt.repository.GameRepository;
import mk.gameIt.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Stefan on 24.03.2016.
 */

@Service
public class GameServiceImpl implements GameService {
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

    @Override
    public Game findOne(Long id) {
        return gameRepository.findOne(id);
    }

    @Override
    public Game save(Game game) {
        return gameRepository.save(game);
    }

    @Override
    public void delete(Long id) {
        gameRepository.delete(id);

    }
}
