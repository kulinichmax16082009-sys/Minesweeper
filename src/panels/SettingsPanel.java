package panels;

import utils.constants.Sounds;
import utils.simpleUI.SimpleButton;
import utils.simpleUI.SimpleCheckBox;
import utils.simpleUI.SimpleLabel;
import utils.SoundPlayer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SettingsPanel extends JPanel {
    public final static float SIZE_FACTOR = 1.5f;
    public final static int HEIGHT = (int) (IntroducingPanel.HEIGHT / SIZE_FACTOR);
    public final static int WIDTH = (int) (IntroducingPanel.WIDTH / SIZE_FACTOR);

    private final int TITLE_WIDTH = 200;
    private final int TITLE_HEIGHT = 50;
    private final int TITLE_FONT_SIZE = 30;

    private final int CHECK_BOX_WIDTH = 200;
    private final int CHECK_BOX_HEIGHT = 15;
    private final float CHECK_BOX_DISTANCE_FACTOR = 1.5f;
    private final int CHECK_BOX_GAP = (int) (CHECK_BOX_HEIGHT * CHECK_BOX_DISTANCE_FACTOR);

    private final int BUTTON_WIDTH = (int) (IntroducingPanel.BUTTON_WIDTH / SIZE_FACTOR);
    private final int BUTTON_HEIGHT = (int) (IntroducingPanel.BUTTON_HEIGHT / SIZE_FACTOR);
    public static final int BUTTON_FONT_SIZE = (int) (IntroducingPanel.BUTTON_FONT_SIZE / SIZE_FACTOR);

    private IntroducingPanel introducingPanel;
    private JLabel titleLabel;
    private ArrayList<SimpleCheckBox> checkBoxes;

    public SettingsPanel(IntroducingPanel introducingPanel) {
        this.introducingPanel = introducingPanel;

        setBounds((IntroducingPanel.WIDTH - WIDTH) / 2, (IntroducingPanel.HEIGHT - HEIGHT) / 2, WIDTH, HEIGHT);
        setLayout(null);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setVisible(false);

        initBackButton();

        initCheckBoxes();
        setCheckBoxesLocation();
        addCheckBoxesToPanel();

        initTitleLabel();
    }

    private void initTitleLabel() {
        titleLabel = SimpleLabel.createTitleLabel(WIDTH / 2 - TITLE_WIDTH / 2, TITLE_HEIGHT / 2, TITLE_WIDTH, TITLE_HEIGHT,
                new Color(0, 0, 0), new Color(0, 0, 240), "Settings", new Font("Arial", Font.BOLD, TITLE_FONT_SIZE));

        add(titleLabel);
    }

    private void initCheckBoxes() {
        checkBoxes = new ArrayList<>();

        checkBoxes.add(SimpleCheckBox.createCheckBox("Play Background Animation", CHECK_BOX_WIDTH, CHECK_BOX_HEIGHT, true, e -> {
            if (((SimpleCheckBox) e.getSource()).isSelected()) introducingPanel.startAnimation();
            else introducingPanel.stopAnimation();
        }));

        checkBoxes.add(SimpleCheckBox.createCheckBox("Play Menu Music", CHECK_BOX_WIDTH, CHECK_BOX_HEIGHT, true, e -> {
            if (((SimpleCheckBox) e.getSource()).isSelected()) SoundPlayer.play(Sounds.MAIN_MENU, true);
            else SoundPlayer.stop();
        }));
    }

    private void setCheckBoxesLocation() {
        for (int i = 0; i < checkBoxes.size(); i++) {
            checkBoxes.get(i).setLocation(WIDTH / 2 - CHECK_BOX_WIDTH / 2, 2 * TITLE_HEIGHT + i * CHECK_BOX_GAP);
        }
    }

    private void addCheckBoxesToPanel() {
        for (SimpleCheckBox checkBox : checkBoxes) add(checkBox);
    }

    private void initBackButton() {
        SimpleButton back = SimpleButton.createButton("Back", BUTTON_WIDTH, BUTTON_HEIGHT, e -> introducingPanel.setPaused(this, false));
        back.setDesign(new Color(0, 0, 240), new Color(0, 0, 0), BUTTON_FONT_SIZE, "Arial");
        back.setLocation(10, HEIGHT - 10 - BUTTON_HEIGHT);

        add(back);
    }

    public IntroducingPanel getIntroducingPanel() {
        return introducingPanel;
    }

    public void setIntroducingPanel(IntroducingPanel introducingPanel) {
        this.introducingPanel = introducingPanel;
    }
}
