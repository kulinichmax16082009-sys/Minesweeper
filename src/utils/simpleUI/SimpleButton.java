package utils.simpleUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * SimpleButton class represents button pre-built for better code logic.
 *
 * @author Maksym Kulynych
 */
public class SimpleButton extends JButton {
    public SimpleButton(String text) {
        super(text);
    }

    /**
     * This method simply creates new instance of a button by parameters
     * @param text title that will be shown on button
     * @param width button width
     * @param height button height
     * @param listener action after pressing the button
     * @return new instance of SimpleButton class
     */
    public static SimpleButton createButton(String text, int width, int height, ActionListener listener) {
        SimpleButton button = new SimpleButton(text);
        button.setSize(width, height);
        button.addActionListener(listener);
        return button;
    }

    /**
     * This method simply sets basic design parameters
     * @param background color of a background
     * @param foreground color of a text
     * @param size text size
     * @param font text font
     */
    public void setDesign(Color background, Color foreground, int size, String font) {
        setForeground(foreground);
        setBackground(background);
        setFont(new Font(font, Font.BOLD, size));
    }
}
