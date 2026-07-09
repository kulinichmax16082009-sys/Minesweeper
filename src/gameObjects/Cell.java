package gameObjects;
import enums.CellTypes;
import javax.swing.*;
import java.awt.*;

public class Cell {
    private Value value;
    private boolean isRevealed;
    private CellTypes type;
    private Image icon;

    public static final int CELL_SIZE = 32;

    private final Image mine = new ImageIcon("resources/cellTypesIcons/mine.png").getImage();
    private final Image hidden = new ImageIcon("resources/cellTypesIcons/hidden.png").getImage();
    private final Image flagged = new ImageIcon("resources/cellTypesIcons/flagged.png").getImage();
    private final Image number = new ImageIcon("resources/cellTypesIcons/number.png").getImage();

    public Cell(CellTypes type, Value value, boolean isRevealed) {
        if (type == CellTypes.MINE) this.value = null;
        else this.value = value;

        if (!isRevealed) this.icon = hidden;
        else setIconByType(type);

        this.type = type;
        this.isRevealed = isRevealed;
    }

    private void setIconByType(CellTypes type) {
        switch (type) {
            case MINE:
                icon = mine;
                break;
            case FLAGGED:
                icon = flagged;
                break;
            case NUMBER:
                icon = number;
                break;
            default:
                icon = null;
        }
    }

    public void paint(Graphics g, int x, int y) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        if (icon == null) {
            g2d.drawRect(x, y, CELL_SIZE, CELL_SIZE);
        } else {
            g2d.drawImage(icon, x, y, null);

            if (type.equals(CellTypes.NUMBER) && isRevealed) {
                g2d.setColor(value.getColor());
                g2d.setFont(new Font("Arial", Font.BOLD, 30));

                String s = String.valueOf(value.getNumber());
                FontMetrics fm = g2d.getFontMetrics();
                int strWidth = fm.stringWidth(s);
                int strHeight = -(int) fm.getLineMetrics(s, g2d).getBaselineOffsets()[2];
                g2d.drawString(s, x + (CELL_SIZE - strWidth) / 2, y + CELL_SIZE - (CELL_SIZE - strHeight) / 2 - 5);
            }
        }
    }

    public void reveal() {
        if (!isRevealed) {
            setIconByType(type);
            isRevealed = true;
        }
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public CellTypes getType() {
        return type;
    }

    public void setType(CellTypes type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "value=" + value +
                ", type=" + type +
                '}';
    }
}