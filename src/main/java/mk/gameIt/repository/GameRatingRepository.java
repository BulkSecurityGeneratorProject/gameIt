package mk.gameIt.repository;

import mk.gameIt.domain.GameRating;
import mk.gameIt.domain.GameRatingId;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Stefan on 23.04.2016.
 */
public interface GameRatingRepository extends JpaRepository<GameRating,GameRatingId> {
}
