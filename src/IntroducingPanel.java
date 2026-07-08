import javax.swing.*;
import java.awt.*;

public class IntroducingPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {

        Cell cell = new Cell(CellTypes.BOMB);

        for (int i = 0; i < IntroducingWindow.HEIGHT; i += Cell.CELL_SIZE) {
            for (int j = 0; j < IntroducingWindow.WIDTH; j += Cell.CELL_SIZE) {
                cell.paint(g, new Coordinates(j, i));
            }
        }

        repaint();
    }
}
