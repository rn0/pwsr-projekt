package chatClient.Gui;

import chatClient.Config;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleContext;
import java.awt.event.*;
import java.lang.reflect.InvocationTargetException;

public class Gui extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JTabbedPane tabbedPane1;
    private JTextField textField1;
    private JTextPane textPane1;

    private final DefaultStyledDocument doc;
    private StyleContext sc;

    public Gui() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        sc = new StyleContext();
        doc = new DefaultStyledDocument(sc);
        textPane1.setDocument(doc);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

// call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    try {
                        doc.insertString(0, "Podłączony do servera: " + Config.getInstance().getIp(), null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch(Exception e) {
            
        }
    }

    private void onOK() {
// add your code here
        dispose();
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }
}
