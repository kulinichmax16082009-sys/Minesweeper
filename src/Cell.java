import javax.swing.*;
import java.awt.*;

public class Cell {
    private int value;
    private CellTypes type;

    private final Image icon;

    public static final int CELL_SIZE = 32;

    public Cell(CellTypes type) {
        if (type == CellTypes.MINE) this.value = -1;
        else this.value = 0;

        Image mine = new ImageIcon("resources/cellTypesIcons/mine.png").getImage();
        Image empty = new ImageIcon("resources/cellTypesIcons/empty.png").getImage();
        Image flagged = new ImageIcon("resources/cellTypesIcons/flagged.png").getImage();

        switch (type) {
            case MINE:
                icon = mine;
                break;
            case EMPTY:
                icon = empty;
                break;
            case FLAGGED:
                icon = flagged;
                break;
            default:
                icon = null;
        }

        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public CellTypes getType() {
        return type;
    }

    public void setType(CellTypes type) {
        this.type = type;
    }

    public void paint(Graphics g, int x, int y) {
        Graphics2D g2d = (Graphics2D) g;

        if (icon == null) {
            g2d.drawRect(x, y, CELL_SIZE, CELL_SIZE);
        } else {
            g2d.drawImage(icon, x, y, null);
        }
    }

    @Override
    public String toString() {
        return "Cell{" +
                "value=" + value +
                ", type=" + type +
                '}';
    }
}
