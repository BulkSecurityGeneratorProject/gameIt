package mk.gameIt.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Stefan on 1/15/2016.
 */
@Entity
@Table
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long videoId;

    @NotNull
    @Column(nullable = false)
    private String videoLink;

    @ManyToOne
    @JoinColumn(name = "gameId")
    private Game game;

    public Video() {

    }

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public String toString() {
        return "Video{" +
                "videoId=" + videoId +
                ", videoLink='" + videoLink + '\'' +
                ", game=" + game +
                '}';
    }
}
