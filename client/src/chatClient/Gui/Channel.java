/*
 * Created by JFormDesigner on Sun Jun 06 13:40:31 CEST 2010
 */

package chatClient.Gui;

import chatClient.Client;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleContext;

/**
 * @author unknown
 */
public class Channel extends JPanel {
    private String channelName;

    private final DefaultStyledDocument doc;
    private StyleContext sc;
    private Client clientThread;
    private SimpleDateFormat timestampFormater = new SimpleDateFormat("HH:mm:ss");
    private DefaultListModel users = new DefaultListModel();
    
    public Channel(String channelName, Client client) {
        initComponents();

        this.clientThread = client;
        this.channelName = channelName;
        list1.setModel(users);
        
        setName(channelName);
        //getRootPane().setDefaultButton(sendButton);

        sc = new StyleContext();
        doc = new DefaultStyledDocument(sc);
        textPane1.setDocument(doc);
    }

    private void sendButtonActionPerformed(ActionEvent e) {
        send();
    }

    private void inputTextFieldKeyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            send();
        }
    }

    private void send() {
        String line = inputTextField.getText();
        if(!line.isEmpty()) {
            addText("ME: <" + timestampFormater.format(new Date()) + "> " + line);
            if(line.startsWith("/")) {
                System.out.println(line);
                clientThread.send(line);
            }
            else {
               String msg;
               if(channelName.equals("server")) {
                   msg = "/msg " + line;
               }
               else {
                   msg = "/msg " + channelName + " " + line;
               }
               System.out.println(msg);
               clientThread.send(msg);
            }
            inputTextField.setText("");
        }
    }

    public void addText(final String text) {
        try {
            if (SwingUtilities.isEventDispatchThread()) {
                insertText(text);
            } else {
                SwingUtilities.invokeAndWait(new Runnable() {
                    public void run() {
                        insertText(text);
                    }
                });
            }
        } catch (Exception e) {

        }
    }

    public void addNewUsers(String[] nicks) {
        int i = 0;
        for(String nick : nicks) {
            if(!users.contains(nick)) {
                users.add(i, nick);
                i++;
            }
        }
    }
    public void removeAllUsers() {
        users.removeAllElements();
    }

    private void insertText(String text) {
        try {
            doc.insertString(doc.getLength(), text + "\n", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void replaceNick(String oldNick, String nick) {
        users.removeElement(oldNick);
        users.add(users.size(), nick);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Piotr Kapera
        panel3 = new JPanel();
        inputTextField = new JTextField();
        sendButton = new JButton();
        splitPane1 = new JSplitPane();
        scrollPane2 = new JScrollPane();
        textPane1 = new JTextPane();
        panel1 = new JPanel();
        label1 = new JLabel();
        scrollPane1 = new JScrollPane();
        list1 = new JList();

        //======== this ========
        setBackground(Color.white);
        setLayout(new BorderLayout());

        //======== panel3 ========
        {
            panel3.setBackground(new Color(204, 204, 0));
            panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));

            //---- inputTextField ----
            inputTextField.setColumns(21);
            inputTextField.setHorizontalAlignment(SwingConstants.LEFT);
            inputTextField.setBorder(new EtchedBorder());
            inputTextField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    inputTextFieldKeyPressed(e);
                }
            });
            panel3.add(inputTextField);

            //---- sendButton ----
            sendButton.setText("Wy\u015blij");
            sendButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    sendButtonActionPerformed(e);
                }
            });
            panel3.add(sendButton);
        }
        add(panel3, BorderLayout.SOUTH);

        //======== splitPane1 ========
        {
            splitPane1.setResizeWeight(0.7);
            splitPane1.setBorder(new EtchedBorder());

            //======== scrollPane2 ========
            {
                scrollPane2.setBorder(null);

                //---- textPane1 ----
                textPane1.setPreferredSize(new Dimension(300, 20));
                textPane1.setBorder(null);
                scrollPane2.setViewportView(textPane1);
            }
            splitPane1.setLeftComponent(scrollPane2);

            //======== panel1 ========
            {
                panel1.setBackground(Color.white);
                panel1.setLayout(new BorderLayout());

                //---- label1 ----
                label1.setText("U\u017cytkownicy:");
                label1.setHorizontalAlignment(SwingConstants.CENTER);
                panel1.add(label1, BorderLayout.NORTH);

                //======== scrollPane1 ========
                {
                    scrollPane1.setPreferredSize(new Dimension(100, 130));
                    scrollPane1.setBorder(null);

                    //---- list1 ----
                    list1.setBorder(null);
                    scrollPane1.setViewportView(list1);
                }
                panel1.add(scrollPane1, BorderLayout.CENTER);
            }
            splitPane1.setRightComponent(panel1);
        }
        add(splitPane1, BorderLayout.CENTER);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Piotr Kapera
    private JPanel panel3;
    private JTextField inputTextField;
    private JButton sendButton;
    private JSplitPane splitPane1;
    private JScrollPane scrollPane2;
    private JTextPane textPane1;
    private JPanel panel1;
    private JLabel label1;
    private JScrollPane scrollPane1;
    private JList list1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
