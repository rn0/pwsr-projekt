package chatServer.command.handlers;

import chatServer.Server;
import chatServer.Session;
import chatServer.Utils;
import chatServer.command.BaseCommand;
import chatServer.message.Notice;

/**
 * User: Piotr Kapera
 * Date: 2010-05-16
 * Time: 12:40:23
 */
public class Nick extends BaseCommand {

    @Override
    public void execute(Server server, Session session, String[] params) {
        Utils.log("* nick command");
        if(params.length == 2) {
            if(server.checkIfNickOccupied(params[1])) {
                server.send(new Notice(session, "Nick already occupied"));
            } else {
                session.setNick(params[1]);
            }
        }
    }

    @Override
    public String getUsage() {
        return "/nick <nickname>";
    }
}
