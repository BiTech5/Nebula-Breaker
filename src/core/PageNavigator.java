package src.core;
import javax.swing.*;
import java.awt.*;

public class PageNavigator {

    public static void navigateTo(JPanel container, JPanel newPage) {
        container.removeAll();
        container.setLayout(new BorderLayout());
        container.add(newPage);
        
        if (container instanceof JComponent) {
            ((JComponent) container).revalidate();
            ((JComponent) container).repaint();
        }
    }
}
