import utils.SoundPlayer;
import utils.constants.Sounds;
import windows.IntroducingWindow;

public class Main {
    public static void main(String[] args) {

        SoundPlayer.load(Sounds.MAIN_MENU);
        SoundPlayer.load(Sounds.NUMBER);
        SoundPlayer.load(Sounds.MINE);
        SoundPlayer.load(Sounds.FLAG);

        IntroducingWindow introducingWindow = new IntroducingWindow();
    }
}