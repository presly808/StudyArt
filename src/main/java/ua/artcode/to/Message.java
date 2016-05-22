package ua.artcode.to;

/**
 * Created by serhii on 22.05.16.
 */
public class Message {

    private String title;
    private MessageType messageType;
    private String message;

    public Message() {
    }

    public Message(String title, MessageType messageType, String message) {
        this.title = title;
        this.messageType = messageType;
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Message{" +
                "title='" + title + '\'' +
                ", messageType=" + messageType +
                ", message='" + message + '\'' +
                '}';
    }
}
