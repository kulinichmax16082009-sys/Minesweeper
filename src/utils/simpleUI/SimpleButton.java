package utils.simpleUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SimpleButton extends JButton {
    public SimpleButton(String text) {
        super(text);
    }

    public static SimpleButton createButton(String text, int width, int height, ActionListener listener) {
        SimpleButton button = new SimpleButton(text);
        button.setSize(width, height);
        button.addActionListener(listener);
        return button;
    }

    public void setDesign(Color background, Color foreground, int size, String font) {
        setForeground(foreground);
        setBackground(background);
        setFont(new Font(font, Font.BOLD, size));
    }
}
