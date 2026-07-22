package utils;

import windows.BasicWindow;

import javax.sound.sampled.*;
import java.io.File;
import java.util.HashMap;

public class SoundPlayer {

    private final static HashMap<String, Clip> sounds = new HashMap<>();
    private static float volumeDb = -30f;

    public static void load(String path) {
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(new File(path));

            Clip clip = AudioSystem.getClip();
            clip.open(audio);

            sounds.put(path, clip);

        } catch (Exception e) {
            BasicWindow.showErrorMessage("Error while loading sounds!");
        }
    }

    public static void play(String name, boolean loop) {
        for (Clip clip : sounds.values()) {
            clip.stop();
        }

        Clip clip = sounds.get(name);

        if (clip == null) return;

        FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        control.setValue(volumeDb);

        clip.stop();
        clip.setFramePosition(0);
        clip.setMicrosecondPosition(0);

        if (loop) clip.loop(Clip.LOOP_CONTINUOUSLY);
        else clip.start();
    }

    public static void unpause(String path, boolean loop) {
        FloatControl control = (FloatControl) sounds.get(path).getControl(FloatControl.Type.MASTER_GAIN);
        control.setValue(volumeDb);

        if (sounds.get(path) != null) {
            if (loop) sounds.get(path).loop(Clip.LOOP_CONTINUOUSLY);
            else sounds.get(path).start();
        }
    }

    public static void pause(String path) {
        if (sounds.get(path) != null) {
            sounds.get(path).stop();
        }
    }

    public static void setVolumeDb(float volumeDb) {
        SoundPlayer.volumeDb = volumeDb;
    }

    public static float getVolumeDb() {
        return volumeDb;
    }
}
