package utils.constants;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Sounds {
    public final static AudioInputStream MINE = load("resources/sounds/explosion.wav");
    public final static AudioInputStream SHOVEL = load("resources/sounds/shovel.wav");
    public final static AudioInputStream FLAG = load("resources/sounds/flag.wav");
    public final static AudioInputStream MAIN_MENU = load("resources/sounds/mainMenu.wav");

    private static AudioInputStream load(String path) {

        File file = new File(path);
        if (!file.exists()) return null;

        AudioInputStream audioStream = null;
        try {
            audioStream = AudioSystem.getAudioInputStream(file);
        } catch (Exception e) {
            //Handle
            e.printStackTrace();
        }

        return audioStream;
    }
}
