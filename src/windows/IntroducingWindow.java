package windows;

import panels.DifficultyPanel;
import panels.IntroducingPanel;

import javax.swing.*;
import java.awt.*;

public class IntroducingWindow extends BasicWindow {
    private IntroducingPanel introducingPanel;
    private DifficultyPanel difficultyPanel;

    public IntroducingWindow() {
        super("Minesweeper - Main Menu");

        initPanels();

        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    @Override
    public String getImagePath() {
        return "";
    }

    private void initPanels() {
        difficultyPanel = new DifficultyPanel(null);
        introducingPanel = new IntroducingPanel(difficultyPanel);
        difficultyPanel.setIntroducingPanel(introducingPanel);

        frame.add(introducingPanel, BorderLayout.CENTER);
        introducingPanel.add(difficultyPanel);
        introducingPanel.setComponentZOrder(difficultyPanel, 0);
    }
}
