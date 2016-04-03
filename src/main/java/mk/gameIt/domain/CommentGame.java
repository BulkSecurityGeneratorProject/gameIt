package mk.gameIt.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Stefan on 1/16/2016.
 */
@Entity
@Table
@IdClass(CommentGameId.class)
public class CommentGame implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User userId;

    @JsonIgnore
    @Id
    @ManyToOne
    @JoinColumn(name = "gameId", referencedColumnName = "gameId")
    private Game gameId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(name = "commentText", nullable = false,  length = 4000)
    private String commentText;

    @Column(name = "commentDate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date commentDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommentGame)) return false;

        CommentGame commentGame = (CommentGame) o;

        if (getUserId() != null ? !getUserId().equals(commentGame.getUserId()) : commentGame.getUserId() != null) return false;
        if (getGameId() != null ? !getGameId().equals(commentGame.getGameId()) : commentGame.getGameId() != null)
            return false;
        if (getCommentId() != null ? !getCommentId().equals(commentGame.getCommentId()) : commentGame.getCommentId() != null) return false;
        if (getCommentText() != null ? !getCommentText().equals(commentGame.getCommentText()) : commentGame.getCommentText() != null) return false;
        return getCommentDate() != null ? getCommentDate().equals(commentGame.getCommentDate()) : commentGame.getCommentDate() == null;

    }

    @Override
    public int hashCode() {
        int result = getUserId() != null ? getUserId().hashCode() : 0;
        result = 31 * result + (getGameId() != null ? getGameId().hashCode() : 0);
        result = 31 * result + (getCommentId() != null ? getCommentId().hashCode() : 0);
        result = 31 * result + (getCommentText() != null ? getCommentText().hashCode() : 0);
        result = 31 * result + (getCommentDate() != null ? getCommentDate().hashCode() : 0);
        return result;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Game getGameId() {
        return gameId;
    }

    public void setGameId(Game gameId) {
        this.gameId = gameId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }
}
