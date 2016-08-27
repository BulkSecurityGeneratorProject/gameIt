package mk.gameIt.service;

import mk.gameIt.domain.CommentGame;
import mk.gameIt.domain.User;
import mk.gameIt.web.dto.CommentGameObject;

/**
 * Created by Stefan on 22.04.2016.
 */
public interface CommentGameService {
    CommentGame save(Long gameId, String commentText, User user);
}
