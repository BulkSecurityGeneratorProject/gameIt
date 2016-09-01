package mk.gameIt.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Email;
import org.springframework.security.crypto.codec.Base64;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @JsonIgnore
    @Column(name = "passwordHash")
    private String passwordHash;

    @Column(name="firstName")
    private String firstName;

    @Column(name="lastName")
    private String lastName;

    private String langKey;

    @JsonIgnore
    private Blob profileImage;

    @Enumerated(EnumType.STRING)
    public Provider provider = Provider.LOCAL;

    @Enumerated(EnumType.STRING)
    public Role role;

    @OneToMany(mappedBy = "userSeller", cascade = CascadeType.ALL)
    private Set<Game> sellingGames;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<UserGameOrder> userGameOrders = new HashSet<UserGameOrder>(0);

    @JsonIgnore
    @OneToMany(mappedBy = "userId", cascade = CascadeType.REMOVE)
    private List<CommentGame> commentsGame;

    @JsonIgnore
    @OneToMany(mappedBy = "userId", cascade = CascadeType.REMOVE)
    private List<GameRating> gamesRatings;

    @JsonIgnore
    @OneToMany(mappedBy = "userId", cascade = CascadeType.REMOVE)
    private List<Location> locations;

    public User() {
       langKey = LangKey.en.name();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return username.equals(user.username);

    }

    public Set<UserGameOrder> getUserGameOrders() {
        return userGameOrders;
    }

    public void setUserGameOrders(Set<UserGameOrder> userGameOrders) {
        this.userGameOrders = userGameOrders;
    }


    public Set<Game> getSellingGames() {
        return sellingGames;
    }

    public void setSellingGames(Set<Game> sellingGames) {
        this.sellingGames = sellingGames;
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

    public String getLangKey() {
        return langKey;
    }

    public void setLangKey(String langKey) {
        this.langKey = langKey;
    }

    public Blob getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(Blob profileImage) {
        this.profileImage = profileImage;
    }

    public List<CommentGame> getCommentsGame() {
        return commentsGame;
    }

    public void setCommentsGame(List<CommentGame> commentsGame) {
        this.commentsGame = commentsGame;
    }

    public List<GameRating> getGamesRatings() {
        return gamesRatings;
    }

    public void setGamesRatings(List<GameRating> gamesRatings) {
        this.gamesRatings = gamesRatings;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public User(String username, String email, String passwordHash, String firstName, String lastName, boolean activated, boolean admin) {
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.firstName = firstName;
        this.lastName = lastName;
        this.langKey = LangKey.en.name();
        this.commentsGame = new ArrayList<>();
        this.gamesRatings = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", langKey='" + langKey + '\'' +
                ", profileImage=" + getUserProfileImageToString() +
                ", provider=" + provider +
                ", role=" + role +
                '}';
    }

    public String getUserProfileImageToString()  {
        String base64Encoded = null;
        if (this.getProfileImage() != null) {
            byte[] encodeBase64 = new byte[0];
            try {
                encodeBase64 = Base64.encode(this.getProfileImage().getBytes(1, (int) this.getProfileImage().length()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                base64Encoded = new String(encodeBase64, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return base64Encoded;
    }
}

