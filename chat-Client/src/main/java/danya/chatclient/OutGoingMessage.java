package danya.chatclient;


public class OutGoingMessage {
    private String from;
    private String message;

    public OutGoingMessage(String message, String name) {
        this.from = name;
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
    public void setFrom(String from) {
        this.from = from;
    }
}
