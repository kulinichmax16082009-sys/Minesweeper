package windows;

import javax.swing.*;
import java.awt.*;

/**
 * BasicWindow abstract class is used as parent of all windows in projects. Provides starting window settings.
 *
 * @author Maksym Kulynych
 */
public abstract class BasicWindow {
    protected JFrame frame;

    public BasicWindow(String title) {
        frame = new JFrame(title);

        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setFocusable(true);
        frame.setIconImage(getImageIcon());
    }

    /**
     * This method is used for showing error message to user if system has troubles
     * @param message text that will be shown if error
     */
    public static void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Minesweeper - Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * This method is used for getting an image for icon of the window
     * @return an image icon
     */
    public abstract Image getImageIcon();
}
