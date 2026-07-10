package panels;

import gameObjects.Cell;
import utils.RandomGen;
import windows.IntroducingWindow;

import javax.swing.*;
import java.awt.*;

public class IntroducingPanel extends JPanel {
    private final RandomGen rnd;
    private Timer timer;

    public IntroducingPanel() {
        this.rnd = new RandomGen();

        animation();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < IntroducingWindow.HEIGHT; i += Cell.CELL_SIZE) {
            for (int j = 0; j < IntroducingWindow.WIDTH; j += Cell.CELL_SIZE) {
                Cell cell = rnd.randomCell();
                cell.paint(g, j, i);
            }
        }
    }

    private void animation() {
        if (timer == null) {
            timer = new Timer(1000, e -> repaint());
            timer.start();
        }
    }

    private void stopAnimation() {
        if (timer != null) {
            timer.stop();
            timer = null;
        }
    }
}
