package windows;

import gameObjects.Player;
import panels.BoardPanel;
import panels.EndPanel;
import panels.GamePanel;
import utils.Board;
import utils.constants.Images;

import java.awt.*;

/**
 * GameWindow class represents the whole game and also all panels used in it.
 *
 * @author Maksym Kulynych
 */
public class GameWindow extends BasicWindow {
    private GamePanel gamePanel;
    private BoardPanel boardPanel;
    private EndPanel endPanel;

    public GameWindow(Player player, Board board) {
        super("Minesweeper - Game");

        initPanels(board, player);

        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    /**
     * This method initializes all panels used in game window by adding them to each other and setting order.
     * @param board used in boardPanel to show board
     * @param player used in all panels to set players parameters
     */
    private void initPanels(Board board, Player player) {
        boardPanel = new BoardPanel(board, player);
        gamePanel = new GamePanel(boardPanel, player);
        endPanel = new EndPanel(boardPanel, player);

        frame.add(gamePanel);
        gamePanel.add(boardPanel);
        boardPanel.add(endPanel);

        gamePanel.setComponentZOrder(boardPanel, 0);
        boardPanel.setComponentZOrder(endPanel,0);
    }

    @Override
    public Image getImageIcon() {
        return Images.MINE;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public BoardPanel getBoardPanel() {
        return boardPanel;
    }

    public EndPanel getEndPanel() {
        return endPanel;
    }
}
