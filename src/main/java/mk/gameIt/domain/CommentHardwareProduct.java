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
@IdClass(CommentHardwareProductId.class)
public class CommentHardwareProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User userId;

    @Id
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "hardwareId", referencedColumnName = "hardwareId")
    private HardwareProduct hardwareId;

    @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @NotNull
    @Column(name = "commentText", nullable = false, length = 4000)
    private String commentText;


    //TODO: INSERT COMMENT DATE AUTOMATICALLY ON CREATE
    @Column(nullable = false)
    private LocalDateTime commentDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommentHardwareProduct that = (CommentHardwareProduct) o;

        if (!userId.equals(that.userId)) return false;
        if (!hardwareId.equals(that.hardwareId)) return false;
        if (!commentId.equals(that.commentId)) return false;
        if (!commentText.equals(that.commentText)) return false;
        return commentDate.equals(that.commentDate);

    }

    @Override
    public int hashCode() {
        int result = userId.hashCode();
        result = 31 * result + hardwareId.hashCode();
        result = 31 * result + commentId.hashCode();
        result = 31 * result + commentText.hashCode();
        result = 31 * result + commentDate.hashCode();
        return result;
    }

    public LocalDateTime getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(LocalDateTime commentDate) {
        this.commentDate = commentDate;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public HardwareProduct getHardwareId() {
        return hardwareId;
    }

    public void setHardwareId(HardwareProduct hardwareId) {
        this.hardwareId = hardwareId;
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
}
