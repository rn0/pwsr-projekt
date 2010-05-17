package chatServer.command;


import chatServer.Server;
import chatServer.Session;

/**
 * User: Piotr Kapera
 * Date: 2010-05-16
 * Time: 12:39:16
 */
public abstract class BaseCommand {

    /**
     * Wykonuje komendę
     *
     * @param server
     * @param session
     * @param params
     */
    abstract public void execute(Server server, Session session, String[] params);

    /**
     * Zwraca łańcuch znaków z pomocą dotyczącą użytkownia komendy
     *
     * @return
     */
    abstract public String getUsage();
}
