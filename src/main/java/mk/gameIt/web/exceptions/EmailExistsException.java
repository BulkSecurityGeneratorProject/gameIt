package mk.gameIt.web.exceptions;

/**
 * Created by Stefan on 27.8.2016.
 */
public class EmailExistsException extends Exception {
    public EmailExistsException(String message) {
        super(message);
    }

    public EmailExistsException() {
        super("e-mail is already in use");
    }

    @Override
    public String toString() {
        return "e-mail is already in use";
    }
}
