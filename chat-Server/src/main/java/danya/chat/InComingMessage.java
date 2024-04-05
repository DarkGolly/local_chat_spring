package danya.chat;
import lombok.Getter;
import lombok.Setter;


public class InComingMessage {
    private String from;
    private String message;

    public String getMessage() {
        return message;
    }
    public String getFrom() {
        return from;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public void setFrom(String from) {
        this.from = from;
    }

    public InComingMessage(String message) {
        this.message = message;
    }

    public InComingMessage() {
    }
}
