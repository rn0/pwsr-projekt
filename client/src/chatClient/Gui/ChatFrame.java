/*
 * Created by JFormDesigner on Sun Jun 06 15:46:19 CEST 2010
 */

package chatClient.Gui;

import chatClient.Client;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import javax.swing.*;

/**
 * @author Piotr Kapera
 */
public class ChatFrame extends JFrame {
    private Client clientThread;
    private HashMap<String, Channel> channels = new HashMap<String, Channel>();

    public ChatFrame() {
        initComponents();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            clientThread = new Client(this);
            Channel serverChannel = new Channel("Server", clientThread);
            registerNewChannel(serverChannel);
        }
        catch(Exception e) {
            channels.get("Server").addText(e.toString());
        }
    }

    private void connectDisconnectButtonActionPerformed(ActionEvent e) {
        clientThread.setIp(ipAddressTextField.getText());
        clientThread.setPort(Integer.parseInt(portTextField.getText()));
        clientThread.start();
    }

    public void markConnected() {
        ipAddressTextField.setEnabled(false);
        portTextField.setEnabled(false);
        connectDisconnectButton.setText("Rozłącz");
    }

    public void markDisconnected() {
        ipAddressTextField.setEnabled(true);
        portTextField.setEnabled(true);
        connectDisconnectButton.setText("Połącz");
    }

    public void registerNewChannel(Channel tab) {
        tabbedPane1.addTab(tab.getName(), tab);
        channels.put(tab.getName(), tab);
    }

    public Channel getChannel(String name) {
        return channels.get(name);
    }

    public void setNewNick(String nick) {
        nickLabel.setText(nick);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Piotr Kapera
        panel1 = new JPanel();
        label1 = new JLabel();
        ipAddressTextField = new JTextField();
        label2 = new JLabel();
        portTextField = new JTextField();
        connectDisconnectButton = new JButton();
        label4 = new JLabel();
        nickLabel = new JLabel();
        panel2 = new JPanel();
        label3 = new JLabel();
        panel3 = new JPanel();
        tabbedPane1 = new JTabbedPane();

        //======== this ========
        setTitle("Chat");
        setMinimumSize(new Dimension(666, 450));
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout(0, 5));

        //======== panel1 ========
        {
            panel1.setLayout(new FlowLayout(FlowLayout.LEFT));

            //---- label1 ----
            label1.setText("IP:");
            panel1.add(label1);

            //---- ipAddressTextField ----
            ipAddressTextField.setText("127.0.0.1");
            panel1.add(ipAddressTextField);

            //---- label2 ----
            label2.setText("Port:");
            panel1.add(label2);

            //---- portTextField ----
            portTextField.setText("2345");
            panel1.add(portTextField);

            //---- connectDisconnectButton ----
            connectDisconnectButton.setText("Po\u0142\u0105cz");
            connectDisconnectButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    connectDisconnectButtonActionPerformed(e);
                }
            });
            panel1.add(connectDisconnectButton);

            //---- label4 ----
            label4.setText("Tw\u00f3j nick:");
            panel1.add(label4);

            //---- nickLabel ----
            nickLabel.setText("---");
            nickLabel.setFocusTraversalPolicyProvider(true);
            nickLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
            panel1.add(nickLabel);
        }
        contentPane.add(panel1, BorderLayout.NORTH);

        //======== panel2 ========
        {
            panel2.setLayout(new FlowLayout(FlowLayout.LEFT));

            //---- label3 ----
            label3.setText("Piotr Kapera, Grzegorz Grudzie\u0144");
            label3.setForeground(Color.lightGray);
            panel2.add(label3);
        }
        contentPane.add(panel2, BorderLayout.SOUTH);

        //======== panel3 ========
        {
            panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));
            panel3.add(tabbedPane1);
        }
        contentPane.add(panel3, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Piotr Kapera
    private JPanel panel1;
    private JLabel label1;
    private JTextField ipAddressTextField;
    private JLabel label2;
    private JTextField portTextField;
    private JButton connectDisconnectButton;
    private JLabel label4;
    private JLabel nickLabel;
    private JPanel panel2;
    private JLabel label3;
    private JPanel panel3;
    private JTabbedPane tabbedPane1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
