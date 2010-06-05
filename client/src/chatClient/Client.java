package chatClient;

import java.io.*;
import java.net.Socket;

/**
 * User: Piotr Kapera
 * Date: 2010-06-05
 * Time: 20:54:40
 */
public class Client extends Thread {
    private BufferedReader in;
    private PrintWriter out;
    private Socket socket;
    private Config config;

    public Client() throws Exception {
        config = Config.getInstance();
        socket = new Socket(config.getIp(), config.getPort());

        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
    }

    @Override
    public void run() {
        try {
            String line = null;
            
            while(true) {
                line = in.readLine();
            }
        }
        catch(Exception e) {
        }
        finally {
            try {
                in.close();
                out.close();
                socket.close();
            }
            catch (IOException e) {
            }
        }
    }
}
