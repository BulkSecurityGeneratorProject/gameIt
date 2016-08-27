package mk.gameIt.web.exceptions;

/**
 * Created by Stefan on 27.8.2016.
 */
public class UsernameExistsException extends Exception {
    public UsernameExistsException() {
        super("username is already in use");
    }

    public UsernameExistsException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "username is already in use";
    }
}
