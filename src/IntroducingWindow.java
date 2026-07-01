import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class IntroducingWindow extends BasicWindow {

    public final static int BUTTON_WIDTH = 200;
    public final static int BUTTON_HEIGHT = 50;
    public final static float BUTTON_DISTANCE_FACTOR = 1.5f;

    private ArrayList<JButton> buttons;

    public IntroducingWindow() {
        super("Minesweeper - Main Menu", 400, 400);

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(width, height));
        frame.add(panel);

        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    public void initButtons() {
        buttons = new ArrayList<>();

        //Start button
        JButton start = new JButton("Start Game");

        start.addActionListener(e -> {
            // Handle start game action
            System.out.println("Start Game button clicked");
        });

        start.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        buttons.add(start);

        //Difficulty button
        JButton difficulty = new JButton("Difficulty");

        difficulty.addActionListener(e -> {
            // Handle load game action
            System.out.println("Difficulty button clicked");
        });

        difficulty.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        buttons.add(difficulty);

        //Quit button
        JButton quit = new JButton("Quit");
        quit.addActionListener(e -> System.exit(0));
        quit.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        buttons.add(quit);
    }

    public void placeButtons(JPanel panel) {
    }

    public void addButtonsToPanel(JPanel panel) {
        for (JButton button : buttons) {
            panel.add(button);
        }
    }

    public int finalWindowSize() {
        return 0;
    }

    @Override
    public String getImagePath() {
        return "";
    }
}
