package utils;

import windows.BasicWindow;

import javax.sound.sampled.*;
import java.util.HashMap;

/**
 * SoundPlayer class is used for playing all sounds used in game.
 *
 * @author Maksym Kulynych
 */
public class SoundPlayer {
    private final static HashMap<String, Clip> sounds = new HashMap<>();
    private static float volumeDb;

    /**
     * This method adds new sound to a HashMap by path.
     * @param path path to a special sound or music
     */
    public static void load(String path) {
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(SoundPlayer.class.getResource(path));

            Clip clip = AudioSystem.getClip();
            clip.open(audio);

            sounds.put(path, clip);

        } catch (Exception e) {
            BasicWindow.showErrorMessage("Error while loading " + path + " sound!");
        }
    }

    /**
     * This method initializes new sound and start playing it from first frame with special volume.
     * @param path path to a sound that must be played
     * @param loop boolean that loops the sound if needed
     */
    public static void play(String path, boolean loop) {
        for (Clip clip : sounds.values()) clip.stop();

        Clip clip = sounds.get(path);

        if (clip == null) return;

        FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        control.setValue(volumeDb);

        clip.stop();
        clip.setFramePosition(0);
        clip.setMicrosecondPosition(0);

        if (loop) clip.loop(Clip.LOOP_CONTINUOUSLY);
        else clip.start();
    }

    /**
     * This method unpauses sound from the frame it was paused.
     * @param path path to a sound that must be unpaused
     * @param loop boolean that loops the sound if needed
     */
    public static void unpause(String path, boolean loop) {
        if (sounds.get(path) == null) return;

        FloatControl control = (FloatControl) sounds.get(path).getControl(FloatControl.Type.MASTER_GAIN);
        control.setValue(volumeDb);

        if (sounds.get(path) != null) {
            if (loop) sounds.get(path).loop(Clip.LOOP_CONTINUOUSLY);
            else sounds.get(path).start();
        }
    }

    /**
     * This method simply pauses sound on current frame
     * @param path path to a sound that must be paused
     */
    public static void pause(String path) {
        if (sounds.get(path) != null) {
            sounds.get(path).stop();
        }
    }

    /**
     * This method sets volume of all sounds.
     * @param volumeDb volume that must be set
     */
    public static void setVolumeDb(float volumeDb) {
        SoundPlayer.volumeDb = volumeDb;
    }
}
