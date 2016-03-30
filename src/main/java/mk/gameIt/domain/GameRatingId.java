package mk.gameIt.domain;

import java.io.Serializable;

/**
 * Created by Stefan on 1/16/2016.
 */
public class GameRatingId implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long userId;
    private Long gameId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameRatingId)) return false;

        GameRatingId that = (GameRatingId) o;

        if (!userId.equals(that.userId)) return false;
        return gameId.equals(that.gameId);

    }

    @Override
    public int hashCode() {
        int result = userId.hashCode();
        result = 31 * result + gameId.hashCode();
        return result;
    }

    public Long getGameId() {
        return gameId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
