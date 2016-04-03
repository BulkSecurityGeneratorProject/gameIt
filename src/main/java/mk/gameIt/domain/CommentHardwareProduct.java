package mk.gameIt.domain;

import javax.persistence.*;
import java.io.Serializable;
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
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User userId;

    @Id
    @ManyToOne
    @JoinColumn(name = "hardwareId", referencedColumnName = "hardwareId")
    private HardwareProduct hardwareId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(name = "commentText", nullable = false, length = 4000)
    private String commentText;

    @Column(name = "commentDate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date commentDate;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommentHardwareProduct)) return false;

        CommentHardwareProduct that = (CommentHardwareProduct) o;

        if (getUserId() != null ? !getUserId().equals(that.getUserId()) : that.getUserId() != null) return false;
        if (getHardwareId() != null ? !getHardwareId().equals(that.getHardwareId()) : that.getHardwareId() != null) return false;
        if (getCommentId() != null ? !getCommentId().equals(that.getCommentId()) : that.getCommentId() != null) return false;
        if (getCommentText() != null ? !getCommentText().equals(that.getCommentText()) : that.getCommentText() != null) return false;
        return getCommentDate() != null ? getCommentDate().equals(that.getCommentDate()) : that.getCommentDate() == null;

    }

    @Override
    public int hashCode() {
        int result = getUserId() != null ? getUserId().hashCode() : 0;
        result = 31 * result + (getHardwareId() != null ? getHardwareId().hashCode() : 0);
        result = 31 * result + (getCommentId() != null ? getCommentId().hashCode() : 0);
        result = 31 * result + (getCommentText() != null ? getCommentText().hashCode() : 0);
        result = 31 * result + (getCommentDate() != null ? getCommentDate().hashCode() : 0);
        return result;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
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
