package chatServer;

import chatServer.message.Broadcast;
import chatServer.message.Message;
import chatServer.message.Notice;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

/**
 * User: Piotr Kapera
 * Date: 2010-05-10
 * Time: 20:00:05
 */
public final class Server implements Runnable {
    private int port;
    private final Vector<Session> sessions = new Vector<Session>();

    /**
     *
     */
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

    /**
     * 
     * @return
     */
    public int getPort() {
        return port;
    }

    /**
     * 
     * @param port
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * 
     * @param msg
     */
    public void send(Message msg) {
        if(msg instanceof Broadcast) {
            synchronized (sessions) {
                for (Session session : sessions) {
                    if (!session.equals(msg.getSender())) {
                        session.send(msg.toString());
                    }
                }
            }
        }
        else if(msg instanceof Notice) {
            msg.getRecipient().send(msg.toString());
        }
    }

    /**
     * 
     * @param id
     * @return
     */
    public Session findSessionByID(long id) {
        for (Session session : sessions) {
            if (session.getId() == id) {
                return session;
            }
        }
        return null;
    }

    /**
     * 
     * @param nick
     * @return
     */
    public boolean checkIfNickOccupied(String nick) {
        for (Session session : sessions) {
            if (session.getNick().equals(nick)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 
     * @param session
     */
    public void kill(Session session) {
        Utils.log("Killed: " + session);
        sessions.remove(session);
    }

    /**
     * 
     * @return
     */
    public Vector<Session> getSessions() {
        return sessions;
    }

    /**
     * 
     * @return number of active sessions
     */
    public int getSessionsCount() {
        return sessions.size();
    }
}
