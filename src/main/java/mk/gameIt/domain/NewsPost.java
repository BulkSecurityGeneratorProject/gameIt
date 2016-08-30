package mk.gameIt.domain;

import org.hibernate.annotations.CollectionId;
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
public class NewsPost implements Comparable<NewsPost> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @NotNull
    @Column(nullable = false)
    private String postTitle;

    @NotNull
    @Column(name = "postDescription", nullable = false, length = 4000)
    private String postDescription;

    @Column(length = 2000)
    private String smallDescription;

    @Column(nullable = false)
    private LocalDateTime postAddDate;

    @Column(nullable = false )
    private Long postNumberOfViews =(long)0;

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

    public NewsPost(String postTitle, String postDescription, Long postNumberOfViews, LocalDateTime postAddDate, List<Tag> tags, String publishedPicturePath) {
        this.postTitle = postTitle;
        this.postDescription = postDescription;
        this.postNumberOfViews = postNumberOfViews;
        this.tags = tags;
        this.publishedPicturePath = publishedPicturePath;
        this.postAddDate = postAddDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewsPost newsPost = (NewsPost) o;

        if (postId != null ? !postId.equals(newsPost.postId) : newsPost.postId != null) return false;
        if (postTitle != null ? !postTitle.equals(newsPost.postTitle) : newsPost.postTitle != null) return false;
        if (postDescription != null ? !postDescription.equals(newsPost.postDescription) : newsPost.postDescription != null)
            return false;
        if (smallDescription != null ? !smallDescription.equals(newsPost.smallDescription) : newsPost.smallDescription != null)
            return false;
        if (postNumberOfViews != null ? !postNumberOfViews.equals(newsPost.postNumberOfViews) : newsPost.postNumberOfViews != null)
            return false;
        if (tags != null ? !tags.equals(newsPost.tags) : newsPost.tags != null) return false;
        return publishedPicturePath != null ? publishedPicturePath.equals(newsPost.publishedPicturePath) : newsPost.publishedPicturePath == null;

    }

    @Override
    public int hashCode() {
        int result = postId != null ? postId.hashCode() : 0;
        result = 31 * result + (postTitle != null ? postTitle.hashCode() : 0);
        result = 31 * result + (postDescription != null ? postDescription.hashCode() : 0);
        result = 31 * result + (smallDescription != null ? smallDescription.hashCode() : 0);
        result = 31 * result + (postNumberOfViews != null ? postNumberOfViews.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        result = 31 * result + (publishedPicturePath != null ? publishedPicturePath.hashCode() : 0);
        return result;
    }

    public String getSmallDescription() {
        return smallDescription;
    }

    public void setSmallDescription(String smallDescription) {
        this.smallDescription = smallDescription;
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

    @Override
    public String toString() {
        return "NewsPost{" +
                "postId=" + postId +
                ", postTitle='" + postTitle + '\'' +
                ", postDescription='" + postDescription + '\'' +
                ", smallDescription='" + smallDescription + '\'' +
                ", postAddDate=" + postAddDate +
                ", postNumberOfViews=" + postNumberOfViews +
                ", tags=" + tags +
                ", publishedPicturePath='" + publishedPicturePath + '\'' +
                '}';
    }

    @Override
    public int compareTo(NewsPost o) {
        return o.postAddDate.compareTo(this.postAddDate);
    }
}
