package chatServer.message;

import chatServer.Session;

/**
 * User: Piotr Kapera, Grzegorz Grudzień
 * Date: 2010-06-06
 * Time: 20:46:01
 */

public class ServerNotice extends Message {
    /**
     * Domyślny konstruktor
     *
     * @param from Nadawca wiadomości
     * @param to
     * @param message Wiadomość do wysłania
     */
    public ServerNotice(Session from, String message) {
        super(from, null, message);
    }

    @Override
    public String toString() {
        return getTextTimestamp() + " " + message;
    }
}
