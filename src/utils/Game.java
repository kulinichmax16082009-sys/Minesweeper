package utils;

import enums.Difficulty;
import enums.MainMenuMusic;
import gameObjects.Player;
import panels.*;
import utils.constants.*;
import windows.*;

import javax.swing.*;

public class Game {
    private static IntroducingWindow introducingWindow;
    private static Timer timer;
    private static Player player;
    private static Board board;
    private static GameWindow gameWindow;

    public static void start() {
        for (MainMenuMusic music : MainMenuMusic.values()) {
            SoundPlayer.load(music.getPath());
        }
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

        gameWindow = new GameWindow(player, board);
    }

    public static void startTimer() {
        timer = new Timer(1000, e -> {
            player.tickTime();
            gameWindow.getGamePanel().updateTimeLabel();
            gameOver();
        });

        timer.start();
    }

    private static void stopTimer() {
        if (timer != null) timer.stop();
    }

    private static void gameOver() {
        if (board.isDead() || board.isWin()) {
            if (board.isDead()) {
                gameWindow.getEndPanel().setGoodEnd(false);
            } else if (board.isWin()) {
                SoundPlayer.play(Sounds.WON, false);
                gameWindow.getEndPanel().setGoodEnd(true);
            }
            stopTimer();
            gameWindow.getBoardPanel().setPaused(gameWindow.getEndPanel(), true);
        }
    }
}
