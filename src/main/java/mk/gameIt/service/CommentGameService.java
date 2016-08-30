package mk.gameIt.service;

import mk.gameIt.domain.CommentGame;
import mk.gameIt.domain.Game;
import mk.gameIt.domain.User;
import mk.gameIt.web.dto.CommentGameObject;
import mk.gameIt.web.dto.DeleteCommentObject;

/**
 * Created by Stefan on 22.04.2016.
 */
public interface CommentGameService {
    CommentGame save(Long gameId, String commentText, User user);

    void delete(Game game, DeleteCommentObject commentGame);
}
