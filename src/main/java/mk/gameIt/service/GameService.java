package mk.gameIt.service;

import mk.gameIt.domain.Game;

import java.util.List;

/**
 * Created by Stefan on 24.03.2016.
 */
public interface GameService {
    List<Game> findAll();
    Game findOne(Long id);
    Game save(Game game);
    void delete(Long id);
}
