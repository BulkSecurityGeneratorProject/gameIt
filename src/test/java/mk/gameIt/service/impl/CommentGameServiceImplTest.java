package mk.gameIt.service.impl;

import mk.gameIt.GameItApplication;
import mk.gameIt.domain.Game;
import mk.gameIt.domain.User;
import mk.gameIt.repository.CommentGameRepository;
import mk.gameIt.repository.GameRepository;
import mk.gameIt.repository.UserRepository;
import mk.gameIt.service.CommentGameService;
import mk.gameIt.web.dto.CommentGameObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;

/**
 * Created by Stefan on 22.04.2016.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = GameItApplication.class)
@WebAppConfiguration
public class CommentGameServiceImplTest {
    @Autowired
    private CommentGameRepository commentGameRepository;
    @Autowired
    private CommentGameService commentGameService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GameRepository gameRepository;

    @Test
    public void save() {
        CommentGameObject commentGameObject = new CommentGameObject();
        commentGameObject.setCommentText("newText");
        User user = userRepository.findOneByUsername("admin");
        Game game = new Game();
        game.setGameName("testGame1");
        game.setGameDescription("testGame1Desc");
        game.setGameReleaseYear(new Date());
        game = gameRepository.save(game);
//        commentGameObject.setUserId(user.getUserId());
//        commentGameObject.setGameId(game.getGameId());
//        commentGameService.save(commentGameObject);
    }
}