package windows;

import gameObjects.Player;
import panels.BoardPanel;
import panels.EndPanel;
import panels.GamePanel;
import utils.Board;
import utils.GameData;

public class GameWindow extends BasicWindow {
    private GamePanel gamePanel;
    private BoardPanel boardPanel;
    private EndPanel endPanel;

    public GameWindow(GameData gameData, Player player, Board board) {
        super("Minesweeper - Game");

        initPanels(board, player, gameData);

        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    private void initPanels(Board board, Player player, GameData gameData) {
        boardPanel = new BoardPanel(board, player);
        gamePanel = new GamePanel(boardPanel, player);
        endPanel = new EndPanel(boardPanel, gameData, player);

        frame.add(gamePanel);
        gamePanel.add(boardPanel);
        boardPanel.add(endPanel);

        gamePanel.setComponentZOrder(boardPanel, 0);
        boardPanel.setComponentZOrder(endPanel,0);
    }

    @Override
    public String getImagePath() {
        return "";
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public BoardPanel getBoardPanel() {
        return boardPanel;
    }

    public void setBoardPanel(BoardPanel boardPanel) {
        this.boardPanel = boardPanel;
    }

    public EndPanel getEndPanel() {
        return endPanel;
    }

    public void setEndPanel(EndPanel endPanel) {
        this.endPanel = endPanel;
    }
}
