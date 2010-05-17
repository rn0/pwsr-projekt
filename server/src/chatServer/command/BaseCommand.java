package chatServer.command;


import chatServer.Server;
import chatServer.Session;

/**
 * User: Piotr Kapera
 * Date: 2010-05-16
 * Time: 12:39:16
 */
public abstract class BaseCommand {

    public BaseCommand() {
        System.out.println("BaseCommand constructor called");
    }

    abstract public void execute(Server server, Session session, String[] params);
    abstract public String getUsage();
}
