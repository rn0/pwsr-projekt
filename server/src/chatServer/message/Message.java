package chatServer.message;

import chatServer.Session;

/**
 * User: Piotr Kapera
 * Date: 2010-05-17
 * Time: 20:35:55
 */
public class Message {
    protected Session from;
    protected Session to;
    protected String message;

    public Message(Session from, Session to, String message) {
        this.from = from;
        this.to = to;
        this.message = message;
    }

    public String toString() {
        return "" + from + ": " + message;
    }

    public Session getSender() {
        return from;
    }

    public Session getRecipient() {
        return to;
    }
}
