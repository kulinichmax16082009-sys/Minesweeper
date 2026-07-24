package utils;

import enums.Difficulty;
import enums.MainMenuMusic;
import gameObjects.Player;
import utils.constants.*;
import windows.*;

import javax.swing.*;

/**
 * Game class represents the whole game mechanism.
 *
 * @author Maksym Kulynych
 */
public class Game {
    private static IntroducingWindow introducingWindow;
    private static Timer timer;
    private static Player player;
    private static Board board;
    private static GameWindow gameWindow;

    /**
     * This method initializes starting position of a game and loading sounds.
     */
    public static void start() {
        for (MainMenuMusic music : MainMenuMusic.values()) {
            SoundPlayer.load(music.getMusicPath());
        }
        SoundPlayer.load(Sounds.NUMBER);
        SoundPlayer.load(Sounds.MINE);
        SoundPlayer.load(Sounds.FLAG);
        SoundPlayer.load(Sounds.WON);

        introducingWindow = new IntroducingWindow();
    }

    /**
     * This method starts the game by initializing board and showing game window
     */
    public static void play() {
        Difficulty difficulty = introducingWindow.getDifficultyPanel().getSelectedDifficulty();

        board = new Board(difficulty);
        board.generateEmptyBoard();

        player = new Player(board.getNumberOfMines());

        gameWindow = new GameWindow(player, board);
    }

    /**
     * This method represents update timer that updates time and checks if game is over.
     */
    public static void startTimer() {
        timer = new Timer(1000, e -> {
            player.tickTime();
            gameWindow.getGamePanel().updateTimeLabel();
            gameOver();
        });

        timer.start();
    }

    /**
     * This method simply stops the timer.
     */
    private static void stopTimer() {
        if (timer != null) timer.stop();
    }

    /**
     * This method checks game over conditions to initialize end panel based on result.
     */
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
