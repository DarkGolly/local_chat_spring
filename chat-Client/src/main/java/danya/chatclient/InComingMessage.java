package danya.chatclient;


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

    public InComingMessage(String name, String message) {
        this.message = message;
        this.from = name;
    }

    public InComingMessage() {
    }
}
