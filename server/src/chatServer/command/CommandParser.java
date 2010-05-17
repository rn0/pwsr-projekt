package chatServer.command;

import chatServer.Session;
import chatServer.Utils;
import chatServer.Server;

import java.lang.reflect.Constructor;
import java.util.HashMap;

/**
 * User: Piotr Kapera
 * Date: 2010-05-16
 * Time: 12:54:37
 */
public class CommandParser {
    private static CommandParser instance;
    private HashMap<String, BaseCommand> commands;

    /**
     * Get the global CommandParser instance
     *
     * @return CommandParser
     */
    public static synchronized CommandParser getInstance() {
        if (instance == null) {
            instance = new CommandParser();
        }
        return instance;
    }

    private CommandParser() {
        commands = new HashMap<String, BaseCommand>();
    }

    private String[] extractParams(String line) {
        return line.trim().substring(1).split(" ");
    }

    private BaseCommand prepareCommand(String command) throws Exception {
        BaseCommand cmd = null;

        try {
            if (commands.containsKey(command)) {
                cmd = commands.get(command);
            } else {
                cmd = (BaseCommand) findCommand(command).newInstance();
                commands.put(command, cmd);
            }
        } catch(Exception e) {
            throw e;
        }

        return cmd;
    }

    public void handle(Server server, Session session, String line) throws Exception {
        String[] params = extractParams(line);
        String command = Utils.capitalize(params[0]);

        prepareCommand(command).execute(server, session, params);
    }

    private Constructor findCommand(String command) throws Exception {
        String path = "chatServer.command.handlers." + Utils.capitalize(command);
        Class clazz = Class.forName(path);
        return clazz.getConstructor();
    }
}
