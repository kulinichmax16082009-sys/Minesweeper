package panels;

import gameObjects.Cell;
import gameObjects.Player;
import utils.Board;
import utils.MyMouseAdapter;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {
    private final Board board;

    public BoardPanel(Board board, Player player) {
        this.board = board;

        setBounds(Cell.CELL_SIZE * (GamePanel.TITLE_CELLS_AMOUNT_WIDTH - 1), Cell.CELL_SIZE * (GamePanel.TITLE_CELLS_AMOUNT_HEIGHT - 1), width(), height());
        addMouseListener(new MyMouseAdapter(board, this, player));
        setLayout(null);
        setPreferredSize(new Dimension(width(), height()));
    }

    public int width() {
        return board.getWidth() * Cell.CELL_SIZE;
    }

    public int height() {
        return board.getHeight() * Cell.CELL_SIZE;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (board.isDead()) {
            board.revealAllMines();
        }

        for (int i = 0; i < board.getCells().length; i++) {
            for (int j = 0; j < board.getCells()[i].length; j++) {
                board.getCells()[i][j].paint(g,j * Cell.CELL_SIZE, i * Cell.CELL_SIZE);
            }
        }
    }

    public Board getBoard() {
        return board;
    }
}
