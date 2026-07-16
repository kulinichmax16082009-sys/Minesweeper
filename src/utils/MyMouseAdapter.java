package utils;

import enums.CellTypes;
import gameObjects.Cell;
import panels.BoardPanel;
import utils.constants.Sounds;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyMouseAdapter extends MouseAdapter {
    private final Board board;
    private final BoardPanel boardPanel;
    private boolean firstReveal;

    public MyMouseAdapter(Board board, BoardPanel boardPanel) {
        firstReveal = true;
        this.board = board;
        this.boardPanel = boardPanel;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        int x = (e.getX()) / Cell.CELL_SIZE;
        int y = (e.getY()) / Cell.CELL_SIZE;

        if (board.isDead()) return;

        if (x < 0 || x >= board.getWidth() || y < 0 || y >= board.getHeight()) return;

        if (e.getButton() == MouseEvent.BUTTON1) {
            if (firstReveal) {
                Game.startTimer();
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
            if (!board.getCells()[y][x].isRevealed() && board.getNumberOfMines() > 0) {
                board.getCells()[y][x].setType(CellTypes.FLAG);
                board.getCells()[y][x].reveal();
                board.subtractNumberOfMines();
                SoundPlayer.play(Sounds.FLAG, false);
            } else if (board.getCells()[y][x].getType() == CellTypes.FLAG) {
                if (board.getCells()[y][x].getValue().getNumber() == -1) {
                    board.getCells()[y][x].setType(CellTypes.MINE);
                } else {
                    board.getCells()[y][x].setType(CellTypes.NUMBER);
                }
                SoundPlayer.play(Sounds.FLAG, false);
                board.addNumberOfMines();
                board.getCells()[y][x].hide();
            }
        }

        boardPanel.repaint();
    }
}
