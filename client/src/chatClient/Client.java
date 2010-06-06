package chatClient;

import chatClient.Gui.Channel;
import chatClient.Gui.Main;

import java.io.*;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: Piotr Kapera
 * Date: 2010-06-05
 * Time: 20:54:40
 */
public class Client extends Thread {
    private BufferedReader in;
    private PrintWriter out;
    private Socket socket;
    private final Main main;

    private String ip;
    private int port;

    public Client(Main main) throws Exception {
        this.main = main;
        System.out.println("construct Client thread");
    }

    @Override
    public void run() {
        System.out.println("run Client thread");
        try {
         
            if(ip.isEmpty()) {
                throw new Exception("Invalid ip");
            }
            socket = new Socket(ip, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

            String line = null;

            this.main.markConnected();
            
            while(true) {
                line = in.readLine();
                if(line.substring(19).startsWith("Channel join: ")) {
                    Pattern p = Pattern.compile( "(#[0-9a-zA-Z]+)" );
                    Matcher m = p.matcher(line);
                    if(m.find()) {
                        String channelName = m.group(0);
                        //String users = m.group(1);
                        this.main.addNewTab(new Channel(channelName, this));
                        System.out.println("new tab! " + channelName);
                    }
                }
                System.out.println(line);
                this.main.addText(line);
            }
        }
        catch(Exception e) {
            this.main.addText(e.toString());
        }
        finally {
            try {
                if(in != null)
                    in.close();
                if(out != null)
                    out.close();
                if(socket != null)
                    socket.close();
            }
            catch (IOException e) {
                this.main.addText(e.toString());
            }
            this.main.markDisconnected();
        }
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void send(String text) {
        if(!text.isEmpty()) {
            out.println(text);
        }
    }
}
