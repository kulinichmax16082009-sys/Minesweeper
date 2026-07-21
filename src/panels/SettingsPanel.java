package panels;

import utils.constants.Sounds;
import utils.simpleUI.SimpleButton;
import utils.simpleUI.SimpleLabel;
import utils.SoundPlayer;

import javax.swing.*;
import java.awt.*;

public class SettingsPanel extends JPanel {
    public final static float SIZE_FACTOR = 1.5f;
    public final static int HEIGHT = (int) (IntroducingPanel.HEIGHT / SIZE_FACTOR);
    public final static int WIDTH = (int) (IntroducingPanel.WIDTH / SIZE_FACTOR);

    private final int TITLE_WIDTH = 200;
    private final int TITLE_HEIGHT = 50;
    private final int TITLE_FONT_SIZE = 30;

    private final int SLIDER_WIDTH = 200;
    private final int SLIDER_HEIGHT = 20;
    private final float SLIDER_DISTANCE_FACTOR = 1.5f;
    private final int SLIDER_GAP = (int) (SLIDER_HEIGHT * SLIDER_DISTANCE_FACTOR);

    private final int BUTTON_WIDTH = (int) (IntroducingPanel.BUTTON_WIDTH / SIZE_FACTOR);
    private final int BUTTON_HEIGHT = (int) (IntroducingPanel.BUTTON_HEIGHT / SIZE_FACTOR);
    public static final int BUTTON_FONT_SIZE = (int) (IntroducingPanel.BUTTON_FONT_SIZE / SIZE_FACTOR);

    private IntroducingPanel introducingPanel;
    private JSlider volume;
    private JSlider animationSpeed;

    public SettingsPanel(IntroducingPanel introducingPanel) {
        this.introducingPanel = introducingPanel;

        setBounds((IntroducingPanel.WIDTH - WIDTH) / 2, (IntroducingPanel.HEIGHT - HEIGHT) / 2, WIDTH, HEIGHT);
        setLayout(null);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setVisible(false);

        initBackButton();

        initAnimationSpeedSlider();
        initVolumeSlider();

        initTitleLabel();
        setBackground(new Color(191, 191, 191));
    }

    private void initAnimationSpeedSlider() {
        SimpleLabel speedLabel = SimpleLabel.createTitleLabel(20, TITLE_HEIGHT * 2, 100, SLIDER_HEIGHT,
                new Color(0,0,0), new Color(154, 154, 154), "Speed", new Font("Arial", Font.BOLD, 18)
        );

        add(speedLabel);

        animationSpeed = new JSlider(0, 1000, 1000 - IntroducingPanel.getAnimationSpeed());
        animationSpeed.setMajorTickSpacing(100);
        animationSpeed.setSize(SLIDER_WIDTH, SLIDER_HEIGHT);
        animationSpeed.setLocation(WIDTH / 2 - SLIDER_WIDTH / 2, TITLE_HEIGHT * 2);
        animationSpeed.addChangeListener(e -> {
            introducingPanel.stopAnimation();
            IntroducingPanel.setAnimationSpeed((animationSpeed.getMaximum() - animationSpeed.getValue()));
            introducingPanel.startAnimation();
            if (animationSpeed.getValue() == 0) introducingPanel.stopAnimation();
        });
        animationSpeed.setBackground(new Color(154, 154, 154));
        add(animationSpeed);
    }

    private void initVolumeSlider() {
        SimpleLabel volumeLabel = SimpleLabel.createTitleLabel(20, animationSpeed.getY() + SLIDER_GAP, 100, SLIDER_HEIGHT,
                new Color(0,0,0), new Color(154, 154, 154), "Volume", new Font("Arial", Font.BOLD, 18)
        );

        add(volumeLabel);

        volume = new JSlider(0, 60, (int) (60 - -1 * SoundPlayer.getVolumeDb()));
        volume.setMajorTickSpacing(1);
        volume.setSize(SLIDER_WIDTH, SLIDER_HEIGHT);
        volume.setLocation(WIDTH / 2 - SLIDER_WIDTH / 2, animationSpeed.getY() + SLIDER_GAP);
        volume.addChangeListener(e -> {
            SoundPlayer.pause(Sounds.MAIN_MENU);
            SoundPlayer.setVolumeDb(-1 * (volume.getMaximum() - volume.getValue()));
            SoundPlayer.unpause(Sounds.MAIN_MENU, true);
        });
        volume.setBackground(new Color(154, 154, 154));
        add(volume);
    }

    private void initTitleLabel() {
        SimpleLabel titleLabel = SimpleLabel.createTitleLabel(WIDTH / 2 - TITLE_WIDTH / 2, TITLE_HEIGHT / 2, TITLE_WIDTH, TITLE_HEIGHT,
                new Color(0, 0, 0), new Color(0, 0, 240), "Settings", new Font("Arial", Font.BOLD, TITLE_FONT_SIZE));

        add(titleLabel);
    }

    private void initBackButton() {
        SimpleButton back = SimpleButton.createButton("Back", BUTTON_WIDTH, BUTTON_HEIGHT, e -> introducingPanel.setPaused(this, false));
        back.setDesign(new Color(0, 0, 240), new Color(0, 0, 0), BUTTON_FONT_SIZE, "Arial");
        back.setLocation(10, HEIGHT - 10 - BUTTON_HEIGHT);

        add(back);
    }

    public void setIntroducingPanel(IntroducingPanel introducingPanel) {
        this.introducingPanel = introducingPanel;
    }
}