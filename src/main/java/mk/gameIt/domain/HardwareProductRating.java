package mk.gameIt.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Stefan on 1/16/2016.
 */
@Entity
@IdClass(HardwareProductRatingId.class)
public class HardwareProductRating implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User userId;

    @Id
    @ManyToOne
    @JoinColumn(name = "hardwareId", referencedColumnName = "hardwareId")
    private HardwareProduct hardwareId;

    @NotNull
    @Column(name = "rating", nullable = false)
    private int rating;

    public HardwareProductRating() {
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public HardwareProduct getHardwareId() {
        return hardwareId;
    }

    public void setHardwareId(HardwareProduct hardwareId) {
        this.hardwareId = hardwareId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HardwareProductRating)) return false;

        HardwareProductRating that = (HardwareProductRating) o;

        if (getRating() != that.getRating()) return false;
        if (!getUserId().equals(that.getUserId())) return false;
        return getHardwareId().equals(that.getHardwareId());

    }

    @Override
    public int hashCode() {
        int result = getUserId().hashCode();
        result = 31 * result + getHardwareId().hashCode();
        result = 31 * result + getRating();
        return result;
    }
}
