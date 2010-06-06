package chatClient.Gui;

import chatClient.Client;

import javax.swing.*;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.*;
import java.nio.channels.Channels;
import java.util.HashMap;

public class Main extends JDialog {
    private JPanel contentPane;
    private JTabbedPane tabbedPane1;
    private JTextField ipAddressTextField;
    private JTextField portTextField;
    private JButton buttonConnect;
    private JButton buttonDisconnect;

    private Client clientThread;
    private HashMap<String, Channel> channels = new HashMap<String, Channel>();

    public Main() {
        setContentPane(contentPane);
        setModal(true);

        try {
            clientThread = new Client(this);
            Channel serverChannel = new Channel("Server", clientThread);
            registerNewChannel(serverChannel);
        }
        catch(Exception e) {
            channels.get("Server").addText(e.toString());
        }

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        
        buttonConnect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clientThread.setIp(ipAddressTextField.getText());
                clientThread.setPort(Integer.parseInt(portTextField.getText()));
                clientThread.start();
            }
        });
        buttonDisconnect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                markDisconnected();
            }
        });
    }

    private void onOK() {
        dispose();
    }

    private void onCancel() {
        dispose();
    }

    public void markConnected() {
        ipAddressTextField.setEnabled(false);
        portTextField.setEnabled(false);
        buttonConnect.setEnabled(false);
        buttonDisconnect.setEnabled(true);
    }

    public void markDisconnected() {
        ipAddressTextField.setEnabled(true);
        portTextField.setEnabled(true);
        buttonConnect.setEnabled(true);
        buttonDisconnect.setEnabled(false);
    }

    public void registerNewChannel(Channel tab) {
        tabbedPane1.addTab(tab.getName(), tab);
        channels.put(tab.getName(), tab);
    }

    public Channel getChannel(String name) {
        return channels.get(name);
    }
}
