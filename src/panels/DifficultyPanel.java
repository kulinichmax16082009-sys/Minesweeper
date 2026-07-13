package panels;

import enums.Difficulty;
import utils.SimpleButton;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DifficultyPanel extends JPanel {
    public final static float SIZE_FACTOR = 1.5f;
    public final static int HEIGHT = (int) (IntroducingPanel.HEIGHT / SIZE_FACTOR);
    public final static int WIDTH = (int) (IntroducingPanel.WIDTH / SIZE_FACTOR);

    private final int BUTTON_WIDTH = (int) (IntroducingPanel.BUTTON_WIDTH / SIZE_FACTOR);
    private final int BUTTON_HEIGHT = (int) (IntroducingPanel.BUTTON_HEIGHT / SIZE_FACTOR);
    private final float BUTTON_DISTANCE_FACTOR = 1.5f;
    public static final int BUTTON_FONT_SIZE = (int) (IntroducingPanel.BUTTON_FONT_SIZE / SIZE_FACTOR);
    private final int BUTTON_GAP = (int) (BUTTON_DISTANCE_FACTOR * BUTTON_HEIGHT);

    private final int TITLE_WIDTH = 200;
    private final int TITLE_HEIGHT = 50;
    private final int TITLE_FONT_SIZE = 30;

    private JLabel titleLabel;
    private ArrayList<SimpleButton> buttons;
    private IntroducingPanel introducingPanel;
    private Difficulty selectedDifficulty;

    public DifficultyPanel(IntroducingPanel introducingPanel) {
        this.introducingPanel = introducingPanel;
        this.selectedDifficulty = Difficulty.MEDIUM;

        setBounds((IntroducingPanel.WIDTH - WIDTH) / 2, (IntroducingPanel.HEIGHT - HEIGHT) / 2, WIDTH, HEIGHT);
        setLayout(null);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setVisible(false);

        initButtons();
        setButtonsDesign();
        setButtonsLocation();
        addButtonsToPanel();

        initTitleLabel();
    }

    private void initButtons() {
        buttons = new ArrayList<>();

        buttons.add(SimpleButton.createButton("Easy", BUTTON_WIDTH, BUTTON_HEIGHT, e -> {
            selectedDifficulty = Difficulty.EASY;
            introducingPanel.unpause(this);
        }));

        buttons.add(SimpleButton.createButton("Medium", BUTTON_WIDTH, BUTTON_HEIGHT, e -> {
            selectedDifficulty = Difficulty.MEDIUM;
            introducingPanel.unpause(this);
        }));

        buttons.add(SimpleButton.createButton("Hard", BUTTON_WIDTH, BUTTON_HEIGHT, e -> {
            selectedDifficulty = Difficulty.HARD;
            introducingPanel.unpause(this);
        }));
    }

    private void setButtonsDesign() {
        for (SimpleButton button : buttons) {
            button.setForeground(new Color(0, 0, 0));
            button.setBackground(new Color(0, 100, 0));
            button.setFont(new Font("Arial", Font.BOLD, BUTTON_FONT_SIZE));
        }
    }

    private void initTitleLabel() {
        titleLabel = new JLabel("Difficulty", JLabel.CENTER);

        titleLabel.setOpaque(true);
        titleLabel.setForeground(new Color(0, 0, 0));
        titleLabel.setBackground(new Color(0, 100, 0));

        titleLabel.setFont(new Font("Arial", Font.BOLD, TITLE_FONT_SIZE));

        titleLabel.setBounds(WIDTH / 2 - TITLE_WIDTH / 2, TITLE_HEIGHT / 2, TITLE_WIDTH, TITLE_HEIGHT);

        add(titleLabel);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(new Color(130,130,130));
        g2d.fillRect(0,0,WIDTH, HEIGHT);

        g2d.setColor(new Color(130,0,0));
        g2d.setStroke(new BasicStroke(15));
        g2d.drawRect(0,0,WIDTH - 1, HEIGHT - 1);

        g2d.setStroke(new BasicStroke(1));
        g2d.setColor(new Color(0,0,0));
    }

    private void setButtonsLocation() {
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setLocation(WIDTH / 2 - BUTTON_WIDTH / 2,  2 * TITLE_HEIGHT + i * BUTTON_GAP);
        }
    }

    private void addButtonsToPanel() {
        for (SimpleButton button : buttons) add(button);
    }

    public Difficulty getSelectedDifficulty() {
        return selectedDifficulty;
    }

    public void setSelectedDifficulty(Difficulty selectedDifficulty) {
        this.selectedDifficulty = selectedDifficulty;
    }

    public void setIntroducingPanel(IntroducingPanel introducingPanel) {
        this.introducingPanel = introducingPanel;
    }
}
