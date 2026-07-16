package utils;

import enums.Difficulty;
import gameObjects.Player;
import panels.BoardPanel;
import panels.GamePanel;
import utils.constants.Sounds;
import windows.EndWindow;
import windows.GameWindow;
import windows.IntroducingWindow;

import javax.swing.*;

public class Game {
    private static IntroducingWindow introducingWindow;
    private static Timer timer;
    private static Player player;
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
        player = new Player();

        board = new Board(difficulty);
        board.generateBoard(0, 0);

        BoardPanel boardPanel = new BoardPanel(board);
        gamePanel = new GamePanel(boardPanel, player);
        gameWindow = new GameWindow(gamePanel);
    }

    public static void startTimer() {
        timer = new Timer(1000, e -> {
            player.tickTime();
            gamePanel.updateTimeLabel(player.getTime());

            if (player.isDead()) {
                stopTimer();
            }

            if (board.isWin()) {
                stopTimer();
            }
//            gameData.update(player, boardManager);
            gameOver();
        });

        timer.start();
    }

    private static void stopTimer() {
        if (timer != null) timer.stop();
    }

    private static void gameOver() {

    }
}
