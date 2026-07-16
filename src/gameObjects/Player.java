package gameObjects;

import utils.constants.Images;
import java.awt.*;

public class Player {
    private boolean isDead;
    private Image icon;
    private int flagsLeft;
    private long time;

    public Player(int flagsLeft) {
        isDead = false;
        setPlayerIcon();
        time = 0;
        this.flagsLeft = flagsLeft;
    }

    public void setPlayerIcon() {
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

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
        setPlayerIcon();
    }

    public Image getIcon() {
        return icon;
    }

    public void setIcon(Image icon) {
        this.icon = icon;
    }

    public int getFlagsLeft() {
        return flagsLeft;
    }

    public long getTime() {
        return time;
    }
}
