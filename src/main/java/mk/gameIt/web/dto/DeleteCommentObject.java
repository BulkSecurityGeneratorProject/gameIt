package mk.gameIt.web.dto;

import mk.gameIt.domain.User;

import java.time.LocalDateTime;

/**
 * Created by Stefan on 30.8.2016.
 */
public class DeleteCommentObject {
    private Long commentId;
    private User userId;
    private String commentText;

    public DeleteCommentObject(Long commentId, User userId, String commentText) {
        this.commentId = commentId;
        this.userId = userId;
        this.commentText = commentText;
    }

    public DeleteCommentObject() {
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

}
