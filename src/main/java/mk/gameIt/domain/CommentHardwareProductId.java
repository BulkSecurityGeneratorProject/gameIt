package mk.gameIt.domain;

import java.io.Serializable;

/**
 * Created by Stefan on 1/16/2016.
 */
public class CommentHardwareProductId implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long userId;
    private Long hardwareId;
    private Long commentId;

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommentHardwareProductId)) return false;

        CommentHardwareProductId that = (CommentHardwareProductId) o;

        if (getUserId() != null ? !getUserId().equals(that.getUserId()) : that.getUserId() != null) return false;
        if (getHardwareId() != null ? !getHardwareId().equals(that.getHardwareId()) : that.getHardwareId() != null) return false;
        return getCommentId() != null ? getCommentId().equals(that.getCommentId()) : that.getCommentId() == null;

    }

    @Override
    public int hashCode() {
        int result = getUserId() != null ? getUserId().hashCode() : 0;
        result = 31 * result + (getHardwareId() != null ? getHardwareId().hashCode() : 0);
        result = 31 * result + (getCommentId() != null ? getCommentId().hashCode() : 0);
        return result;
    }

    public Long getHardwareId() {
        return hardwareId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setHardwareId(Long hardwareId) {
        this.hardwareId = hardwareId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
