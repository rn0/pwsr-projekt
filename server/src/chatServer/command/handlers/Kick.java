package chatServer.command.handlers;

import chatServer.*;
import chatServer.command.BaseCommand;
import chatServer.message.Broadcast;
import chatServer.message.Notice;

import java.util.EnumSet;

/**
 * User: Piotr Kapera, Grzegorz Grudzień
 * Date: 2010-05-30
 * Time: 18:23:04
 */
public class Kick extends BaseCommand {
    @Override
    public void execute(Server server, Session session, String[] params) {
        Utils.log("* kick command");
        if(params.length == 2) {
            // kick user :)
            if(session.getModes().get("Server").contains(UserMode.o)) {
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
                EnumSet<UserMode> modes = session.getModes().get(chan.getName());
                if(modes != null && modes.contains(UserMode.o)) {
                    Session sessionToBan = chan.findSession(params[2]);
                    if(sessionToBan == null) {
                        server.send(new Notice(session, "Unknown recipient"));
                    } else {
                        chan.removeSession(sessionToBan);
                        sessionToBan.removeChannel(chan);
                        server.send(new Notice(sessionToBan, "Zostałeś wykopany z kanału " + chan));
                        chan.send(new Broadcast(sessionToBan, "Został wykopany z kanału"));
                    }
                } else {
                    server.send(new Notice(session, "Brak wystarczajacych uprawnien"));
                }
            }
        }
    }

    @Override
    public String getUsage() {
        return "/kick <?channel> <nick>";
    }
}
