package utils.simpleUI;

import javax.swing.*;
import java.awt.*;

/**
 * SimpleLabel class represents label pre-built for better code logic.
 *
 * @author Maksym Kulynych
 */
public class SimpleLabel extends JLabel {
    public SimpleLabel(String text, int horizontalAlignment) {
        super(text, horizontalAlignment);
    }

    /**
     * This method simply creates new instance of label by parameters
     * @param x X-axis of position
     * @param y Y-axis of position
     * @param width label width
     * @param height label height
     * @param foreground color of a text
     * @param background color of a background
     * @param name title of the label
     * @param font label font instance
     * @return new instance of SimpleLabel class
     */
    public static SimpleLabel createTitleLabel(int x, int y, int width, int height, Color foreground, Color background, String name, Font font) {
        SimpleLabel titleLabel = new SimpleLabel(name, JLabel.CENTER);

        titleLabel.setOpaque(true);
        titleLabel.setForeground(foreground);
        titleLabel.setBackground(background);

        titleLabel.setFont(font);

        titleLabel.setBounds(x, y, width, height);

        return titleLabel;
    }
}
