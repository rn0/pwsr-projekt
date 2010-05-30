package chatServer.command.handlers;

import chatServer.Server;
import chatServer.Session;
import chatServer.SessionPrivileges;
import chatServer.command.BaseCommand;
import chatServer.message.Broadcast;
import chatServer.message.Message;
import chatServer.message.Notice;

/**
 * User: Piotr Kapera
 * Date: 2010-05-30
 * Time: 18:23:04
 */
public class Kick extends BaseCommand {
    @Override
    public void execute(Server server, Session session, String[] params) {
        if((session.getPrivileges() & SessionPrivileges.admin) == SessionPrivileges.admin) {
            Session to = server.findSession(params[1]);
            if(to == null) {
                server.send(new Notice(session, "Unknown recipient"));
            } else {
                server.send(new Notice(to, "Zostałeś wykopany z kanału! :D"));
                server.send(new Broadcast(to, "Zostałem wykopany z kanału!"));
                server.kill(to);
            }
        }
        else {
            server.send(new Notice(session, "Brak wystarczajacych uprawnien"));
        }

    }

    @Override
    public String getUsage() {
        return "/kick <nick>";
    }
}
