package utils.simpleUI;

import javax.swing.*;
import java.awt.event.ActionListener;

public class SimpleCheckBox extends JCheckBox {
    public SimpleCheckBox(String text) {
        super(text);
    }

    public static SimpleCheckBox createCheckBox(String text, int width, int height, boolean isSelected, ActionListener listener) {
        SimpleCheckBox simpleCheckBox  = new SimpleCheckBox(text);
        simpleCheckBox.setSelected(isSelected);
        simpleCheckBox.setSize(width, height);
        simpleCheckBox.addActionListener(listener);
        return simpleCheckBox;
    }
}
