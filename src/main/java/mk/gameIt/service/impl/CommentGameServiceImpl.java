package mk.gameIt.service.impl;

import mk.gameIt.domain.CommentGame;
import mk.gameIt.repository.CommentGameRepository;
import mk.gameIt.repository.GameRepository;
import mk.gameIt.repository.UserRepository;
import mk.gameIt.service.CommentGameService;
import mk.gameIt.web.dto.CommentGameObject;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Stefan on 22.04.2016.
 */
@Service
public class CommentGameServiceImpl implements CommentGameService {
    private final Logger log = org.slf4j.LoggerFactory.getLogger(CommentGameServiceImpl.class);
    @Autowired
    CommentGameRepository commentGameRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    GameRepository gameRepository;

    @Transactional
    @Override
    public CommentGame save(CommentGameObject commentGameObject) {
        CommentGame commentGame = new CommentGame();
        if (commentGameObject.getCommentDate() == null) {
            commentGame.setCommentDate(LocalDateTime.now());
        } else {
            commentGame.setCommentDate(commentGameObject.getCommentDate());
        }
        commentGame.setCommentText(commentGameObject.getCommentText());
        long nmrComments = commentGameRepository.count();
        commentGame.setCommentId(nmrComments);
        commentGame.setGameId(gameRepository.findOne(commentGameObject.getGameId()));
        commentGame.setUserId(userRepository.findOne(commentGameObject.getUserId()));
        commentGame = commentGameRepository.save(commentGame);
        log.debug("Created Comment for Game: {}", commentGame);
        return commentGame;
    }
}
