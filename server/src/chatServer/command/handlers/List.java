package chatServer.command.handlers;

import chatServer.Server;
import chatServer.Session;
import chatServer.command.BaseCommand;
import chatServer.message.Notice;

import java.util.Vector;

/**
 * User: Piotr Kapera
 * Date: 2010-05-17
 * Time: 20:11:59
 */
public class List extends BaseCommand {
    @Override
    public void execute(Server server, Session session, String[] params) {
        Vector<String> names = new Vector<String>();
        for (Session s : server.getSessions()) {
            names.add(s.getNick());
        }
        server.send(new Notice(session, names.toString()));
    }

    @Override
    public String getUsage() {
        return "/list";
    }
}
