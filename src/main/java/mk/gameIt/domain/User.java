package mk.gameIt.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Stefan on 24.03.2016.
 */
@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotNull
    @Column(unique = true, name = "username")
    private String username;

    @Email
    @Column(unique = true, name="email")
    private String email;

    @Column(name = "passwordHash")
    private String passwordHash;

    @Column(name="firstName")
    private String firstName;

    @Column(name="lastName")
    private String lastName;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean active = true;

    @Enumerated(EnumType.STRING)
    public Provider provider = Provider.LOCAL;

    @Enumerated(EnumType.STRING)
    public Role role;


    @JsonIgnore
    @OneToMany(mappedBy = "userId")
    private List<CommentGame> commentsGame;

    @JsonIgnore
    @OneToMany(mappedBy = "userId")
    private List<CommentHardwareProduct> commentsHardware;

    @OneToMany(mappedBy = "userId")
    private List<GameRating> gamesRatings;

    @JsonIgnore
    @OneToMany(mappedBy = "userId")
    private List<HardwareProductRating> hardwareProductRatings;

    public User() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return username.equals(user.username);

    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }

    public Provider getProvider() {
        return provider;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


    public User(String username, String email, String passwordHash, String firstName, String lastName, boolean active, boolean admin) {
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.firstName = firstName;
        this.lastName = lastName;
        this.active = active;
    }
}

