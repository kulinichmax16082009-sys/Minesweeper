package gameObjects;

import utils.constants.Images;
import java.awt.*;

public class Player {
    private boolean isDead;
    private Image icon;
    private int score;
    private long time;

    public Player() {
        isDead = false;
        setPlayerIcon();
        time = 0;
        score = 0;
    }

    public void setPlayerIcon() {
        if (isDead) icon = Images.PLAYER_DEAD;
        else icon = Images.PLAYER_ALIVE;
    }

    public void addScore(int amount) {
        score += amount;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
