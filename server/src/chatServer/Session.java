package chatServer;

import chatServer.command.CommandParser;
import java.io.*;
import java.net.Socket;

/**
 * User: Piotr Kapera
 * Date: 2010-05-10
 * Time: 19:42:14
 */
public class Session extends Thread {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private Server server;
    private long id;
    private String nick = "";
    private boolean closed = false;

    public Session(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
        id = Utils.crc32(socket.toString());
        
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Utils.log("Nowa sesja! ID:" + this + " (" + socket + ")");
    }

    public void run() {
        try {
            out.println("ID:" + getId() + " joined chat!");
            //String message = "";
            while (!closed) {
                String message = in.readLine();
                if(message == null || message.isEmpty()) {
                    closed = true;
                }
                else {
                    try {
                        CommandParser.getInstance().handle(server, this, message);
                    } catch(Exception e) {
                        Utils.log(e);
                    }
                    //server.broadcast(this, message);
                }
            }
        } catch (Exception e) {
            Utils.log(e);
        } finally {
            out.close();
            try {
                in.close();
                socket.close();
            } catch (IOException e) {
                Utils.log(e);
            }

            server.kill(this);
            Utils.log("Połączenie zostało zakończone: " + this);
        }
    }

    public void send(String message) {
        out.println(message);
    }

    /*public void notify(String message) {
        send(message);
    }*/

    public long getId() {
        return id;
    }

    public String getNick() {
        return nick.isEmpty() ? Long.toString(getId()) : nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    /**
     * Returns a string representation of this session
     *
     * @return a string representation of this session.
     */
    @Override
    public String toString() {
        return "{" + getNick() + "}";
    }
}