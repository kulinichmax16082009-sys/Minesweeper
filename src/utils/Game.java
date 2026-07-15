package utils;

import enums.Difficulty;
import gameObjects.Player;
import panels.BoardPanel;
import panels.GamePanel;
import utils.constants.Sounds;
import windows.EndWindow;
import windows.GameWindow;
import windows.IntroducingWindow;

public class Game {
    private static IntroducingWindow introducingWindow;
    private static Player player;
    private static GameWindow gameWindow;
    private EndWindow endWindow;

    public void start() {
        SoundPlayer.load(Sounds.MAIN_MENU);
        SoundPlayer.load(Sounds.NUMBER);
        SoundPlayer.load(Sounds.MINE);
        SoundPlayer.load(Sounds.FLAG);

        introducingWindow = new IntroducingWindow();
    }

    public static void play() {
        Difficulty difficulty = introducingWindow.getDifficultyPanel().getSelectedDifficulty();
        player = new Player();

        Board board = new Board(difficulty);
        board.generateBoard(0, 0);

        BoardPanel boardPanel = new BoardPanel(board);
        GamePanel gamePanel = new GamePanel(board, boardPanel, player);
        gameWindow = new GameWindow(gamePanel);
    }
}
