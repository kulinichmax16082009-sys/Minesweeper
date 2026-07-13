package windows;

import panels.GamePanel;

public class GameWindow extends BasicWindow {
    private GamePanel gamePanel;

    public GameWindow(GamePanel gamePanel) {
        super("Minesweeper - Game");

        this.gamePanel = gamePanel;
        frame.add(gamePanel);

        frame.pack();
        frame.setLocationRelativeTo(null);
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
}
