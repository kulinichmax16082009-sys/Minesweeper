package windows;

import javax.swing.*;
import java.awt.*;

public abstract class BasicWindow {
    protected JFrame frame;

    public BasicWindow(String title) {
        frame = new JFrame(title);

        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setFocusable(true);
        frame.setIconImage(new ImageIcon(getImagePath()).getImage());
    }

    public static void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Minesweeper - Error", JOptionPane.ERROR_MESSAGE);
    }

    public abstract String getImagePath();
}
