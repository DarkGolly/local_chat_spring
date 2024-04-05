package danya.chat;

import lombok.Getter;
import lombok.Setter;


public class OutGoingMessage {
    private String from;
    private String message;

    public OutGoingMessage(String from, String message) {
        this.from = from;
        this.message = message;
    }

    public OutGoingMessage() {
    }

    public String getMessage() {
        return message;
    }
    public String getFrom() {
        return from;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public void setFrom(String name) {
        this.from = from;
    }
}
