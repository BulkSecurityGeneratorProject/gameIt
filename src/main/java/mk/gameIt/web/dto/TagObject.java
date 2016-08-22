package mk.gameIt.web.dto;

/**
 * Created by Stefan on 22.8.2016.
 */
public class TagObject {

    private String tagName;


    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public String toString() {
        return "TagObject{" +
                "tagName='" + tagName + '\'' +
                '}';
    }
}
