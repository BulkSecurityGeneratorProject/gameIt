package mk.gameIt.web.dto;

/**
 * Created by Stefan on 16.04.2016.
 */
public class KeyAndPasswordObject {
    private String key;
    private String newPassword;

    public KeyAndPasswordObject() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
