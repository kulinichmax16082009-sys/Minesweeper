package windows;

import gameObjects.Cell;
import panels.IntroducingPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class IntroducingWindow extends BasicWindow {

    private static final int CELL_AMOUNT_HEIGHT = 13;
    private static final int CELL_AMOUNT_WIDTH = 22;

    public final static int HEIGHT = CELL_AMOUNT_HEIGHT * Cell.CELL_SIZE;
    public final static int WIDTH = CELL_AMOUNT_WIDTH * Cell.CELL_SIZE;

    private final int BUTTON_WIDTH = 200;
    private final int BUTTON_HEIGHT = 50;
    private final float BUTTON_DISTANCE_FACTOR = 1.5f;
    private final int BUTTON_X = 40;

    private final int TITLE_WIDTH = 200;
    private final int TITLE_HEIGHT = 50;
    private final int TITLE_FONT_SIZE = 30;

    private IntroducingPanel panel;
    private ArrayList<JButton> buttons;

    public IntroducingWindow() {
        super("Minesweeper - Main Menu", WIDTH, HEIGHT);

        initIntroducingPanel();

        initButtons();
        setButtonsDesign();
        setButtonsSize();
        setButtonsLocation();
        addButtonsToPanel();

        initTitleLabel();

        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    private void initButtons() {
        buttons = new ArrayList<>();

        buttons.add(createButton("Start", e -> {
            // Handle start game action
            System.out.println("Start Game button clicked");
        }));

        buttons.add(createButton("Settings", e -> {
            // Handle settings action
            System.out.println("Settings button clicked");
        }));

        buttons.add(createButton("Difficulty", e -> {
            // Handle difficulty action
            System.out.println("Difficulty button clicked");
        }));

        buttons.add(createButton("Quit", e -> System.exit(0)));
    }

    private JButton createButton(String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        button.addActionListener(listener);
        return button;
    }

    private void setButtonsDesign() {
        for (JButton button : buttons) {
            button.setForeground(new Color(0, 0, 0));
            button.setBackground(new Color(173, 24, 24));
        }
    }

    private void setButtonsSize() {
        for (JButton button : buttons) button.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
    }

    private void setButtonsLocation() {
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setLocation(BUTTON_X, (int) ((float) (height - buttons.size() * BUTTON_HEIGHT) / buttons.size() + i * (BUTTON_DISTANCE_FACTOR * BUTTON_HEIGHT)));
        }
    }

    private void addButtonsToPanel() {
        for (JButton button : buttons) panel.add(button);
    }

   private void initIntroducingPanel() {
        panel = new IntroducingPanel();
        panel.setPreferredSize(new Dimension(width, height));
        panel.setLayout(null);
        frame.add(panel, BorderLayout.CENTER);
    }

    private void initTitleLabel() {
        JLabel titleLabel = new JLabel("Minesweeper", JLabel.CENTER);

        titleLabel.setOpaque(true);
        titleLabel.setForeground(new Color(0, 0, 0));
        titleLabel.setBackground(new Color(173, 24, 24));

        titleLabel.setFont(new Font("Arial", Font.BOLD, TITLE_FONT_SIZE));

        titleLabel.setBounds(width - TITLE_WIDTH - 10, height / 2 - TITLE_HEIGHT / 2, TITLE_WIDTH, TITLE_HEIGHT);

        panel.add(titleLabel);
    }

    @Override
    public String getImagePath() {
        return "";
    }
}
