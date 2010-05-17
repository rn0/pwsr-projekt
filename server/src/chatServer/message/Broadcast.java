package chatServer.message;

import chatServer.Session;

/**
 * User: Piotr Kapera
 * Date: 2010-05-17
 * Time: 20:42:16
 */
public class Broadcast extends Message {
    public Broadcast(Session from, String message) {
        super(from, null, message);
    }

    @Override
    public String toString() {
        return "" + from + ": " + message;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
