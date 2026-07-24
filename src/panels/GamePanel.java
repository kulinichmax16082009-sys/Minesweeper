package panels;
import gameObjects.Cell;
import gameObjects.Player;
import utils.simpleUI.SimpleLabel;

import javax.swing.*;
import java.awt.*;

/**
 * GamePanel class is used for showing side things such as flags left and time.
 *
 * @author Maksym Kulynych
 */
public class GamePanel extends JPanel {
    public static final int TITLE_CELLS_AMOUNT_HEIGHT = 4;
    public static final int TITLE_CELLS_AMOUNT_WIDTH = 2;

    private final int LABEL_WIDTH = 200;
    private final int LABEL_HEIGHT = 30;
    private final int LABEL_FONT_SIZE = 17;

    private final Player player;
    private SimpleLabel timeLabel;
    private SimpleLabel flagsLabel;
    private final BoardPanel boardPanel;

    /**
     * Constructor initializes basic panel values and also time label and flags left label.
     * @param boardPanel panel that must be set
     * @param player player values that are shown in labels
     */
    public GamePanel(BoardPanel boardPanel, Player player) {
        this.player = player;
        this.boardPanel = boardPanel;

        initTimeLabel();
        initFlagsLabel();

        Timer timer = new Timer(16, e -> {
            repaint();
            updateFlagsLabel();
        });

        timer.start();

        setLayout(null);
        setPreferredSize(new Dimension(width(), height()));

        setBackground(new Color(191, 191, 191));
    }

    /**
     * This method simply initializes time label by setting its design and adding it to panel.
     */
    private void initTimeLabel() {
        timeLabel = SimpleLabel.createTitleLabel(Cell.CELL_SIZE, Cell.CELL_SIZE + Cell.CELL_SIZE / 2,
                LABEL_WIDTH, LABEL_HEIGHT,
                new Color(0,0,0), new Color(191, 191, 191),
                "Time: 0", new Font("Arial", Font.BOLD, LABEL_FONT_SIZE));

        timeLabel.setBorder(BorderFactory.createLineBorder(new Color(154, 154, 154), 3));

        add(timeLabel);
    }

    /**
     * This method simply initializes flags left label by setting its design and adding it to panel.
     */
    private void initFlagsLabel() {
        flagsLabel = SimpleLabel.createTitleLabel(Cell.CELL_SIZE, Cell.CELL_SIZE - Cell.CELL_SIZE / 2,
                LABEL_WIDTH, LABEL_HEIGHT,
                new Color(0,0,0), new Color(191, 191, 191),
                "Flags left: " + player.getFlagsLeft(), new Font("Arial", Font.BOLD, LABEL_FONT_SIZE));

        flagsLabel.setBorder(BorderFactory.createLineBorder(new Color(154, 154, 154), 3));

        add(flagsLabel);
    }

    /**
     * This method updates time in label and transforms it into simple format.
     */
    public void updateTimeLabel() {
        if (player.getTime() >= 60) {
            long minutes = 0;
            long seconds = player.getTime();

            while (seconds >= 60) {
                seconds -= 60;
                minutes++;
            }
            timeLabel.setText("Time: " + minutes + " min " + seconds + " s");

        } else {
            timeLabel.setText("Time: " + player.getTime() + " s");
        }
    }

    /**
     * This method simply updates flags left amount in label.
     */
    private void updateFlagsLabel() {
        flagsLabel.setText("Flags left: " + player.getFlagsLeft());
    }

    /**
     * This method represents width of the panel.
     * @return width of the panel
     */
    private int width() {
        return boardPanel.getBoard().getWidth() * Cell.CELL_SIZE + TITLE_CELLS_AMOUNT_WIDTH * Cell.CELL_SIZE;
    }

    /**
     * This method represents height of the panel.
     * @return height of the panel
     */
    private int height() {
        return boardPanel.getBoard().getHeight() * Cell.CELL_SIZE + TITLE_CELLS_AMOUNT_HEIGHT * Cell.CELL_SIZE;
    }

    /**
     * This method paint current player icon and also border between board panel and game panel.
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        player.setPlayerIcon(boardPanel.getBoard().isDead(), boardPanel.getBoard().isWin());

        Image playerIcon = player.getIcon();

        if (playerIcon != null) g2d.drawImage(playerIcon, getWidth() - playerIcon.getWidth(null) - Cell.CELL_SIZE, 10, null);

        g2d.setStroke(new BasicStroke(20));
        g2d.setColor(new Color(154, 154, 154));

        g2d.drawRect(Cell.CELL_SIZE * (TITLE_CELLS_AMOUNT_WIDTH - 1),
                Cell.CELL_SIZE * (TITLE_CELLS_AMOUNT_HEIGHT - 1),
                width() - Cell.CELL_SIZE * TITLE_CELLS_AMOUNT_WIDTH, height() - Cell.CELL_SIZE * TITLE_CELLS_AMOUNT_HEIGHT);
        g2d.setStroke(new BasicStroke(1));
    }
}
