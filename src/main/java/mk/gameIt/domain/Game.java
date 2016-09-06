package mk.gameIt.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
//import org.hibernate.search.annotations.Field;
//import org.hibernate.search.annotations.Indexed;
//import org.hibernate.search.annotations.Store;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

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

    private Double gamePrice;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "game")
    private Set<UserGameOrder> userGameOrders = new HashSet<UserGameOrder>(0);

    private String category;

    @ManyToOne
    @JoinColumn(name = "user_seller")
    private User userSeller;

    @OneToMany(mappedBy = "gameId", cascade = CascadeType.REMOVE)
    private List<GameRating> ratings;

    @OneToMany(mappedBy = "gameId", cascade = CascadeType.REMOVE)
    private List<CommentGame> comments;


    public Game() {
        gameGradeSum = new Double(0);
        gameNumberOfViews = new Long(0);
        comments = new ArrayList<CommentGame>();
        gamePrice = new Double(0.0);
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
                ", gamePrice='" + gamePrice + '\'' +
                ", gameDescription='" + gameDescription + '\'' +
                ", gameMinimalPerformance='" + gameMinimalPerformance + '\'' +
                ", gameOptimalPerformance='" + gameOptimalPerformance + '\'' +
                ", gameNumberOfViews=" + gameNumberOfViews +
                ", gameGradeSum=" + gameGradeSum +
                '}';
    }

//    public User getUserWishlist() {
//        return userWishlist;
//    }
//
//    public void setUserWishlist(User userWishlist) {
//        this.userWishlist = userWishlist;
//    }

    public void setGameNumberOfViews(Long gameNumberOfViews) {
        this.gameNumberOfViews = gameNumberOfViews;
    }

    public void setGameGradeSum(Double gameGradeSum) {
        this.gameGradeSum = gameGradeSum;
    }

    public Double getGamePrice() {
        return gamePrice;
    }

    public void setGamePrice(Double gamePrice) {
        this.gamePrice = gamePrice;
    }

    public Set<UserGameOrder> getUserGameOrders() {
        return userGameOrders;
    }

    public void setUserGameOrders(Set<UserGameOrder> userGameOrders) {
        this.userGameOrders = userGameOrders;
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

    public User getUserSeller() {
        return userSeller;
    }

    public void setUserSeller(User userSeller) {
        this.userSeller = userSeller;
    }

    public void setGameGradeSum(double gameGradeSum) {
        this.gameGradeSum = gameGradeSum;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void recalculateGameGradeSum() {
        double gameGradeSum = 0;
        for(GameRating rating: this.ratings) {
            gameGradeSum += rating.getRating();
        }
        gameGradeSum /= this.ratings.size();

        this.setGameGradeSum(gameGradeSum);
    }
}
