/*
 * Created by JFormDesigner on Sun Jun 06 13:40:31 CEST 2010
 */

package chatClient.Gui;

import chatClient.Client;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author unknown
 */
public class Channel extends JPanel {
    private Client client;
    private String channelName;
    
    public Channel(String channelName, Client client) {
        initComponents();

        this.client = client;
        this.channelName = channelName;
        
        setName(channelName);
    }

    private void sendButtonActionPerformed(ActionEvent e) {
        String line = inputTextField.getText();
        if(!line.isEmpty()) {
            if(line.startsWith("/")) {
                
            }
            else {
               String msg = "/msg " + channelName + " " + line;
               System.out.println(msg);
               client.send(msg); 
            }
        }
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
        scrollPane1 = new JScrollPane();
        list1 = new JList();

        //======== this ========

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

        setLayout(new BorderLayout());

        //======== panel3 ========
        {
            panel3.setBackground(new Color(204, 204, 0));
            panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));

            //---- inputTextField ----
            inputTextField.setColumns(21);
            inputTextField.setHorizontalAlignment(SwingConstants.LEFT);
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

            //======== scrollPane2 ========
            {
                scrollPane2.setBorder(null);

                //---- textPane1 ----
                textPane1.setPreferredSize(new Dimension(300, 20));
                textPane1.setBorder(null);
                scrollPane2.setViewportView(textPane1);
            }
            splitPane1.setLeftComponent(scrollPane2);

            //======== scrollPane1 ========
            {
                scrollPane1.setPreferredSize(new Dimension(100, 130));
                scrollPane1.setBorder(null);

                //---- list1 ----
                list1.setBorder(null);
                scrollPane1.setViewportView(list1);
            }
            splitPane1.setRightComponent(scrollPane1);
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
    private JScrollPane scrollPane1;
    private JList list1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
