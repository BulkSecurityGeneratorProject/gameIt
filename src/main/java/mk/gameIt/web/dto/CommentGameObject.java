package mk.gameIt.web.dto;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Created by Stefan on 23.04.2016.
 */
public class CommentGameObject {
    private Long commentId;
    @NotNull
    private Long userId;
    @NotNull
    private Long gameId;
    @NotNull
    private String commentText;

    private LocalDateTime commentDate;

    public CommentGameObject() {
    }

    public CommentGameObject(Long commentId, Long userId, Long gameId, String commentText, LocalDateTime commentDate) {
        this.commentId = commentId;
        this.userId = userId;
        this.gameId = gameId;
        this.commentText = commentText;
        this.commentDate = commentDate;
    }

    public CommentGameObject(CommentGameObject commentGameObject) {
        this.commentId = commentGameObject.getCommentId();
        this.userId = commentGameObject.getUserId();
        this.gameId = commentGameObject.getGameId();
        this.commentText = commentGameObject.getCommentText();
        this.commentDate = commentGameObject.getCommentDate();
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public LocalDateTime getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(LocalDateTime commentDate) {
        this.commentDate = commentDate;
    }
}
