package utils;

import javax.sound.sampled.*;
import java.io.File;
import java.util.HashMap;

public class SoundPlayer {

    private final static HashMap<String, Clip> sounds = new HashMap<>();

    public static void load(String path) {
        try {
            AudioInputStream audio =
                    AudioSystem.getAudioInputStream(new File(path));

            Clip clip = AudioSystem.getClip();
            clip.open(audio);

            sounds.put(path, clip);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void play(String name, boolean loop) {
        Clip clip = sounds.get(name);

        if (clip == null) return;

        clip.stop();
        clip.setFramePosition(0);
        clip.setMicrosecondPosition(0);

        if (loop) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } else {
            clip.start();
        }
    }

    public static void unpause(String path) {
        if (sounds.get(path) != null) {
            sounds.get(path).start();
        }
    }

    public static void pause(String path) {
        if (sounds.get(path) != null) {
            sounds.get(path).stop();
        }
    }
}
