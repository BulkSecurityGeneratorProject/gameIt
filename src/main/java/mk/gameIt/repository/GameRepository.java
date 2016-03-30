package mk.gameIt.repository;

import mk.gameIt.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Stefan on 24.03.2016.
 */
public interface GameRepository extends JpaRepository<Game, Long> {
}
