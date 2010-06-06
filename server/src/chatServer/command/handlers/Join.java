package chatServer.command.handlers;

import chatServer.*;
import chatServer.command.BaseCommand;
import chatServer.message.Broadcast;
import chatServer.message.Notice;

/**
 * User: Piotr Kapera
 * Date: 2010-06-03
 * Time: 17:28:23
 */
public class Join extends BaseCommand {
    @Override
    public void execute(Server server, Session session, String[] params) {
        if(params.length == 2) {
            Channel channel = server.findChannel(params[1]);
            if(channel == null) {
                // TODO: refactor. static validators
                if(params[1].startsWith("#")) {
                    channel = new Channel(params[1]);
                    server.registerChannel(channel);
                    Utils.log("* new channel: " + params[1]);
                }
                else {
                    server.send(new Notice(session, "Invalid channel name"));
                }
            }
            if(channel != null) {
                channel.addSession(session);
                session.addChannel(channel);
                server.send(new Notice(session, "Channel join: " + params[1] + ";"));
                channel.send(new Notice(session, "Users on channel: " + channel.getSessions().toString()));

                if(channel.getSessionsCount() == 1) {
                    Utils.log("Channel " + channel + " administrator ID:" + session);
                    server.send(new Notice(session, "jesteś właścicielem kanału!"));
                    session.getModes().get(channel.getName()).add(UserMode.o);
                }

                channel.send(new Broadcast(session, "joined channel"));
            }
        }
    }

    @Override
    public String getUsage() {
        return "/kick <nick>";
    }
}
