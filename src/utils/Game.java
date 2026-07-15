package utils;

import enums.Difficulty;
import panels.GamePanel;
import utils.constants.Sounds;
import windows.EndWindow;
import windows.GameWindow;
import windows.IntroducingWindow;

public class Game {
    private static IntroducingWindow introducingWindow;
    private static GameWindow gameWindow;
    private static GamePanel gamePanel;
    private static Board board;
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

        board = new Board(difficulty);
        board.generateBoard(0, 0);

        gamePanel = new GamePanel(board);
        gameWindow = new GameWindow(gamePanel);
    }
}
