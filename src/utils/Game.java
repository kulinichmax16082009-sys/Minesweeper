package utils;

import enums.Difficulty;
import gameObjects.Player;
import panels.BoardPanel;
import panels.GamePanel;
import utils.constants.Images;
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
        SoundPlayer.load(Sounds.WON);

        introducingWindow = new IntroducingWindow();
    }

    public static void play() {
        Difficulty difficulty = introducingWindow.getDifficultyPanel().getSelectedDifficulty();

        board = new Board(difficulty);
        board.generateEmptyBoard();

        player = new Player(board.getNumberOfMines());

        BoardPanel boardPanel = new BoardPanel(board, player);
        gamePanel = new GamePanel(boardPanel, player);
        gameWindow = new GameWindow(gamePanel);
    }

    public static void startTimer() {
        timer = new Timer(1000, e -> {
            player.tickTime();
            gamePanel.updateTimeLabel();
//            gameData.update(player, boardManager);
            gameOver();
        });

        timer.start();
    }

    private static void stopTimer() {
        if (timer != null) timer.stop();
    }

    private static void gameOver() {
        if (player.isDead()) {
            stopTimer();
        }

        if (board.isWin()) {
            SoundPlayer.play(Sounds.WON, false);
            player.setIcon(Images.PLAYER_WON);
            stopTimer();
        }
    }
}
