package panels;

import enums.Difficulty;
import gameObjects.Cell;
import gameObjects.Player;
import utils.Game;
import utils.saveUtils.GameData;
import utils.simpleUI.SimpleButton;
import utils.simpleUI.SimpleLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import static utils.simpleUI.SimpleButton.createButton;

/**
 * EndPanel class is used to show player if he loosed or won.
 *
 * @author Maksym Kulynych
 */
public class EndPanel extends JPanel {
    public final static float SIZE_FACTOR = 1.5f;
    private final int TITLE_WIDTH = 150;
    private final int TITLE_HEIGHT = 50;
    private final int TITLE_FONT_SIZE = 24;

    private final int BUTTON_WIDTH = 100;
    private final int BUTTON_HEIGHT = (int) (IntroducingPanel.BUTTON_HEIGHT / SIZE_FACTOR);
    private final float BUTTON_DISTANCE_FACTOR = 0.5f;
    public static final int BUTTON_FONT_SIZE = 19;
    private final int BUTTON_GAP = (int) (BUTTON_DISTANCE_FACTOR * BUTTON_HEIGHT);

    private final int TEXT_FIELD_WIDTH = 150;
    private final int TEXT_FIELD_HEIGH = 20;

    private ArrayList<SimpleButton> buttons;
    private JTextField textField;
    private SimpleButton submit;
    private final BoardPanel boardPanel;
    private boolean isGoodEnd;

    /**
     * Constructor sets basic panel values and also initializes buttons, text field and submit button.
     * @param boardPanel is needed to paused
     * @param player is needed for saving his data
     */
    public EndPanel(BoardPanel boardPanel, Player player) {
        this.boardPanel = boardPanel;
        this.isGoodEnd = true;

        initButtons();
        setButtonsLocation();
        setButtonsDesign();
        addButtonsToPanel();

        initTextField(player);
        initSubmitButton(player);

        setBounds((boardPanel.width() - width()) / 2, (boardPanel.height() - height()) / 2, width(), height());
        setLayout(null);
        setPreferredSize(new Dimension(width(), height()));
        setVisible(false);

        setBackground(new Color(191, 191, 191));
    }

    /**
     * This method initializes title label by setting its design that depends on if the end is good or not and adding to panel.
     */
    private void initTitleLabel() {
        SimpleLabel titleLabel;

        if (isGoodEnd) {
            titleLabel = SimpleLabel.createTitleLabel(width() / 2 - TITLE_WIDTH / 2, TITLE_HEIGHT / 2, TITLE_WIDTH, TITLE_HEIGHT,
                    new Color(0, 0, 0), new Color(237, 194, 46), "You Won!", new Font("Arial", Font.BOLD, TITLE_FONT_SIZE));
        } else {
            titleLabel = SimpleLabel.createTitleLabel(width() / 2 - TITLE_WIDTH / 2, TITLE_HEIGHT / 2, TITLE_WIDTH, TITLE_HEIGHT,
                    new Color(0, 0, 0), new Color(0,   150, 0), "You Loose!", new Font("Arial", Font.BOLD, TITLE_FONT_SIZE));
        }
        add(titleLabel);
    }

    /**
     * This method simply initializes text field and also adds key listener that saves data.
     * @param player is needed to save data
     */
    private void initTextField(Player player) {
        textField = new JTextField("Enter the title");
        textField.setLocation(0,0);
        textField.setSize(TEXT_FIELD_WIDTH,TEXT_FIELD_HEIGH);

        textField.setLocation(width() / 2 - TEXT_FIELD_WIDTH / 2, height() - Cell.CELL_SIZE);

        textField.setVisible(false);

        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER && !textField.getText().isEmpty()) {
                    GameData gameData = (GameData) new GameData().loadData();
                    gameData.addAll(player, textField.getText(), boardPanel.getBoard());
                    gameData.saveData();
                    textField.setText("");
                    submit.setEnabled(false);
                    textField.setEnabled(false);
                }
            }
        });

        add(textField);
    }

    /**
     * This method simply initializes submit button that saves data.
     * @param player is needed to save data
     */
    private void initSubmitButton(Player player) {
        submit = SimpleButton.createButton("Submit", 60, 20, e -> {
            if (textField.getText().isEmpty()) return;
            GameData gameData = (GameData) new GameData().loadData();
            gameData.addAll(player, textField.getText(), boardPanel.getBoard());
            gameData.saveData();
            textField.setText("");
            submit.setEnabled(false);
            textField.setEnabled(false);
        });

        submit.setDesign(new Color(191, 191, 191), new Color(0, 0, 0), (int) (BUTTON_FONT_SIZE / 2.5), "Arial");

        submit.setLocation((width() - TEXT_FIELD_WIDTH) / 2 + TEXT_FIELD_WIDTH + 10, height() - Cell.CELL_SIZE);

        submit.setVisible(false);

        add(submit);
    }

    /**
     * This method simply initializes buttons and their action listeners.
     */
    private void initButtons() {
        buttons = new ArrayList<>();

        buttons.add(createButton("Retry", BUTTON_WIDTH, BUTTON_HEIGHT, e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.dispose();
            Game.play();
        }));

        buttons.add(createButton("Menu", BUTTON_WIDTH, BUTTON_HEIGHT, e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.dispose();
            Game.start();
        }));

        buttons.add(createButton("Save", BUTTON_WIDTH, BUTTON_HEIGHT, e -> {
            textField.setVisible(true);
            submit.setVisible(true);
        }));
    }

    /**
     * This method simply sets buttons location by calculation.
     */
    private void setButtonsLocation() {
        if (boardPanel.getBoard().getDifficulty() == Difficulty.EASY) {
            for (int i = 0; i < buttons.size(); i++) {
                buttons.get(i).setLocation(width() / 2 - BUTTON_WIDTH / 2, (int) (2 * TITLE_HEIGHT + i * (BUTTON_GAP * 2.5)));
            }
            return;
        }

        int xOffset = (width() - (BUTTON_WIDTH * buttons.size() + BUTTON_GAP * (buttons.size() - 1))) / 2;

        for (int i = 0; i < buttons.size(); i++) {

            buttons.get(i).setLocation(xOffset + i *(BUTTON_WIDTH + BUTTON_GAP), 2 * TITLE_HEIGHT);
        }
    }

    /**
     * This method simply sets buttons design.
     */
    private void setButtonsDesign() {
        for (SimpleButton button : buttons) {
            button.setDesign(new Color(191, 191, 191), new Color(0, 0, 0), BUTTON_FONT_SIZE, "Arial");
        }
    }

    /**
     * This method simply add buttons to panel.
     */
    private void addButtonsToPanel() {
        for (SimpleButton button : buttons) add(button);
    }

    /**
     * This method represents width of the panel.
     * @return width of the panel
     */
    public int width() {
        if (boardPanel.getBoard().getDifficulty() == Difficulty.EASY) return boardPanel.width();
        return (int) (boardPanel.width() / SIZE_FACTOR);
    }

    /**
     * This method represents height of the panel.
     * @return height of the panel
     */
    public int height() {
        if (boardPanel.getBoard().getDifficulty() == Difficulty.EASY) return boardPanel.height();
        return (int) (boardPanel.height() / (SIZE_FACTOR * SIZE_FACTOR));
    }

    /**
     * This method simply adds border to a panel.
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setStroke(new BasicStroke(10));
        g2d.setColor(new Color(154, 154, 154));

        g2d.drawRect(0, 0, width(), height());
        g2d.setStroke(new BasicStroke(3));
    }

    /**
     * This method simply initializes title label by good end.
     * @param goodEnd true - the end is good, false - otherwise
     */
    public void setGoodEnd(boolean goodEnd) {
        isGoodEnd = goodEnd;
        initTitleLabel();
    }
}
