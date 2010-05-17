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

    /**
     * Domyślny konstruktor
     * 
     * @param from Nadawca wiadomości
     * @param to Odbiorca wiadomości
     * @param message Wiadomość do wysłania
     */
    public Message(Session from, Session to, String message) {
        this.from = from;
        this.to = to;
        this.message = message;
        timestamp = new Date().getTime();
    }

    /**
     * 
     * @return Sformatowana wiadomość
     */
    public String toString() {
        return getTextTimestamp() + " " + from + ": " + message;
    }

    /**
     * 
     * @return
     */
    public Session getSender() {
        return from;
    }

    /**
     * 
     * @return
     */
    public Session getRecipient() {
        return to;
    }

    /**
     * 
     * @return
     */
    protected String getTextTimestamp() {
        return "<" + timestampFormater.format(new Date(timestamp)) + ">";
    }
}
