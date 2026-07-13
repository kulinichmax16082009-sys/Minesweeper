package utils;
import javax.sound.sampled.*;

public class SoundPlayer {
    private static Clip clip;

    public static void initAudio(AudioInputStream audio, boolean shouldLoop) {
        try {

            if (clip == null) {
                clip = AudioSystem.getClip();
            }

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

    public static void unpause() {
        if (clip != null) clip.start();
    }


    public static void pause() {
        if (clip != null) clip.stop();
    }
}
