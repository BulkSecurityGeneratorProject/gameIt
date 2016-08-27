package mk.gameIt.web.dto;

/**
 * Created by Stefan on 27.8.2016.
 */
public class ExceptionObject {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ExceptionObject() {
        this.message = "exception";
    }

    public ExceptionObject(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ExceptionObject{" +
                "message='" + message + '\'' +
                '}';
    }
}
