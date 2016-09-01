package mk.gameIt.domain;

import com.stripe.model.Charge;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Created by Stefan on 30.8.2016.
 */
@Entity
@Table
public class UserGameOrder implements java.io.Serializable {

    @ManyToOne
    @NotNull
    private User user;

    @ManyToOne
    private Game game;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private long createdDate;

    @Column(nullable = false)
    private String stripeOrderId;

    public UserGameOrder() {
        createdDate = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
    }

    public String getStripeOrderId() {
        return stripeOrderId;
    }

    public void setStripeOrderId(String stripeOrderId) {
        this.stripeOrderId = stripeOrderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserGameOrder that = (UserGameOrder) o;

        return orderId != null ? orderId.equals(that.orderId) : that.orderId == null;

    }

    @Override
    public int hashCode() {
        return orderId != null ? orderId.hashCode() : 0;
    }
}
