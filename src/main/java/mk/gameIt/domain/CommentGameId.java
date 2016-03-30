package mk.gameIt.domain;

import java.io.Serializable;

/**
 * Created by Stefan on 1/16/2016.
 */
public class CommentGameId implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long userId;
    private Long gameId;
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
        if (!(o instanceof CommentGameId)) return false;

        CommentGameId that = (CommentGameId) o;

        if (getUserId() != null ? !getUserId().equals(that.getUserId()) : that.getUserId() != null) return false;
        if (getGameId() != null ? !getGameId().equals(that.getGameId()) : that.getGameId() != null) return false;
        return getCommentId() != null ? getCommentId().equals(that.getCommentId()) : that.getCommentId() == null;

    }

    @Override
    public int hashCode() {
        int result = getUserId() != null ? getUserId().hashCode() : 0;
        result = 31 * result + (getGameId() != null ? getGameId().hashCode() : 0);
        result = 31 * result + (getCommentId() != null ? getCommentId().hashCode() : 0);
        return result;
    }

    public Long getGameId() {
        return gameId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
