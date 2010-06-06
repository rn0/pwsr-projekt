package chatClient;

import chatClient.Gui.Channel;
import chatClient.Gui.ChatFrame;

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
    private final ChatFrame chatFrame;

    private String ip;
    private int port;

    public Client(ChatFrame chatFrame) throws Exception {
        this.chatFrame = chatFrame;
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
            chatFrame.markConnected();
            
            while(true) {
                line = in.readLine();
                System.out.println(line);
                
                if(line.substring(19).startsWith("Channel join:")) {
                    Pattern p = Pattern.compile("Channel join: (#[0-9a-zA-Z]+)");
                    Matcher m = p.matcher(line);
                    if(m.find()) {
                        String channelName = m.group(1);
                        //String users = m.group(1);
                        chatFrame.registerNewChannel(new Channel(channelName, this));
                        System.out.println("new tab! " + channelName);
                    }
                }
                else if(line.substring(19).startsWith("Nick changed to:")) {
                    Pattern p = Pattern.compile("Nick changed to: ([0-9a-zA-Z]+)");
                    Matcher m = p.matcher(line);
                    if(m.find()) {
                        String nick = m.group(1);
                        chatFrame.setNewNick(nick);
                    }
                }
                
                String[] parts = line.split(" ");
                String prefix = parts[0].substring(0, parts[0].length() - 1);
                chatFrame.getChannel(prefix).addText(line);
            }
        }
        catch(Exception e) {
            chatFrame.getChannel("Server").addText(e.toString());
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
                chatFrame.getChannel("Server").addText(e.toString());
            }
            chatFrame.markDisconnected();
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
