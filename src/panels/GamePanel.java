package panels;
import gameObjects.Cell;
import utils.Board;
import utils.MyMouseAdapter;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private Board board;

    public GamePanel(Board board) {
        this.board = board;

        addMouseListener(new MyMouseAdapter(board, this));

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

        for (int i = 0; i < board.getCells().length; i++) {
            for (int j = 0; j < board.getCells()[i].length; j++) {
                board.getCells()[i][j].paint(g,j * 32, i * 32);
            }
        }
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
