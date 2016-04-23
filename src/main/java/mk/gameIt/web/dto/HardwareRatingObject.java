package mk.gameIt.web.dto;

import javax.validation.constraints.NotNull;

/**
 * Created by Stefan on 23.04.2016.
 */
public class HardwareRatingObject {
    @NotNull
    private Long userId;
    @NotNull
    private Long hardwareId;
    @NotNull
    private Integer rating;

    public HardwareRatingObject() {
    }

    public HardwareRatingObject(Long userId, Long hardwareId, Integer rating) {
        this.userId = userId;
        this.hardwareId = hardwareId;
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HardwareRatingObject that = (HardwareRatingObject) o;

        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (hardwareId != null ? !hardwareId.equals(that.hardwareId) : that.hardwareId != null) return false;
        return rating != null ? rating.equals(that.rating) : that.rating == null;

    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (hardwareId != null ? hardwareId.hashCode() : 0);
        result = 31 * result + (rating != null ? rating.hashCode() : 0);
        return result;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getHardwareId() {
        return hardwareId;
    }

    public void setHardwareId(Long hardwareId) {
        this.hardwareId = hardwareId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
