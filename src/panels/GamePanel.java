package panels;
import gameObjects.Cell;
import gameObjects.Player;
import utils.Board;
import utils.constants.Images;
import utils.simpleUI.SimpleLabel;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    public static final int TITLE_CELLS_AMOUNT_HEIGHT = 4;
    public static final int TITLE_CELLS_AMOUNT_WIDTH = 2;

    private Board board;
    private Player player;
    private SimpleLabel titleLabel;
    private BoardPanel boardPanel;

    public GamePanel(Board board, BoardPanel boardPanel, Player player) {
        this.board = board;
        this.player = player;
        this.boardPanel = boardPanel;

        initEmojiLabel();

        add(boardPanel);
        setComponentZOrder(boardPanel, 0);

        setLayout(null);
        setPreferredSize(new Dimension(width(), height()));

    }

    public void initEmojiLabel() {
        ImageIcon icon = new ImageIcon(Images.PLAYER_ALIVE);

        titleLabel = new SimpleLabel(icon, JLabel.CENTER);

        titleLabel.setBounds((width() - icon.getIconWidth()) / 2, 10, icon.getIconWidth(), icon.getIconHeight());

        add(titleLabel);
    }

    public int width() {
        return board.getWidth() * Cell.CELL_SIZE + TITLE_CELLS_AMOUNT_WIDTH * Cell.CELL_SIZE;
    }

    public int height() {
        return board.getHeight() * Cell.CELL_SIZE + TITLE_CELLS_AMOUNT_HEIGHT * Cell.CELL_SIZE;
    }

    @Override
    protected void printComponent(Graphics g) {
        super.printComponent(g);

        g.drawImage(Images.PLAYER_ALIVE, 0, 0, null);

        repaint();
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
