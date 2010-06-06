package chatClient;

import java.util.HashMap;

/**
 * User: Piotr Kapera, Grzegorz Grudzień
 * Date: 2010-06-05
 * Time: 20:01:55
 */
public class Config {
    private static Config instance;
    private String ip;
    private int port;
    
    /**
     * Get the global CommandParser instance
     *
     * @return CommandParser
     */
    public static synchronized Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        System.out.println("set ip");
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
