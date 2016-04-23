package mk.gameIt.web.dto;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Created by Stefan on 23.04.2016.
 */
public class CommentHardwareProductObject {
    private Long commentId;
    @NotNull
    private Long userId;
    @NotNull
    private Long hardwareProductId;
    @NotNull
    private String commentText;

    private LocalDateTime commentDate;

    public CommentHardwareProductObject() {
    }

    public CommentHardwareProductObject(Long commentId, Long userId, Long hardwareProductId, String commentText, LocalDateTime commentDate) {
        this.commentId = commentId;
        this.userId = userId;
        this.hardwareProductId = hardwareProductId;
        this.commentText = commentText;
        this.commentDate = commentDate;
    }

    public CommentHardwareProductObject(CommentHardwareProductObject commentGameObject) {
        this.commentId = commentGameObject.getCommentId();
        this.userId = commentGameObject.getUserId();
        this.hardwareProductId = commentGameObject.getHardwareProductId();
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

    public Long getHardwareProductId() {
        return hardwareProductId;
    }

    public void setHardwareProductId(Long hardwareProductId) {
        this.hardwareProductId = hardwareProductId;
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
