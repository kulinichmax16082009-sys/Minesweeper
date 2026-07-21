package utils.simpleUI;

import javax.swing.*;
import java.awt.*;

public class SimpleLabel extends JLabel {
    public SimpleLabel(String text, int horizontalAlignment) {
        super(text, horizontalAlignment);
    }

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
