package chatServer;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Vector;

/**
 * User: Piotr Kapera
 * Date: 2010-05-10
 * Time: 20:00:05
 */
public final class Server implements Runnable {
    private int port;
    private final Vector<Session> sessions = new Vector<Session>();

    public void run() {
        try {
            ServerSocket listener = new ServerSocket(port);
            Utils.log("Serwer uruchomiony na porcie: " + port);
            while (true) {
                Socket socket = listener.accept();

                Session session = new Session(socket, this);
                sessions.add(session);
                session.start();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void broadcast(Session from, String message) {
        message = "[ID:" + from.getNick() + "]: " + message;
        Utils.log("Broadcast: " + message);
        synchronized (sessions) {
            for (Session session : sessions) {
                if (!session.equals(from)) {
                    session.send(message);
                }
            }
        }
    }

    public void send(Session from, Session to, String message) {
        message = "[ID:" + from.getNick() + "]: " + message;
        //Session to = findSessionByID(id);
        Utils.log("Message from [ID:" + from.getId() + "] to [ID:" + to.getId() + "]");
        to.send(message);
    }

    public Session findSessionByID(long id) {
        for (Session session : sessions) {
            if (session.getId() == id) {
                return session;
            }
        }
        return null;
    }

    public boolean checkIfNickOccupied(String nick) {
        for (Session session : sessions) {
            if (session.getNick().equals(nick)) {
                return true;
            }
        }
        return false;
    }

    public void kill(Session session) {
        Utils.log("Killed: " + session.getId());
        sessions.remove(session);
    }

    public Vector<Session> getSessions() {
        return sessions;
    }
}
