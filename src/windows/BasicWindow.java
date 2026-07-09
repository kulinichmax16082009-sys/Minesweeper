package windows;

import javax.swing.*;
import java.awt.*;

public abstract class BasicWindow {
    protected JFrame frame;
    protected int width;
    protected int height;

    public BasicWindow(String title, int width, int height) {
        this.width = width;
        this.height = height;
        frame = new JFrame(title);

        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setFocusable(true);
        frame.setIconImage(new ImageIcon(getImagePath()).getImage());
    }

    public void close() {
        frame.dispose();
    }

    public static void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public abstract String getImagePath();
}
