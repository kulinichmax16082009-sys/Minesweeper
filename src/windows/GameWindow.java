package windows;

import gameObjects.Player;
import panels.BoardPanel;
import panels.EndPanel;
import panels.GamePanel;
import utils.Board;

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
    public String getImagePath() {
        return "resources/cellTypesIcons/mine.png";
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
