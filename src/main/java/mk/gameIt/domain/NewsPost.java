package mk.gameIt.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Stefan on 2/19/2016.
 */
@Entity
@Table
public class NewsPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(nullable = false)
    private String postTitle;

    @Column(name = "postDescription", nullable = false)
    private String postDescription;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date postAddDate;

    @Column(nullable = false )
    private Long postNumberOfViews;

    @Column(nullable = true)
    private String postVideoUrl;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NewsPost)) return false;

        NewsPost newsPost = (NewsPost) o;

        if (!getPostId().equals(newsPost.getPostId())) return false;
        if (!getPostTitle().equals(newsPost.getPostTitle())) return false;
        if (!getPostDescription().equals(newsPost.getPostDescription())) return false;
        if (!getPostAddDate().equals(newsPost.getPostAddDate())) return false;
        if (!getPostNumberOfViews().equals(newsPost.getPostNumberOfViews())) return false;
        return getPostVideoUrl().equals(newsPost.getPostVideoUrl());

    }

    @Override
    public int hashCode() {
        int result = getPostId().hashCode();
        result = 31 * result + getPostTitle().hashCode();
        result = 31 * result + getPostDescription().hashCode();
        result = 31 * result + getPostAddDate().hashCode();
        result = 31 * result + getPostNumberOfViews().hashCode();
        result = 31 * result + getPostVideoUrl().hashCode();
        return result;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getPostNumberOfViews() {
        return postNumberOfViews;
    }

    public void setPostNumberOfViews(Long postNumberOfViews) {
        this.postNumberOfViews = postNumberOfViews;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public Date getPostAddDate() {
        return postAddDate;
    }

    public void setPostAddDate(Date postAddDate) {
        this.postAddDate = postAddDate;
    }

    public String getPostDescription() {
        return postDescription;
    }

    public void setPostDescription(String postDescription) {
        this.postDescription = postDescription;
    }

    public String getPostVideoUrl() {
        return postVideoUrl;
    }

    public void setPostVideoUrl(String postVideoUrl) {
        this.postVideoUrl = postVideoUrl;
    }

    @Override
    public String toString() {
        return "NewsPost{" +
                "postId=" + postId +
                ", postTitle='" + postTitle + '\'' +
                ", postDescription='" + postDescription + '\'' +
                ", postAddDate=" + postAddDate +
                ", postNumberOfViews=" + postNumberOfViews +
                ", postVideoUrl='" + postVideoUrl + '\'' +
                '}';
    }
}
