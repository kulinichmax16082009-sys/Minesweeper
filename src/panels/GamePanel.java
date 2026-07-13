package panels;
import utils.Board;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private Board board;

    public GamePanel(Board board) {
        this.board = board;

        setLayout(null);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
