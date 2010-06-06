package chatServer.message;

import chatServer.Channel;
import chatServer.Session;

/**
 * User: Piotr Kapera, Grzegorz Grudzień
 * Date: 2010-05-17
 * Time: 20:42:16
 */
public class Broadcast extends Message {
    /**
     * Domyślny konstruktor
     *
     * @param from Nadawca wiadomości
     * @param to 
     * @param message Wiadomość do wysłania
     */
    public Broadcast(Session from, String message) {
        super(from, null, message);
    }

    @Override
    public String toString() {
        if(from == null)
            return getTextTimestamp() + " " + message;
        else
            return getTextTimestamp() + " " + from + ": " + message;
    }
}
