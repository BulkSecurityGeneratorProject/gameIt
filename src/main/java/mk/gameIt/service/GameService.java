package mk.gameIt.service;

import mk.gameIt.domain.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by Stefan on 24.03.2016.
 */
public interface GameService {
    List<Game> findAll();
    Page<Game> findAll(Pageable pageable);

    Game findOne(Long id);
    Game save(Game game);
    void delete(Long id);
}
