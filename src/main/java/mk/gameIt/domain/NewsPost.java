package mk.gameIt.domain;

import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * Created by Stefan on 2/19/2016.
 */
@Entity
@Table
public class NewsPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @NotNull
    @Column(nullable = false)
    private String postTitle;

    @NotNull
    @Column(name = "postDescription", nullable = false, length = 4000)
    private String postDescription;

    @Column(nullable = false)
    private LocalDateTime postAddDate;

    @Column(nullable = false )
    private Long postNumberOfViews =(long)0;

    @Column(nullable = true)
    private String postVideoUrl;

    @ManyToMany()
    @JoinTable(joinColumns = @JoinColumn(name = "postId",referencedColumnName = "postId"),inverseJoinColumns = @JoinColumn(name="tagId", referencedColumnName = "tagId"))
    private List<Tag> tags;

    private String publishedPicturePath;

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getPublishedPicturePath() {
        return publishedPicturePath;
    }

    public void setPublishedPicturePath(String publishedPicturePath) {
        this.publishedPicturePath = publishedPicturePath;
    }

    public NewsPost() {
    }

    public NewsPost(String postTitle, String postDescription, Long postNumberOfViews, String postVideoUrl, List<Tag> tags, String publishedPicturePath) {
        this.postTitle = postTitle;
        this.postDescription = postDescription;
        this.postNumberOfViews = postNumberOfViews;
        this.postVideoUrl = postVideoUrl;
        this.tags = tags;
        this.publishedPicturePath = publishedPicturePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewsPost newsPost = (NewsPost) o;

        if (postId != null ? !postId.equals(newsPost.postId) : newsPost.postId != null) return false;
        if (!postTitle.equals(newsPost.postTitle)) return false;
        if (!postDescription.equals(newsPost.postDescription)) return false;
        if (!postAddDate.equals(newsPost.postAddDate)) return false;
        if (!postNumberOfViews.equals(newsPost.postNumberOfViews)) return false;
        if (postVideoUrl != null ? !postVideoUrl.equals(newsPost.postVideoUrl) : newsPost.postVideoUrl != null)
            return false;
        return publishedPicturePath != null ? publishedPicturePath.equals(newsPost.publishedPicturePath) : newsPost.publishedPicturePath == null;

    }

    @Override
    public int hashCode() {
        int result = postTitle.hashCode();
        result = 31 * result + postDescription.hashCode();
        result = 31 * result + postAddDate.hashCode();
        result = 31 * result + postNumberOfViews.hashCode();
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

    public LocalDateTime getPostAddDate() {
        return postAddDate;
    }

    public void setPostAddDate(LocalDateTime postAddDate) {
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
