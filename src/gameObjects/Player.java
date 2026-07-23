package gameObjects;

import utils.constants.Images;
import java.awt.*;

public class Player {
    private Image icon;
    private int flagsLeft;
    private long time;

    public Player(int flagsLeft) {
        time = 0;
        this.flagsLeft = flagsLeft;
    }

    public void setPlayerIcon(boolean isDead, boolean isWon) {
        if (isWon && !isDead) {
            icon = Images.PLAYER_WON;
            return;
        }

        if (isDead) icon = Images.PLAYER_DEAD;
        else icon = Images.PLAYER_ALIVE;
    }

    public void subtractFlagsLeft() {
        flagsLeft--;
    }

    public void addFlagsLeft() {
        flagsLeft++;
    }

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
