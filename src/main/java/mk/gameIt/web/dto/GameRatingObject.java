package mk.gameIt.web.dto;

import javax.validation.constraints.NotNull;

/**
 * Created by Stefan on 23.04.2016.
 */
public class GameRatingObject {
    @NotNull
    private Long userId;
    @NotNull
    private Long gameId;
    @NotNull
    private Integer rating;

    public GameRatingObject() {
    }

    public GameRatingObject(Long userId, Long gameId, Integer rating) {
        this.userId = userId;
        this.gameId = gameId;
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameRatingObject that = (GameRatingObject) o;

        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (gameId != null ? !gameId.equals(that.gameId) : that.gameId != null) return false;
        return rating != null ? rating.equals(that.rating) : that.rating == null;

    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (gameId != null ? gameId.hashCode() : 0);
        result = 31 * result + (rating != null ? rating.hashCode() : 0);
        return result;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
