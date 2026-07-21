package windows;

import panels.DifficultyPanel;
import panels.IntroducingPanel;
import panels.SettingsPanel;
import panels.StatsPanel;

import javax.swing.*;
import java.awt.*;

public class IntroducingWindow extends BasicWindow {
    private DifficultyPanel difficultyPanel;

    public IntroducingWindow() {
        super("Minesweeper - Main Menu");

        initPanels();

        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    @Override
    public String getImagePath() {
        return "resources/cellTypesIcons/flag.png";
    }

    private void initPanels() {
        StatsPanel statsPanel = new StatsPanel(null);
        SettingsPanel settingsPanel = new SettingsPanel(null);
        difficultyPanel = new DifficultyPanel(null);
        IntroducingPanel introducingPanel = new IntroducingPanel(difficultyPanel, settingsPanel, statsPanel);

        difficultyPanel.setIntroducingPanel(introducingPanel);
        settingsPanel.setIntroducingPanel(introducingPanel);
        statsPanel.setIntroducingPanel(introducingPanel);

        introducingPanel.add(difficultyPanel);
        introducingPanel.add(settingsPanel);
        introducingPanel.add(statsPanel);

        introducingPanel.setComponentZOrder(statsPanel,0);
        introducingPanel.setComponentZOrder(difficultyPanel, 0);
        introducingPanel.setComponentZOrder(settingsPanel, 0);

        frame.add(introducingPanel, BorderLayout.CENTER);
    }

    public DifficultyPanel getDifficultyPanel() {
        return difficultyPanel;
    }
}
