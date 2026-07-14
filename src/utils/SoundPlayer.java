package utils;
import javax.sound.sampled.*;
import java.io.File;

public class SoundPlayer {
    private static Clip clip;

    public static void play(String path, boolean shouldLoop) {
        try {

            if (clip != null && clip.isRunning()) {
                clip.stop();
                clip.close();
            }

            AudioInputStream audio = AudioSystem.getAudioInputStream(new File(path));

            clip = AudioSystem.getClip();
            clip.open(audio);

            if (shouldLoop) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }

            clip.start();

        } catch (Exception e) {

            //Handle
            e.printStackTrace();
        }
    }

    public static void stop() {
        if (clip != null) {
            clip.stop();
            clip.close();
        }
    }
}
