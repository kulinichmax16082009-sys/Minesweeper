package panels;

import gameObjects.Cell;
import utils.Game;
import utils.RandomGen;
import utils.simpleUI.SimpleButton;
import utils.SoundPlayer;
import utils.simpleUI.SimpleLabel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static utils.simpleUI.SimpleButton.createButton;

public class IntroducingPanel extends JPanel {
    private static final int CELL_AMOUNT_HEIGHT = 13;
    private static final int CELL_AMOUNT_WIDTH = 22;

    public final static int HEIGHT = CELL_AMOUNT_HEIGHT * Cell.CELL_SIZE;
    public final static int WIDTH = CELL_AMOUNT_WIDTH * Cell.CELL_SIZE;

    public static final int BUTTON_WIDTH = 200;
    public static final int BUTTON_HEIGHT = 50;
    private final float BUTTON_DISTANCE_FACTOR = 1.5f;
    public static final int BUTTON_FONT_SIZE = 20;
    private final int BUTTON_GAP = (int) (BUTTON_HEIGHT * BUTTON_DISTANCE_FACTOR);

    private final int TITLE_WIDTH = 200;
    private final int TITLE_HEIGHT = 50;
    private final int TITLE_FONT_SIZE = 30;

    private static int animationSpeed;

    private final RandomGen rnd;
    private ArrayList<SimpleButton> buttons;
    private final DifficultyPanel difficultyPanel;
    private final SettingsPanel settingsPanel;
    private final StatsPanel statsPanel;
    private SimpleLabel titleLabel;
    private Timer timer;
    private boolean isPaused;

    public IntroducingPanel(DifficultyPanel difficultyPanel, SettingsPanel settingsPanel, StatsPanel statsPanel) {
        this.rnd = new RandomGen();
        this.difficultyPanel = difficultyPanel;
        this.settingsPanel = settingsPanel;
        this.statsPanel = statsPanel;

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLayout(null);

        initButtons();
        setButtonsDesign();
        setButtonsLocation();
        addButtonsToPanel();
        initTitleLabel();

        startAnimation();
    }

    private void initButtons() {
        buttons = new ArrayList<>();

        buttons.add(createButton("Start", BUTTON_WIDTH, BUTTON_HEIGHT, e -> {
            Game.play();
            SoundPlayer.pause(settingsPanel.getSelectedMusic().getPath());
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.dispose();
        }));
        buttons.add(createButton("Stats", BUTTON_WIDTH, BUTTON_HEIGHT, e -> setPaused(statsPanel, true)));
        buttons.add(createButton("Settings",BUTTON_WIDTH, BUTTON_HEIGHT, e -> setPaused(settingsPanel, true)));
        buttons.add(createButton("Difficulty",BUTTON_WIDTH, BUTTON_HEIGHT, e -> setPaused(difficultyPanel, true)));
        buttons.add(createButton("Quit",BUTTON_WIDTH, BUTTON_HEIGHT ,e -> System.exit(0)));
    }

    public void setPaused(JPanel newPanel, boolean isPaused) {
        this.isPaused = isPaused;

        newPanel.setVisible(isPaused);
        for (SimpleButton button : buttons) button.setVisible(!isPaused);

        titleLabel.setVisible(!isPaused);

        repaint();
    }

    private void initTitleLabel() {
        titleLabel = SimpleLabel.createTitleLabel(WIDTH - TITLE_WIDTH - 10, HEIGHT / 2 - TITLE_HEIGHT / 2, TITLE_WIDTH, TITLE_HEIGHT,
                new Color(0, 0, 0), new Color(173, 24, 24), "Minesweeper", new Font("Arial", Font.BOLD, TITLE_FONT_SIZE));

        add(titleLabel);
    }

    private void setButtonsDesign() {
        for (SimpleButton button : buttons) {
            button.setDesign(new Color(173, 24, 24), new Color(0, 0, 0), BUTTON_FONT_SIZE, "Arial");
        }
    }

    private void setButtonsLocation() {
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setLocation(40, (int) (TITLE_HEIGHT * 0.75 + i * BUTTON_GAP));
        }
    }

    private void addButtonsToPanel() {
        for (SimpleButton button : buttons) add(button);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < HEIGHT; i += Cell.CELL_SIZE) {
            for (int j = 0; j < WIDTH; j += Cell.CELL_SIZE) {
                Cell cell = rnd.randomCell();
                cell.paint(g, j, i);
            }
        }

        if (isPaused) {
            g.setColor(new Color(0, 0, 0, 150));
            g.fillRect(0, 0, WIDTH, HEIGHT);
        }
    }

    public void startAnimation() {
        if (timer == null && animationSpeed != 1000) {
            timer = new Timer(animationSpeed, e -> repaint());
            timer.start();
        }
    }

    public void stopAnimation() {
        if (timer != null) {
            timer.stop();
            timer = null;
        }
    }

    public static void setAnimationSpeed(int animationSpeed) {
        IntroducingPanel.animationSpeed = animationSpeed;
    }
}
