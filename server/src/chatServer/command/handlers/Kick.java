package chatServer.command.handlers;

import chatServer.*;
import chatServer.command.BaseCommand;
import chatServer.message.Broadcast;
import chatServer.message.Notice;

/**
 * User: Piotr Kapera
 * Date: 2010-05-30
 * Time: 18:23:04
 */
public class Kick extends BaseCommand {
    @Override
    public void execute(Server server, Session session, String[] params) {
        Utils.log("* kick command");
        if(params.length == 2) {
            // kick user :)
            if(session.getModes().contains(UserMode.o)) {
                Session sessionToBan = server.findSession(params[1]);
                if(sessionToBan == null) {
                    server.send(new Notice(sessionToBan, "Unknown recipient"));
                } else {
                    server.send(new Notice(sessionToBan, "Zostałeś wykopany z serwera! :D"));
                    server.kill(sessionToBan);
                }
            } else {
                server.send(new Notice(session, "Brak wystarczajacych uprawnien"));
            }
        }
        else if(params.length == 3) {
            // kick usera z kanału
            Channel chan = server.findChannel(params[1]);
            if(chan == null) {
                server.send(new Notice(session, "Unknown channel"));
            } else {
                Session sessionToBan = server.findSession(params[2]);
                if(sessionToBan == null) {
                    server.send(new Notice(session, "Unknown recipient"));
                } else {
                    chan.removeSession(sessionToBan);
                    server.send(new Notice(sessionToBan, "Zostałeś wykopany z kanału! :D"));
                }
            }
        }
    }

    @Override
    public String getUsage() {
        return "/kick <?channel> <nick>";
    }
}
