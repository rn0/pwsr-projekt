package chatServer.command.handlers;

import chatServer.Server;
import chatServer.Session;
import chatServer.Utils;
import chatServer.command.BaseCommand;

/**
 * User: Piotr Kapera
 * Date: 2010-05-16
 * Time: 12:41:59
 */
public class Msg extends BaseCommand {
    @Override
    public void execute(Server server, Session session, String[] params) {
        Utils.log("* msg command");

        if(params.length == 2) {
            server.broadcast(session, params[1]);
        } else {
            Session to = server.findSessionByID(Long.parseLong(params[1]));
            if(to == null) {
                session.notify("Unknown recipient");
            } else {
                server.send(session, to, params[2]);
            }
        }
    }

    @Override
    public String getUsage() {
        return "/msg <target> <message>";
    }
}
