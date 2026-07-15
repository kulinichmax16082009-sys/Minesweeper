package gameObjects;
import enums.CellTypes;
import utils.constants.CellTextures;
import utils.constants.Sounds;

import javax.swing.*;
import java.awt.*;

public class Cell {
    private Value value;
    private boolean isRevealed;
    private CellTypes type;
    private Image icon;
    private String sound;

    public static final int CELL_SIZE = 32;

    public Cell(CellTypes type, Value value, boolean isRevealed) {
        if (type == CellTypes.MINE) this.value = new Value(-1);
        else this.value = value;

        if (!isRevealed) this.icon = CellTextures.HIDDEN;
        else setCellByType(type);

        this.type = type;
        this.isRevealed = isRevealed;
    }

    public Cell() {
        type = CellTypes.NUMBER;
        isRevealed = false;
        value = new Value(-1);
        icon = new ImageIcon("").getImage();
    }

    private void setCellByType(CellTypes type) {
        switch (type) {
            case MINE:
                icon = CellTextures.MINE;
                sound = Sounds.MINE;
                break;
            case FLAGGED:
                icon = CellTextures.FLAGGED;
                sound = Sounds.FLAG;
                break;
            case NUMBER:
                icon = CellTextures.NUMBER;
                sound = Sounds.NUMBER;
                break;
            default:
                icon = null;
                sound = "";
        }
    }

    public void paint(Graphics g, int x, int y) {
        Graphics2D g2d = (Graphics2D) g;

        antialias(g2d);

        if (icon == null) paintDefaultTexture(g2d, x, y);
        else g2d.drawImage(icon, x, y, null);

        paintText(g2d, x, y);
    }

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
            case FLAGGED:
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

    private void paintText(Graphics2D g2d, int x, int y) {
        if (type.equals(CellTypes.NUMBER) && isRevealed && value.getNumber() != 0) {
            g2d.setColor(value.getColor());
            g2d.setFont(new Font("Arial", Font.BOLD, 30));

            String s = String.valueOf(value.getNumber());
            FontMetrics fm = g2d.getFontMetrics();
            int strWidth = fm.stringWidth(s);
            int strHeight = -(int) fm.getLineMetrics(s, g2d).getBaselineOffsets()[2];
            g2d.drawString(s, x + (CELL_SIZE - strWidth) / 2, y + CELL_SIZE - (CELL_SIZE - strHeight) / 2 - 5);
        }
    }

    private void antialias(Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    }

    public void reveal() {
        setCellByType(type);
        isRevealed = true;
    }

    public void hide() {
        icon = CellTextures.HIDDEN;
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

    public void setType(CellTypes type) {
        this.type = type;
        setCellByType(type);
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public void setRevealed(boolean revealed) {
        isRevealed = revealed;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "value=" + value +
                ", type=" + type +
                '}';
    }
}