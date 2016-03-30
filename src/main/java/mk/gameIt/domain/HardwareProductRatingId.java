package mk.gameIt.domain;

import java.io.Serializable;

/**
 * Created by Stefan on 1/16/2016.
 */
public class HardwareProductRatingId implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long userId;
    private Long hardwareId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HardwareProductRatingId)) return false;

        HardwareProductRatingId that = (HardwareProductRatingId) o;

        if (!userId.equals(that.userId)) return false;
        return hardwareId.equals(that.hardwareId);

    }

    @Override
    public int hashCode() {
        int result = userId.hashCode();
        result = 31 * result + hardwareId.hashCode();
        return result;
    }

    public Long getHardwareId() {
        return hardwareId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setHardwareId(Long hardwareId) {
        this.hardwareId = hardwareId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
