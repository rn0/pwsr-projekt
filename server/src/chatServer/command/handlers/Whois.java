package chatServer.command.handlers;

import chatServer.Server;
import chatServer.Session;
import chatServer.command.BaseCommand;
import chatServer.message.Notice;

/**
 * User: Piotr Kapera, Grzegorz Grudzień
 * Date: 2010-06-06
 * Time: 00:42:17
 */
public class Whois extends BaseCommand {
    @Override
    public void execute(Server server, Session session, String[] params) {
        if(params.length == 2) {
            Session about = server.findSession(params[1]);
            if(about == null) {
                server.send(new Notice(session, "Unknown user: " + params[1]));
            }
            else {
                server.send(new Notice(session, "Info about: " + about.getNick() + "; Channels: " + about.getChannels().toString()));
            }
        }
    }

    @Override
    public String getUsage() {
        return "/names";
    }
}
