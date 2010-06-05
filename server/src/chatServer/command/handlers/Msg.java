package chatServer.command.handlers;

import chatServer.Channel;
import chatServer.Server;
import chatServer.Session;
import chatServer.Utils;
import chatServer.command.BaseCommand;
import chatServer.message.Broadcast;
import chatServer.message.Message;
import chatServer.message.Notice;

/**
 * User: Piotr Kapera
 * Date: 2010-05-16
 * Time: 12:41:59
 */
public class Msg extends BaseCommand {
    @Override
    public void execute(Server server, Session session, String[] params) {
        Utils.log("* msg command");

        //if(params.length == 2) {
        //    server.send(new Broadcast(session, params[1]));
        //} else {
            Session to = server.findSession(params[1]);
            if(to == null) {
                Channel channel = server.findChannel(params[1]);
                if(channel == null) {
                    server.send(new Notice(session, "Unknown recipient"));
                }
                else {
                    channel.send(new Broadcast(session, params[2]));
                }
            } else {
                server.send(new Message(session, to, params[2]));
            }
        //}
    }

    @Override
    public String getUsage() {
        return "/msg <target> <message>";
    }
}
