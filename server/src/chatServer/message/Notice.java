package chatServer.message;

import chatServer.Session;

/**
 * User: Piotr Kapera
 * Date: 2010-05-17
 * Time: 20:40:39
 */
public class Notice extends Message {
    public Notice(Session to, String message) {
        super(null, to, message);
    }

    @Override
    public String toString() {
        return message;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
