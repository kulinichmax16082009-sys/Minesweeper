package gameObjects;
import enums.CellTypes;
import utils.constants.Images;

import java.awt.*;
import java.io.Serializable;

/**
 * Cell class represents cell in board with its type, icon, sound, etc.
 *
 * @author Maksym Kulynych
 */
public class Cell implements Serializable {
    private Value value;
    private boolean isRevealed;
    private CellTypes type;
    private transient Image icon;
    private String sound;

    public static final int CELL_SIZE = 32;

    public Cell(CellTypes type, Value value, boolean isRevealed) {
        if (type == CellTypes.MINE) this.value = new Value(-1);
        else this.value = value;

        this.type = type;
        this.isRevealed = isRevealed;

        if (!isRevealed) this.icon = Images.HIDDEN;
        else setCellByType();
    }

    /**
     * This method simply sets icon and sound value based on cell type.
     */
    private void setCellByType() {
        icon = type.getIcon();
        sound = type.getSoundPath();
    }

    /**
     * This method is used for painting 1 cell in panel.
     * @param g paint instrument
     * @param x X-axis of position where cell must be painted
     * @param y Y-axis of position where cell must be painted
     */
    public void paint(Graphics g, int x, int y) {
        Graphics2D g2d = (Graphics2D) g;

        antialias(g2d);

        if (icon == null) paintDefaultTexture(g2d, x, y);
        else g2d.drawImage(icon, x, y, null);

        paintText(g2d, x, y);
    }

    /**
     * This method is used for painting 1 cell in panel in case there is troubles with icon paths.
     * @param g2d paint instrument
     * @param x X-axis of position where cell must be painted
     * @param y Y-axis of position where cell must be painted
     */
    private void paintDefaultTexture(Graphics2D g2d, int x, int y) {
        if (!isRevealed) {
            g2d.setColor(new Color(130, 130, 130));
            g2d.fillRect(x, y, CELL_SIZE, CELL_SIZE);
            return;
        }

        switch (type) {
            case MINE:
                g2d.setColor(new Color(0,0,0));
                g2d.fillRect(x, y, CELL_SIZE, CELL_SIZE);
                break;
            case FLAG:
                g2d.setColor(new Color(255,0,0));
                g2d.fillRect(x, y, CELL_SIZE, CELL_SIZE);
                break;
            case NUMBER:
                g2d.drawRect(x, y, CELL_SIZE, CELL_SIZE);
                g2d.setColor(new Color(255,255,255));
                g2d.fillRect(x, y, CELL_SIZE, CELL_SIZE);
                break;
            default:
                g2d.fillRect(x, y, CELL_SIZE, CELL_SIZE);
        }
    }

    /**
     * This method simply paints value of cell with its number and color.
     * @param g2d paint instrument
     * @param x X-axis of position where cell must be painted
     * @param y Y-axis of position where cell must be painted
     */
    private void paintText(Graphics2D g2d, int x, int y) {
        if (type.equals(CellTypes.NUMBER) && isRevealed && value.getNumber() != 0) {
            g2d.setColor(value.getColor());
            g2d.setFont(new Font("Arial", Font.BOLD, CELL_SIZE - 2));

            String s = String.valueOf(value.getNumber());
            FontMetrics fm = g2d.getFontMetrics();
            int strWidth = fm.stringWidth(s);
            int strHeight = -(int) fm.getLineMetrics(s, g2d).getBaselineOffsets()[2];
            g2d.drawString(s, x + (CELL_SIZE - strWidth) / 2, y + CELL_SIZE - (CELL_SIZE - strHeight) / 2 - 5);
        }
    }

    /**
     * This method antialiases text (makes it more vector-graphics looking)
     * @param g2d paint instrument
     */
    private void antialias(Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    }

    /**
     * This method reveals cell.
     */
    public void reveal() {
        setCellByType();
        isRevealed = true;
    }

    /**
     * This method hides cell.
     */
    public void hide() {
        icon = Images.HIDDEN;
        isRevealed = false;
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

    /**
     * This method sets type and also changes icon and sound.
     * @param type type that must be changed
     */
    public void setType(CellTypes type) {
        this.type = type;
        setCellByType();
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public String getSound() {
        return sound;
    }

    @Override
    public String toString() {
        return "Value: " + value + ", Type: " + type;
    }
}