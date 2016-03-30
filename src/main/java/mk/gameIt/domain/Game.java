package mk.gameIt.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Stefan on 24.03.2016.
 */
@Entity
@Table
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    private String gameName;

    @Column(length = 4000)
    @NotNull
    private String gameDescription;

    @Column
    @NotNull
    private Long gameViews;

    @Column
    @NotNull
    private String gameImagePath;

    @Column(length = 1000)
    private String gameMinRequirements;

    @Column(length = 1000)
    private String gameRecRequirements;

    @Column
    private Integer gameReleaseYear;

    @Column
    private Float gameRating;

    @ManyToMany(mappedBy = "gamesRated")
    private List<User> rated;

    public Game(String gameName, String gameDescription, Long gameViews, String gameImagePath, String gameMinRequirements, String gameRecRequirements, Integer gameReleaseYear, Float gameRating) {
        this.gameName = gameName;
        this.gameDescription = gameDescription;
        this.gameViews = gameViews;
        this.gameImagePath = gameImagePath;
        this.gameMinRequirements = gameMinRequirements;
        this.gameRecRequirements = gameRecRequirements;
        this.gameReleaseYear = gameReleaseYear;
        this.gameRating = gameRating;
    }

    public Game() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getGameDescription() {
        return gameDescription;
    }

    public void setGameDescription(String gameDescription) {
        this.gameDescription = gameDescription;
    }

    public Long getGameViews() {
        return gameViews;
    }

    public void setGameViews(Long gameViews) {
        this.gameViews = gameViews;
    }

    public String getGameImagePath() {
        return gameImagePath;
    }

    public void setGameImagePath(String gameImagePath) {
        this.gameImagePath = gameImagePath;
    }

    public String getGameMinRequirements() {
        return gameMinRequirements;
    }

    public void setGameMinRequirements(String gameMinRequirements) {
        this.gameMinRequirements = gameMinRequirements;
    }

    public String getGameRecRequirements() {
        return gameRecRequirements;
    }

    public void setGameRecRequirements(String gameRecRequirements) {
        this.gameRecRequirements = gameRecRequirements;
    }

    public Integer getGameReleaseYear() {
        return gameReleaseYear;
    }

    public void setGameReleaseYear(Integer gameReleaseYear) {
        this.gameReleaseYear = gameReleaseYear;
    }

    public Float getGameRating() {
        return gameRating;
    }

    public void setGameRating(Float gameRating) {
        this.gameRating = gameRating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Game game = (Game) o;

        if (!gameName.equals(game.gameName)) return false;
        if (!gameDescription.equals(game.gameDescription)) return false;
        return gameImagePath.equals(game.gameImagePath);

    }

    @Override
    public int hashCode() {
        int result = gameName.hashCode();
        result = 31 * result + gameDescription.hashCode();
        result = 31 * result + gameImagePath.hashCode();
        return result;
    }
}
