package enums;

import utils.constants.Images;
import utils.constants.Sounds;

import java.awt.*;

/**
 * CellTypes enum is used to assign type for special cell. Also giving it special sound and icon.
 *
 * @author Maksym Kulynych
 */
public enum CellTypes {
    NUMBER(Sounds.NUMBER, Images.NUMBER),
    MINE(Sounds.MINE, Images.MINE),
    FLAG(Sounds.FLAG, Images.FLAG);

    private final String soundPath;
    private final Image icon;

    /**
     * Constructor sets values of soundPath and icon.
     * @param soundPath used for setting soundPath
     * @param icon used for setting icon
     */
    CellTypes(String soundPath, Image icon) {
        this.icon = icon;
        this.soundPath = soundPath;
    }

    public String getSoundPath() {
        return soundPath;
    }

    public Image getIcon() {
        return icon;
    }
}
