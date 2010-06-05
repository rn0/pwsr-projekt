package chatServer.command.handlers;

import chatServer.Channel;
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
public class Names extends BaseCommand {
    @Override
    public void execute(Server server, Session session, String[] params) {
        Vector<String> names = new Vector<String>();
        for (Session s : server.getSessions()) {
            names.add(s.getNick());
        }
        if(params.length == 1) {
            server.send(new Notice(session, server.getSessions().toString()));
        }
        else if(params.length == 2) {
            Channel chan = server.findChannel(params[1]);
            if(chan == null) {
                server.send(new Notice(session, "Unknown channel: " + params[1]));
            }
            else {
                server.send(new Notice(session, server.getSessions(chan).toString()));
            }
        }

    }

    @Override
    public String getUsage() {
        return "/names";
    }
}
