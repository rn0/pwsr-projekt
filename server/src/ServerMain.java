import chatServer.Server;

/**
 * User: Piotr Kapera, Grzegorz Grudzień
 * Date: 2010-05-10
 * Time: 19:29:44
 */
public class ServerMain {
    public static void main(String[] args) {
        Server server = new Server();
        server.setPort(2345);
        new Thread(server).run();
    }
}
