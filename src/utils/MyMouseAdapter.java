package utils;

import enums.CellTypes;
import enums.Difficulty;
import gameObjects.Cell;
import panels.GamePanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyMouseAdapter extends MouseAdapter {
    private Board board;
    private final GamePanel gamePanel;
    private boolean firstReveal;

    public MyMouseAdapter(Board board, GamePanel gamePanel) {
        firstReveal = true;
        this.board = board;
        this.gamePanel = gamePanel;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        int x = e.getX() / Cell.CELL_SIZE;
        int y = e.getY() / Cell.CELL_SIZE;

//        System.out.println(e.getX());
//        System.out.println(e.getY());
//
//        System.out.println(x);
//        System.out.println(y);

        if (e.getButton() == MouseEvent.BUTTON1) {
            if (firstReveal) {
                board.generateBoard(Difficulty.HARD, x, y);
                firstReveal = false;
            }

            board.getCells()[y][x].reveal();
            board.openZerosNear(x, y);
        }

        if (e.getButton() == MouseEvent.BUTTON3) {
            if (!board.getCells()[y][x].isRevealed()) {
                board.getCells()[y][x].setType(CellTypes.FLAGGED);
                board.getCells()[y][x].reveal();
            } else if (board.getCells()[y][x].getType() == CellTypes.FLAGGED) {
                if (board.getCells()[y][x].getValue().getNumber() == -1) {
                    board.getCells()[y][x].setType(CellTypes.MINE);
                } else {
                    board.getCells()[y][x].setType(CellTypes.NUMBER);
                }

                board.getCells()[y][x].hide();
            }
        }

        gamePanel.repaint();
        SoundPlayer.play(board.getCells()[y][x].getSound(), false);
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
