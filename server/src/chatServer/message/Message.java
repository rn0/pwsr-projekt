package chatServer.message;

import chatServer.Session;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User: Piotr Kapera
 * Date: 2010-05-17
 * Time: 20:35:55
 */
public class Message {
    protected Session from;
    protected Session to;
    protected String message;
    protected long timestamp;
    protected SimpleDateFormat timestampFormater = new SimpleDateFormat("HH:mm:ss");

    public Message(Session from, Session to, String message) {
        this.from = from;
        this.to = to;
        this.message = message;
        timestamp = new Date().getTime();
    }

    public String toString() {
        return getTextTimestamp() + " " + from + ": " + message;
    }

    public Session getSender() {
        return from;
    }

    public Session getRecipient() {
        return to;
    }

    protected String getTextTimestamp() {
        return "<" + timestampFormater.format(new Date(timestamp)) + ">";
    }
}
