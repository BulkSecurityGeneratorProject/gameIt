package mk.gameIt.service;

import mk.gameIt.domain.Game;
import mk.gameIt.domain.User;
import mk.gameIt.web.dto.GameObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Stefan on 24.03.2016.
 */
public interface GameService {
    List<Game> findAll();
    Page<Game> findAll(Pageable pageable);
    Game incrementNumberOfViews(Game game);
    Game findOne(Long id);
    Game save(GameObject gameObject) throws IOException, SQLException;
    void delete(Long id);

    Game rate(Game game, User user, Integer rating);

    Game updateGame(GameObject gameObject);
}
