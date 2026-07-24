package gameObjects;

import utils.constants.Images;
import java.awt.*;

/**
 * Player class represents player in game with its parameters.
 *
 * @author Maksym Kulynych
 */
public class Player {
    private Image icon;
    private int flagsLeft;
    private long time;

    public Player(int flagsLeft) {
        time = 0;
        this.flagsLeft = flagsLeft;
    }

    /**
     * This method sets players icon based on its state in game.
     * @param isDead boolean that checks if player has dead
     * @param isWon boolean that checks if player has won
     */
    public void setPlayerIcon(boolean isDead, boolean isWon) {
        if (isWon && !isDead) {
            icon = Images.PLAYER_WON;
            return;
        }

        if (isDead) icon = Images.PLAYER_DEAD;
        else icon = Images.PLAYER_ALIVE;
    }

    /**
     * This method simply decrements number of flags.
     */
    public void subtractFlagsLeft() {
        flagsLeft--;
    }

    /**
     * This method simply increments number of flags.
     */
    public void addFlagsLeft() {
        flagsLeft++;
    }

    /**
     * This method simply increments time.
     */
    public void tickTime() {
        time++;
    }

    public Image getIcon() {
        return icon;
    }

    public int getFlagsLeft() {
        return flagsLeft;
    }

    public long getTime() {
        return time;
    }
}
