package mk.gameIt.web.dto;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Created by Stefan on 23.04.2016.
 */
public class CommentGameObject {
    private Long gameId;

    private String commentText;

    public CommentGameObject() {
    }

    public CommentGameObject(Long gameId, String commentText) {
        this.gameId = gameId;
        this.commentText = commentText;
    }

    public CommentGameObject(CommentGameObject commentGameObject) {
        this.gameId = commentGameObject.getGameId();
        this.commentText = commentGameObject.getCommentText();
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
}
