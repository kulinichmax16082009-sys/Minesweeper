package windows;

import panels.BoardPanel;
import utils.Board;
import utils.constants.Images;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

/**
 * BoardWindow class is used for showing user a board in stats.
 *
 * @author Maksym Kulynych
 */
public class BoardWindow extends BasicWindow {

    public BoardWindow(Board board) {
        super("Minesweeper - Board");

        board.revealAll();

        BoardPanel boardPanel = new BoardPanel(board, null);

        for (MouseListener listener : boardPanel.getMouseListeners()) {
            boardPanel.removeMouseListener(listener);
        }

        frame.add(boardPanel);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    @Override
    public Image getImageIcon() {
        return Images.HIDDEN;
    }
}
