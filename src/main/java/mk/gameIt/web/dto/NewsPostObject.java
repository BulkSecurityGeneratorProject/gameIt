package mk.gameIt.web.dto;

import mk.gameIt.domain.NewsPost;

/**
 * Created by Stefan on 23.04.2016.
 */
public class NewsPostObject {
    private NewsPost newsPost;
    private TagObject tagObject;

    public NewsPost getNewsPost() {
        return newsPost;
    }

    public void setNewsPost(NewsPost newsPost) {
        this.newsPost = newsPost;
    }

    public TagObject getTagObject() {
        return tagObject;
    }

    public void setTagObject(TagObject tagObject) {
        this.tagObject = tagObject;
    }

    @Override
    public String toString() {
        return "NewsPostObject{" +
                "newsPost=" + newsPost +
                ", tagObject=" + tagObject +
                '}';
    }
}
