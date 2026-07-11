package utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SimpleButton extends JButton {
    public SimpleButton(String text) {
        super(text);
    }

    public static SimpleButton createButton(String text, int width, int height, ActionListener listener) {
        SimpleButton button = new SimpleButton(text);
        button.setPreferredSize(new Dimension(width, height));
        button.setSize(width, height);
        button.addActionListener(listener);
        return button;
    }
}
