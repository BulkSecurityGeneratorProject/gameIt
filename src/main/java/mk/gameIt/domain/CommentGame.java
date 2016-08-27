package mk.gameIt.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
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

    @Id
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "gameId", referencedColumnName = "gameId")
    private Game gameId;

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @NotNull
    @Column(name = "commentText", nullable = false,  length = 4000)
    private String commentText;


    @Column(nullable = false)
    private LocalDateTime commentDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommentGame that = (CommentGame) o;

        if (!userId.equals(that.userId)) return false;
        if (!gameId.equals(that.gameId)) return false;
        return commentText.equals(that.commentText);

    }

    @Override
    public int hashCode() {
        int result = userId.hashCode();
        result = 31 * result + gameId.hashCode();
        result = 31 * result + commentText.hashCode();
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

    public LocalDateTime getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(LocalDateTime commentDate) {
        this.commentDate = commentDate;
    }
}
