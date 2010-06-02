package chatServer.command.handlers;

import chatServer.ChannelMode;
import chatServer.Server;
import chatServer.Session;
import chatServer.Utils;
import chatServer.command.BaseCommand;

/**
 * User: Piotr Kapera
 * Date: 2010-06-02
 * Time: 21:16:40
 */
public class Mode extends BaseCommand {
    @Override
    public void execute(Server server, Session session, String[] params) {
        Utils.log("* mode command");
    }

    @Override
    public String getUsage() {
        return "/mode";
    }

   private boolean isValidModeSymbol(String symbol) {
       ChannelMode[] allModes = ChannelMode.values();
       for (ChannelMode mode : allModes) {
           if (mode.toString().equals(symbol)) {
               return true;
           }
       }
       return false;
   }
}
