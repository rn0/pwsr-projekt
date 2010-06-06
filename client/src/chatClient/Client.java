package chatClient;

import chatClient.Gui.Channel;
import chatClient.Gui.ChatFrame;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: Piotr Kapera, Grzegorz Grudzień
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

    private volatile boolean running = false;
    private Vector<String> users = new Vector<String>();

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
            running = true;            
            
            while(running) {
                line = in.readLine();
                if(line == null || line.isEmpty()) {
                    running = false;
                    return;
                }
                System.out.println(line);
                
                if(line.substring(19).startsWith("Channel join:")) {
                    Pattern p = Pattern.compile("Channel join: (#[0-9a-zA-Z]+); Users on channel: \\[([{}, 0-9a-zA-Z]+)\\]");
                    Matcher m = p.matcher(line);
                    if(m.find()) {
                        String channelName = m.group(1);
                        String usersStr = m.group(2);
                        String[] u = parseUserList(usersStr);
                        Channel chan = new Channel(channelName, this);
                        chan.addNewUsers(u);
                        chatFrame.registerChannel(chan);
                        System.out.println("new tab! " + channelName);
                    }
                }
                else if(line.substring(19).startsWith("Your nick:")) {
                    Pattern p = Pattern.compile("Your nick: ([0-9a-zA-Z]+);");
                    Matcher m = p.matcher(line);
                    if(m.find()) {
                        String nick = m.group(1);
                        users.add(nick);
                        chatFrame.setNewNick(null, nick);
                    }
                }
                else if(line.substring(19).startsWith("Nick changed from:")) {
                    Pattern p = Pattern.compile("Nick changed from: ([0-9a-zA-Z]+) to: ([0-9a-zA-Z]+)");
                    Matcher m = p.matcher(line);
                    if(m.find()) {
                        String nick = m.group(2);
                        String oldNick = m.group(1);
                        users.remove(oldNick);
                        users.add(nick);
                        chatFrame.setNewNick(oldNick, nick);
                        chatFrame.replaceNick(oldNick, nick);
                    }
                }
                else if(line.substring(25).startsWith("Nick changed from:")) {
                    Pattern p = Pattern.compile("Nick changed from: ([0-9a-zA-Z]+) to: ([0-9a-zA-Z]+)");
                    Matcher m = p.matcher(line);
                    if(m.find()) {
                        String nick = m.group(2);
                        String oldNick = m.group(1);
                        users.remove(oldNick);
                        users.add(nick);
                        chatFrame.replaceNick(oldNick, nick);
                    }
                }
                else if(line.substring(25).startsWith("Users on channel:")) {
                    chatFrame.getChannel("Server").addText(line);
                    Pattern p = Pattern.compile("Users on channel: (#[0-9a-zA-Z]+) -> \\[([{}, 0-9a-zA-Z]+)\\]");
                    Matcher m = p.matcher(line);
                    if(m.find()) {
                        String channel = m.group(1);
                        Channel chan = chatFrame.getChannel(channel);
                        if(chan != null) {
                            String[] u = parseUserList(m.group(2));
                            chan.removeAllUsers();
                            chan.addNewUsers(u);
                        }
                    }
                }

                String[] parts = line.split(" ");
                String prefix = parts[0].substring(0, parts[0].length() - 1);
                if(prefix.equals("ServerNotice")) {
                    //prefix = "Server";
                }
                else {
                    chatFrame.getChannel(prefix).addText(line);
                }
            }
        }
        catch(Exception e) {
            chatFrame.getChannel("Server").addText(e.getMessage());
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
                chatFrame.getChannel("Server").addText(e.getMessage());
            }
            running = false;
            chatFrame.markDisconnected();
        }
    }

    private String[] parseUserList(String usersStr) {
        ArrayList<String> cleanUsers = new ArrayList<String>();
        String[] usersArr = usersStr.split(", ");

        for(String nick : usersArr) {
            String newNick = nick.substring(1, nick.length() - 1);
            cleanUsers.add(newNick);
        }
        return cleanUsers.toArray(new String[]{});
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

    public void requestClose() {
        send("/exit");
    }

    public boolean isRunning() {
        return running;
    }
}
