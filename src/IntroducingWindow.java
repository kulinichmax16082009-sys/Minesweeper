import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class IntroducingWindow extends BasicWindow {

    public final static int BUTTON_WIDTH = 200;
    public final static int BUTTON_HEIGHT = 50;
    public final static float BUTTON_DISTANCE_FACTOR = 1.5f;
    private static final int BUTTON_X = 40;

    private static final int TITLE_WIDTH = 200;
    private static final int TITLE_HEIGHT = 50;
    private static final int TITLE_FONT_SIZE = 30;

    private IntroducingPanel panel;
    private ArrayList<JButton> buttons;

    public IntroducingWindow() {
        super("Minesweeper - Main Menu", 700, 400);

        initIntroducingPanel();

        initButtons();
        setButtonsSize();
        setButtonsLocation();
        addButtonsToPanel();

        initTitleLabel();

        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    private void initButtons() {
        buttons = new ArrayList<>();

        buttons.add(createButton("Start", e -> {
            // Handle start game action
            System.out.println("Start Game button clicked");
        }));

        buttons.add(createButton("Settings", e -> {
            // Handle settings action
            System.out.println("Settings button clicked");
        }));

        buttons.add(createButton("Difficulty", e -> {
            // Handle difficulty action
            System.out.println("Difficulty button clicked");
        }));

        buttons.add(createButton("Quit", e -> System.exit(0)));
    }

    private JButton createButton(String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        button.addActionListener(listener);
        return button;
    }

    private void setButtonsSize() {
        for (JButton button : buttons) button.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
    }

    private void setButtonsLocation() {
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setLocation(BUTTON_X, (int) ((float) (height - buttons.size() * BUTTON_HEIGHT) / buttons.size() + i * (BUTTON_DISTANCE_FACTOR * BUTTON_HEIGHT)));
        }
    }

    private void addButtonsToPanel() {
        for (JButton button : buttons) panel.add(button);
    }

   private void initIntroducingPanel() {
        panel = new IntroducingPanel();
        panel.setPreferredSize(new Dimension(width, height));
        panel.setLayout(null);
        frame.add(panel, BorderLayout.CENTER);
    }

    private void initTitleLabel() {
        JLabel titleLabel = new JLabel("Minesweeper", JLabel.CENTER);

        titleLabel.setFont(new Font("Arial", Font.BOLD, TITLE_FONT_SIZE));

        titleLabel.setBounds(width - TITLE_WIDTH - 10, height / 2 - TITLE_HEIGHT / 2, TITLE_WIDTH, TITLE_HEIGHT);

        panel.add(titleLabel);
    }

    @Override
    public String getImagePath() {
        return "";
    }
}
