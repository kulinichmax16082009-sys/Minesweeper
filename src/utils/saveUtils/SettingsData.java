package utils.saveUtils;
import enums.MainMenuMusic;

import java.io.*;

public class SettingsData extends Data implements Serializable {
    private int volume;
    private int animationSpeed;
    private MainMenuMusic selectedMusic;

    public SettingsData() {
        this.volume = -30;
        this.animationSpeed = 500;
        this.selectedMusic = MainMenuMusic.CLASSIC_RETRO;
    }

    @Override
    protected String getFilePath() {
        return "resources/settings.dat";
    }

    @Override
    protected Data createEmpty() {
        return new SettingsData();
    }

    public int getVolume() {
        return volume;
    }

    public int getAnimationSpeed() {
        return animationSpeed;
    }

    public MainMenuMusic getSelectedMusic() {
        return selectedMusic;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public void setAnimationSpeed(int animationSpeed) {
        this.animationSpeed = animationSpeed;
    }

    public void setSelectedMusic(MainMenuMusic selectedMusic) {
        this.selectedMusic = selectedMusic;
    }

    @Override
    public String toString() {
        return "SettingsData{" +
                "volume=" + volume +
                ", animationSpeed=" + animationSpeed +
                ", selectedMusic=" + selectedMusic +
                '}';
    }
}
