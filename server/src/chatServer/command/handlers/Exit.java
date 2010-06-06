package chatServer.command.handlers;

import chatServer.Server;
import chatServer.Session;
import chatServer.Utils;
import chatServer.command.BaseCommand;
import chatServer.message.Notice;

/**
 * User: Piotr Kapera
 * Date: 2010-06-06
 * Time: 16:50:26
 */
public class Exit extends BaseCommand {
    @Override
    public void execute(Server server, Session session, String[] params) {
        Utils.log("* exit msg: " + session);
        server.send(new Notice(session, "Rozłączony!"));
        server.kill(session);
    }

    @Override
    public String getUsage() {
        return "/exit";
    }
}