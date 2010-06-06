package chatServer.command.handlers;

import chatServer.Channel;
import chatServer.Server;
import chatServer.Session;
import chatServer.command.BaseCommand;
import chatServer.message.Broadcast;
import chatServer.message.Notice;

/**
 * User: Piotr Kapera
 * Date: 2010-06-06
 * Time: 18:46:45
 */
public class Part extends BaseCommand {
    @Override
    public void execute(Server server, Session session, String[] params) {
        if(params.length == 2) {
            Channel chan = server.findChannel(params[1]);
            if(chan == null) {
                server.send(new Notice(session, "Unknown recipient"));
            }
            else {
                chan.removeSession(session);
                session.removeChannel(chan);
                server.send(new Notice(session, "Opuściłeś kanał: " + chan));
                chan.send(new Broadcast(session, "Opuścił kanał"));
            }
        }
    }

    @Override
    public String getUsage() {
        return "/part <kanał>";
    }
}
