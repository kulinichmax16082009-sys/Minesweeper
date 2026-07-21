package panels;

import enums.Difficulty;
import gameObjects.Cell;
import gameObjects.Player;
import utils.Game;
import utils.GameData;
import utils.simpleUI.SimpleButton;
import utils.simpleUI.SimpleLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import static utils.simpleUI.SimpleButton.createButton;

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

    private final int SUBMIT_BUTTON_WIDTH = 60;
    private final int SUBMIT_BUTTON_HEIGHT = 20;

    private ArrayList<SimpleButton> buttons;
    private JTextField textField;
    private SimpleButton submit;
    private final BoardPanel boardPanel;
    private boolean isGoodEnd;

    public EndPanel(BoardPanel boardPanel, GameData gameData, Player player) {
        this.boardPanel = boardPanel;
        this.isGoodEnd = true;

        initButtons();
        setButtonsLocation();
        setButtonsDesign();
        addButtonsToPanel();

        initTextField(gameData, player);
        initSubmitButton(gameData, player);

        setBounds((boardPanel.width() - width()) / 2, (boardPanel.height() - height()) / 2, width(), height());
        setLayout(null);
        setPreferredSize(new Dimension(width(), height()));
        setVisible(false);
        setBackground(new Color(191, 191, 191));
    }

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

    private void initTextField(GameData gameData, Player player) {
        textField = new JTextField("Enter the title");
        textField.setLocation(0,0);
        textField.setSize(TEXT_FIELD_WIDTH,TEXT_FIELD_HEIGH);

        textField.setLocation(width() / 2 - TEXT_FIELD_WIDTH / 2, height() - Cell.CELL_SIZE);

        textField.setVisible(false);

        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER && !textField.getText().isEmpty()) {
                    gameData.addAll(player, textField.getText(), boardPanel.getBoard());
                    gameData.saveGame();
                    textField.setText("");
                    submit.setEnabled(false);
                    textField.setEnabled(false);
                }
            }
        });

        add(textField);
    }

    private void initSubmitButton(GameData gameData, Player player) {
        submit = SimpleButton.createButton("Submit", SUBMIT_BUTTON_WIDTH, SUBMIT_BUTTON_HEIGHT, e -> {
            if (textField.getText().isEmpty()) return;
            gameData.addAll(player, textField.getText(), boardPanel.getBoard());
            gameData.saveGame();
            textField.setText("");
            submit.setEnabled(false);
            textField.setEnabled(false);
        });

        submit.setDesign(new Color(191, 191, 191), new Color(0, 0, 0), (int) (BUTTON_FONT_SIZE / 2.5), "Arial");

        submit.setLocation((width() - TEXT_FIELD_WIDTH) / 2 + TEXT_FIELD_WIDTH + 10, height() - Cell.CELL_SIZE);

        submit.setVisible(false);

        add(submit);
    }

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

    private void setButtonsDesign() {
        for (SimpleButton button : buttons) {
            button.setDesign(new Color(191, 191, 191), new Color(0, 0, 0), BUTTON_FONT_SIZE, "Arial");
        }
    }

    private void addButtonsToPanel() {
        for (SimpleButton button : buttons) add(button);
    }

    public int width() {
        if (boardPanel.getBoard().getDifficulty() == Difficulty.EASY) return boardPanel.width();
        return (int) (boardPanel.width() / SIZE_FACTOR);
    }

    public int height() {
        if (boardPanel.getBoard().getDifficulty() == Difficulty.EASY) return boardPanel.height();
        return (int) (boardPanel.height() / (SIZE_FACTOR * SIZE_FACTOR));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setStroke(new BasicStroke(10));
        g2d.setColor(new Color(154, 154, 154));

        g2d.drawRect(0, 0, width(), height());
        g2d.setStroke(new BasicStroke(3));
    }

    public void setGoodEnd(boolean goodEnd) {
        isGoodEnd = goodEnd;
        initTitleLabel();
    }
}
