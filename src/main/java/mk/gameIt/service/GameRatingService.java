package mk.gameIt.service;

import mk.gameIt.domain.GameRating;
import mk.gameIt.web.dto.GameRatingObject;

/**
 * Created by Stefan on 23.04.2016.
 */
public interface GameRatingService {
    GameRating save(GameRatingObject gameRatingObject);
}
