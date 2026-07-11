package panels;

import gameObjects.Cell;
import utils.RandomGen;
import utils.SimpleButton;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static utils.SimpleButton.createButton;

public class IntroducingPanel extends JPanel {
    private static final int CELL_AMOUNT_HEIGHT = 13;
    private static final int CELL_AMOUNT_WIDTH = 22;

    public final static int HEIGHT = CELL_AMOUNT_HEIGHT * Cell.CELL_SIZE;
    public final static int WIDTH = CELL_AMOUNT_WIDTH * Cell.CELL_SIZE;

    public static final int BUTTON_WIDTH = 200;
    public static final int BUTTON_HEIGHT = 50;
    private final float BUTTON_DISTANCE_FACTOR = 1.5f;
    private final int BUTTON_X = 40;
    public static final int BUTTON_FONT_SIZE = 20;

    private final int TITLE_WIDTH = 200;
    private final int TITLE_HEIGHT = 50;
    private final int TITLE_FONT_SIZE = 30;

    private final RandomGen rnd;
    private ArrayList<SimpleButton> buttons;
    private DifficultyPanel difficultyPanel;
    private JLabel titleLabel;
    private Timer timer;
    private boolean isPaused;

    public IntroducingPanel(DifficultyPanel difficultyPanel) {
        this.rnd = new RandomGen();
        this.difficultyPanel = difficultyPanel;

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLayout(null);

        initButtons(difficultyPanel);
        setButtonsDesign();
        setButtonsLocation();
        addButtonsToPanel();
        initTitleLabel();

        startAnimation();
    }

    private void initButtons(DifficultyPanel difficultyPanel) {
        buttons = new ArrayList<>();

        buttons.add(createButton("Start", BUTTON_WIDTH, BUTTON_HEIGHT, e -> {
            // Handle start game action
            System.out.println("Start Game button clicked");
        }));

        buttons.add(createButton("Settings",BUTTON_WIDTH, BUTTON_HEIGHT, e -> {
            // Handle settings action
            System.out.println("Settings button clicked");
        }));

        buttons.add(createButton("Difficulty",BUTTON_WIDTH, BUTTON_HEIGHT, e -> pause(difficultyPanel)));

        buttons.add(createButton("Quit",BUTTON_WIDTH, BUTTON_HEIGHT ,e -> System.exit(0)));
    }

    public void pause(JPanel newPanel) {
        isPaused = true;

        newPanel.setVisible(true);
        for (SimpleButton button : buttons) button.setVisible(false);

        titleLabel.setVisible(false);

        stopAnimation();
        repaint();
    }

    public void unpause(JPanel newPanel) {
        isPaused = false;

        newPanel.setVisible(false);
        for (SimpleButton button : buttons) button.setVisible(true);

        titleLabel.setVisible(true);

        startAnimation();
        repaint();
    }

    private void initTitleLabel() {
        titleLabel = new JLabel("Minesweeper", JLabel.CENTER);

        titleLabel.setOpaque(true);
        titleLabel.setForeground(new Color(0, 0, 0));
        titleLabel.setBackground(new Color(173, 24, 24));

        titleLabel.setFont(new Font("Arial", Font.BOLD, TITLE_FONT_SIZE));

        titleLabel.setBounds(WIDTH - TITLE_WIDTH - 10, HEIGHT / 2 - TITLE_HEIGHT / 2, TITLE_WIDTH, TITLE_HEIGHT);

        add(titleLabel);
    }

    private void setButtonsDesign() {
        for (SimpleButton button : buttons) {
            button.setForeground(new Color(0, 0, 0));
            button.setBackground(new Color(173, 24, 24));
            button.setFont(new Font("Arial", Font.BOLD, BUTTON_FONT_SIZE));
        }
    }

    private void setButtonsLocation() {
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setLocation(BUTTON_X, (int) ((float) (HEIGHT - buttons.size() * BUTTON_HEIGHT) / buttons.size() + i * (BUTTON_DISTANCE_FACTOR * BUTTON_HEIGHT)));
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
            g.setColor(new Color(0, 0, 0, 120));
            g.fillRect(0, 0, WIDTH, HEIGHT);
        }
    }

    private void startAnimation() {
        if (timer == null) {
            timer = new Timer(1000, e -> repaint());
            timer.start();
        }
    }

    private void stopAnimation() {
        if (timer != null) {
            timer.stop();
            timer = null;
        }
    }

    public DifficultyPanel getDifficultyPanel() {
        return difficultyPanel;
    }

    public void setDifficultyPanel(DifficultyPanel difficultyPanel) {
        this.difficultyPanel = difficultyPanel;
    }
}
