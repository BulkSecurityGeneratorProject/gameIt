package mk.gameIt.service.impl;

import mk.gameIt.domain.CommentGame;
import mk.gameIt.repository.CommentGameRepository;
import mk.gameIt.repository.GameRepository;
import mk.gameIt.repository.UserRepository;
import mk.gameIt.service.CommentGameService;
import mk.gameIt.web.dto.CommentGameObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Stefan on 22.04.2016.
 */
@Service
@Transactional
public class CommentGameServiceImpl implements CommentGameService {
    @Autowired
    CommentGameRepository commentGameRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    GameRepository gameRepository;

    @Override
    public CommentGame save(CommentGameObject commentGameObject) {
        CommentGame newGame = new CommentGame();
        if (commentGameObject.getCommentDate() == null) {
            newGame.setCommentDate(LocalDateTime.now());
        } else {
            newGame.setCommentDate(commentGameObject.getCommentDate());
        }
        newGame.setCommentText(commentGameObject.getCommentText());
        List<CommentGame> games = commentGameRepository.findAll();
        long nmrComments = commentGameRepository.count();
        newGame.setCommentId(nmrComments);
        newGame.setGameId(gameRepository.findOne(commentGameObject.getGameId()));
        newGame.setUserId(userRepository.findOne(commentGameObject.getUserId()));

        return commentGameRepository.save(newGame);
    }
}
