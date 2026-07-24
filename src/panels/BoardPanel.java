package panels;

import gameObjects.Cell;
import gameObjects.Player;
import utils.Board;
import utils.MyMouseAdapter;

import javax.swing.*;
import java.awt.*;

/**
 * BoardPanel class represents panel where main game board is shown.
 *
 * @author Maksym Kulynych
 */
public class BoardPanel extends JPanel {
    private final Board board;
    private boolean isPaused;

    /**
     * Constructor sets all values to the panel and adds mouse adapter.
     * @param board board that is used in mouse adapter
     * @param player player that is used in player
     */
    public BoardPanel(Board board, Player player) {
        this.board = board;

        setBounds(Cell.CELL_SIZE * (GamePanel.TITLE_CELLS_AMOUNT_WIDTH - 1), Cell.CELL_SIZE * (GamePanel.TITLE_CELLS_AMOUNT_HEIGHT - 1), width(), height());
        addMouseListener(new MyMouseAdapter(board, this, player));
        setLayout(null);
        setPreferredSize(new Dimension(width(), height()));
    }

    /**
     * This method represents width of the panel.
     * @return width of the panel
     */
    public int width() {
        return board.getWidth() * Cell.CELL_SIZE;
    }

    /**
     * This method represents height of the panel.
     * @return height of the panel
     */
    public int height() {
        return board.getHeight() * Cell.CELL_SIZE;
    }

    /**
     * This method paints all cell in the board and also black rectangle if panel is paused.
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (board.isDead()) board.revealAllMines();

        for (int i = 0; i < board.getCells().length; i++) {
            for (int j = 0; j < board.getCells()[i].length; j++) {
                board.getCells()[i][j].paint(g,j * Cell.CELL_SIZE, i * Cell.CELL_SIZE);
            }
        }

        if (isPaused) {
            g.setColor(new Color(0, 0, 0, 150));
            g.fillRect(0, 0, width(), height());
        }
    }

    /**
     * This method simply pauses/unpauses panel to show/hide another panel.
     * @param newPanel panel that must be shown
     * @param isPaused boolean that decides whether panel must be paused/unpaused
     */
    public void setPaused(JPanel newPanel, boolean isPaused) {
        this.isPaused = isPaused;

        newPanel.setVisible(isPaused);

        repaint();
    }

    public Board getBoard() {
        return board;
    }
}
