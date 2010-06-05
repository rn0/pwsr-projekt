package chatClient.Gui;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import java.awt.*;

/**
 * User: Piotr Kapera
 * Date: 2010-06-05
 * Time: 23:00:23
 */
public class CaretUpdater implements CaretListener {
    @Override
    public void caretUpdate(CaretEvent e) {
        /*JTextPane jtp = (JTextPane)e.getSource();

        Highlighter h = jtp.getHighlighter();
        try {
            Document doc = jtp.getDocument();
            String text = doc.getText();
            System.out.println("Carret update: " + text);

            h.addHighlight(text.indexOf("<"), text.indexOf(">") + 1, new DefaultHighlighter.DefaultHighlightPainter(Color.GRAY));
        } catch (BadLocationException e1) {
            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }*/
    }
}
