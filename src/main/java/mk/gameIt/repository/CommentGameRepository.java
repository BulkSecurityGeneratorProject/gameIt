package mk.gameIt.repository;

import mk.gameIt.domain.CommentGame;
import mk.gameIt.domain.CommentGameId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.w3c.dom.Comment;

/**
 * Created by Stefan on 22.04.2016.
 */
public interface CommentGameRepository extends JpaRepository<CommentGame, CommentGameId> {
}
