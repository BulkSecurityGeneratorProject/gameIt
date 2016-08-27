package mk.gameIt.web.dto;

import mk.gameIt.domain.Game;

import javax.validation.constraints.NotNull;
import java.sql.Blob;
import java.util.Date;

/**
 * Created by Stefan on 23.04.2016.
 */
//TODO: FINISH CLASS
public class GameObject {

    private Long gameId;

    @NotNull
    private String gameName;

    @NotNull
    private Date gameReleaseYear;

    private String gamePicture;

    @NotNull
    private String gameDescription;

    private String gameMinimalPerformance;

    private String gameOptimalPerformance;

    private Long gameNumberOfViews;

    private Double gameGradeSum;

    public GameObject() {
    }

    public GameObject(String gameName, Date gameReleaseYear, String gamePicture, String gameDescription, String gameMinimalPerformance, String gameOptimalPerformance, Long gameNumberOfViews, Double gameGradeSum) {

        this.gameName = gameName;
        this.gameReleaseYear = gameReleaseYear;
        this.gamePicture = gamePicture;
        this.gameDescription = gameDescription;
        this.gameMinimalPerformance = gameMinimalPerformance;
        this.gameOptimalPerformance = gameOptimalPerformance;
        this.gameNumberOfViews = gameNumberOfViews;
        this.gameGradeSum = gameGradeSum;
    }

    public Game getGame() {
        Game game = new Game();
        game.setGameName(this.gameName);
        game.setGameReleaseYear(this.gameReleaseYear);
        game.setGamePicture(this.gamePicture);
        game.setGameDescription(this.gameDescription);
        game.setGameMinimalPerformance(this.gameMinimalPerformance);
        game.setGameOptimalPerformance(this.gameOptimalPerformance);
        return game;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public Date getGameReleaseYear() {
        return gameReleaseYear;
    }

    public void setGameReleaseYear(Date gameReleaseYear) {
        this.gameReleaseYear = gameReleaseYear;
    }

    public String getGamePicture() {
        return gamePicture;
    }

    public void setGamePicture(String gamePicture) {
        this.gamePicture = gamePicture;
    }

    public String getGameDescription() {
        return gameDescription;
    }

    public void setGameDescription(String gameDescription) {
        this.gameDescription = gameDescription;
    }

    public String getGameMinimalPerformance() {
        return gameMinimalPerformance;
    }

    public void setGameMinimalPerformance(String gameMinimalPerformance) {
        this.gameMinimalPerformance = gameMinimalPerformance;
    }

    public String getGameOptimalPerformance() {
        return gameOptimalPerformance;
    }

    public void setGameOptimalPerformance(String gameOptimalPerformance) {
        this.gameOptimalPerformance = gameOptimalPerformance;
    }

    public Long getGameNumberOfViews() {
        return gameNumberOfViews;
    }

    public void setGameNumberOfViews(Long gameNumberOfViews) {
        this.gameNumberOfViews = gameNumberOfViews;
    }

    public Double getGameGradeSum() {
        return gameGradeSum;
    }

    public void setGameGradeSum(Double gameGradeSum) {
        this.gameGradeSum = gameGradeSum;
    }
}
