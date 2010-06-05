import chatClient.Config;
import chatClient.Connect;
import chatClient.Gui;

import javax.swing.*;

/**
 * User: Piotr Kapera
 * Date: 2010-05-10
 * Time: 19:32:17
 */
public class ClientMain {
    public static void main(String[] args) {
        try {
          UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e) {
          System.out.println("Error setting native LAF: " + e);
        }
        
        Connect connect = new Connect();
        connect.setTitle("Chat");
        connect.pack();
        connect.setVisible(true);

        Gui dialog = new Gui();
        dialog.setTitle("Chat");
        dialog.pack();
        dialog.setVisible(true);
        
        System.exit(0);
    }
}
