package mk.gameIt.service.impl;

import mk.gameIt.domain.GameRating;
import mk.gameIt.repository.CommentGameRepository;
import mk.gameIt.repository.GameRatingRepository;
import mk.gameIt.repository.GameRepository;
import mk.gameIt.repository.UserRepository;
import mk.gameIt.service.GameRatingService;
import mk.gameIt.web.dto.GameRatingObject;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Stefan on 23.04.2016.
 */
@Service
public class GameRatingServiceImpl implements GameRatingService {
    private final Logger log = org.slf4j.LoggerFactory.getLogger(GameRatingServiceImpl.class);

    @Autowired
    UserRepository userRepository;
    @Autowired
    GameRatingRepository gameRatingRepository;
    @Autowired
    GameRepository gameRepository;

    @Transactional
    @Override
    public GameRating save(GameRatingObject gameRatingObject) {
        GameRating gameRating = new GameRating();
        gameRating.setUserId(userRepository.findOne(gameRatingObject.getUserId()));
        gameRating.setGameId(gameRepository.findOne(gameRatingObject.getGameId()));
        gameRating.setRating(gameRatingObject.getRating());
        gameRating = gameRatingRepository.save(gameRating);
        log.debug("Created Rating for Game: {}", gameRating);
        return gameRating;
    }
}
