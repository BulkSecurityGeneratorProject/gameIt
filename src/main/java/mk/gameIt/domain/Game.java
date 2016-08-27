package mk.gameIt.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Stefan on 24.03.2016.
 */
@Entity
@Indexed
@Table(name = "Game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gameId;

    @NotNull
    @Field
    @Column(nullable = false)
    private String gameName;

    @NotNull
    @Column(nullable = false)
    private Date gameReleaseYear;

    @Column(nullable = true, length = 4000)
    private String gamePicture;

    @NotNull
    @Field(store = Store.NO)
    @Column(nullable = false, length = 4000)
    private String gameDescription;

    @Column(length = 1000)
    private String gameMinimalPerformance;
    @Column(length = 1000)
    private String gameOptimalPerformance;
    @Column(nullable = false)
    private Long gameNumberOfViews = (long) 0;

    @Column(nullable = true)
    private Double gameGradeSum = (double) 0;

    @ManyToMany
    @JoinTable(name = "GameGenre", joinColumns = {@JoinColumn(name = "gameId")}, inverseJoinColumns = {@JoinColumn(name = "genreId")})
    private List<Genre> gameGenres;

    @OneToMany(mappedBy = "gameId")
    private List<GameRating> ratings;

    @ManyToMany(mappedBy = "publishedGames")
    private List<Company> gameCompanies;

    @OneToMany(mappedBy = "gameId")
    private List<CommentGame> comments;

    public Game() {
        gameGradeSum = new Double(0);
        gameNumberOfViews = new Long(0);
        comments = new ArrayList<CommentGame>();
        gameGenres = new ArrayList<Genre>();
        gameCompanies = new ArrayList<Company>();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Game game = (Game) o;

        if (!gameName.equals(game.gameName)) return false;
        if (!gamePicture.equals(game.gamePicture)) return false;
        return gameDescription.equals(game.gameDescription);

    }

    @Override
    public int hashCode() {
        int result = gameName.hashCode();
        result = 31 * result + gameDescription.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Game{" +
                "gameId=" + gameId +
                ", gameName='" + gameName + '\'' +
                ", gameReleaseYear=" + gameReleaseYear +
                ", gamePicture='" + gamePicture + '\'' +
                ", gameDescription='" + gameDescription + '\'' +
                ", gameMinimalPerformance='" + gameMinimalPerformance + '\'' +
                ", gameOptimalPerformance='" + gameOptimalPerformance + '\'' +
                ", gameNumberOfViews=" + gameNumberOfViews +
                ", gameGradeSum=" + gameGradeSum +
                '}';
    }

    public List<GameRating> getRatings() {
        return ratings;
    }

    public void setRatings(List<GameRating> ratings) {
        this.ratings = ratings;
    }

    public List<CommentGame> getComments() {
        return comments;
    }

    public void setComments(List<CommentGame> comments) {
        this.comments = comments;
    }


    public List<Company> getGameCompanies() {
        return gameCompanies;
    }

    public void setGameCompanies(List<Company> gameCompanies) {
        this.gameCompanies = gameCompanies;
    }

    public List<Genre> getGameGenres() {
        return gameGenres;
    }

    public void setGameGenres(List<Genre> gameGenres) {
        this.gameGenres = gameGenres;
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

    public long getGameNumberOfViews() {
        return gameNumberOfViews;
    }

    public void setGameNumberOfViews(long gameNumberOfViews) {
        this.gameNumberOfViews = gameNumberOfViews;
    }

    public double getGameGradeSum() {
        return gameGradeSum;
    }

    public void setGameGradeSum(double gameGradeSum) {
        this.gameGradeSum = gameGradeSum;
    }
}
