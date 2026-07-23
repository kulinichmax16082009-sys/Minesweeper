package panels;

import enums.MainMenuMusic;
import utils.saveUtils.SettingsData;
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

    private final int CHOOSER_HEIGHT = 20;
    private final int CHOOSER_WIDTH = 200;

    private final int BUTTON_WIDTH = (int) (IntroducingPanel.BUTTON_WIDTH / SIZE_FACTOR);
    private final int BUTTON_HEIGHT = (int) (IntroducingPanel.BUTTON_HEIGHT / SIZE_FACTOR);
    public static final int BUTTON_FONT_SIZE = (int) (IntroducingPanel.BUTTON_FONT_SIZE / SIZE_FACTOR);

    private IntroducingPanel introducingPanel;
    private JSlider volume;
    private JSlider animationSpeed;
    private JComboBox<MainMenuMusic> musicChooser;
    private MainMenuMusic selectedMusic;

    public SettingsPanel(IntroducingPanel introducingPanel) {
        this.introducingPanel = introducingPanel;

        SettingsData settingsData = (SettingsData) new SettingsData().loadData();

        setBounds((IntroducingPanel.WIDTH - WIDTH) / 2, (IntroducingPanel.HEIGHT - HEIGHT) / 2, WIDTH, HEIGHT);
        setLayout(null);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setVisible(false);

        initBackButton();

        initAnimationSpeedSlider(settingsData);
        initVolumeSlider(settingsData);

        initTitleLabel();

        initMusicChooser(settingsData);
        setBackground(new Color(191, 191, 191));
        SoundPlayer.play(selectedMusic.getPath(), true);
    }

    private void initMusicChooser(SettingsData settingsData) {
        SimpleLabel musicLabel = SimpleLabel.createTitleLabel(20, volume.getY() + SLIDER_GAP, 100, SLIDER_HEIGHT,
                new Color(0,0,0), new Color(154, 154, 154), "Music", new Font("Arial", Font.BOLD, 18)
        );

        add(musicLabel);

        selectedMusic = settingsData.getSelectedMusic();

        musicChooser = new JComboBox<>(MainMenuMusic.values());
        musicChooser.setSelectedItem(selectedMusic);

        musicChooser.addActionListener(e -> {
            selectedMusic = (MainMenuMusic) musicChooser.getSelectedItem();
            SoundPlayer.play(selectedMusic != null ? selectedMusic.getPath() : null, true);
            settingsData.setSelectedMusic(selectedMusic);
            settingsData.saveData();
        });

        musicChooser.setLocation(WIDTH / 2 - CHOOSER_WIDTH / 2,volume.getY() + SLIDER_GAP);
        musicChooser.setSize(CHOOSER_WIDTH,CHOOSER_HEIGHT);
        musicChooser.setBackground(new Color(154, 154, 154));
        add(musicChooser);
    }

    private void initAnimationSpeedSlider(SettingsData settingsData) {
        SimpleLabel speedLabel = SimpleLabel.createTitleLabel(20, TITLE_HEIGHT * 2, 100, SLIDER_HEIGHT,
                new Color(0,0,0), new Color(154, 154, 154), "Speed", new Font("Arial", Font.BOLD, 18)
        );

        add(speedLabel);

        animationSpeed = new JSlider(0, 1000, 1000 - settingsData.getAnimationSpeed());
        IntroducingPanel.setAnimationSpeed((animationSpeed.getMaximum() - animationSpeed.getValue()));
        animationSpeed.setMajorTickSpacing(100);
        animationSpeed.setSize(SLIDER_WIDTH, SLIDER_HEIGHT);
        animationSpeed.setLocation(WIDTH / 2 - SLIDER_WIDTH / 2, TITLE_HEIGHT * 2);
        animationSpeed.addChangeListener(e -> {
            introducingPanel.stopAnimation();
            int result = animationSpeed.getMaximum() - animationSpeed.getValue();
            IntroducingPanel.setAnimationSpeed(result);
            settingsData.setAnimationSpeed(result);
            settingsData.saveData();
            introducingPanel.startAnimation();
            if (animationSpeed.getValue() == 0) introducingPanel.stopAnimation();
        });
        animationSpeed.setBackground(new Color(154, 154, 154));
        add(animationSpeed);
    }

    private void initVolumeSlider(SettingsData settingsData) {
        SimpleLabel volumeLabel = SimpleLabel.createTitleLabel(20, animationSpeed.getY() + SLIDER_GAP, 100, SLIDER_HEIGHT,
                new Color(0,0,0), new Color(154, 154, 154), "Volume", new Font("Arial", Font.BOLD, 18)
        );

        add(volumeLabel);

        volume = new JSlider(0, 60,  60 - -1 * settingsData.getVolume());
        SoundPlayer.setVolumeDb(-1 * (volume.getMaximum() - volume.getValue()));
        volume.setMajorTickSpacing(1);
        volume.setSize(SLIDER_WIDTH, SLIDER_HEIGHT);
        volume.setLocation(WIDTH / 2 - SLIDER_WIDTH / 2, animationSpeed.getY() + SLIDER_GAP);
        volume.addChangeListener(e -> {
            int result = -1 * (volume.getMaximum() - volume.getValue());
            SoundPlayer.setVolumeDb(result);
            settingsData.setVolume(result);
            settingsData.saveData();
            SoundPlayer.unpause(selectedMusic.getPath(), true);
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

    public MainMenuMusic getSelectedMusic() {
        return selectedMusic;
    }
}