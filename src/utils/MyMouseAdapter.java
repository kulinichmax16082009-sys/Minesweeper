package utils;

import enums.CellTypes;
import gameObjects.Cell;
import panels.GamePanel;
import utils.constants.Sounds;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyMouseAdapter extends MouseAdapter {
    private final Board board;
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
                board.generateBoard(x, y);
                firstReveal = false;
            }

            if (!board.getCells()[y][x].isRevealed()) {
                board.getCells()[y][x].reveal();
                board.openZerosNear(x, y);
                SoundPlayer.play(board.getCells()[y][x].getSound(), false);
            }
        }

        if (e.getButton() == MouseEvent.BUTTON3) {
            if (!board.getCells()[y][x].isRevealed()) {
                board.getCells()[y][x].setType(CellTypes.FLAGGED);
                board.getCells()[y][x].reveal();
                SoundPlayer.play(Sounds.FLAG, false);
            } else if (board.getCells()[y][x].getType() == CellTypes.FLAGGED) {
                if (board.getCells()[y][x].getValue().getNumber() == -1) {
                    board.getCells()[y][x].setType(CellTypes.MINE);
                } else {
                    board.getCells()[y][x].setType(CellTypes.NUMBER);
                }
                SoundPlayer.play(Sounds.FLAG, false);
                board.getCells()[y][x].hide();
            }
        }

        gamePanel.repaint();
    }
}
