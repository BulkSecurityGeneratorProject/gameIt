package mk.gameIt.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Stefan on 1/16/2016.
 */
@Entity
@Table
@IdClass(GameRatingId.class)
public class GameRating implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User userId;

    @Id
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "gameId", referencedColumnName = "gameId")
    private Game gameId;

    @NotNull
    @Column(name = "rating", nullable = false)
    private int rating;


    public int getRating() {
        return rating;
    }

    public Game getGameId() {
        return gameId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setGameId(Game gameId) {
        this.gameId = gameId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameRating)) return false;

        GameRating that = (GameRating) o;

        if (getRating() != that.getRating()) return false;
        if (getUserId() != null ? !getUserId().equals(that.getUserId()) : that.getUserId() != null) return false;
        return getGameId() != null ? getGameId().equals(that.getGameId()) : that.getGameId() == null;

    }

    @Override
    public int hashCode() {
        int result = getUserId() != null ? getUserId().hashCode() : 0;
        result = 31 * result + (getGameId() != null ? getGameId().hashCode() : 0);
        result = 31 * result + getRating();
        return result;
    }
}
