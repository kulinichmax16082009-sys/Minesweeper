package enums;

import utils.constants.Images;
import utils.constants.Sounds;

import java.awt.*;

public enum CellTypes {
    NUMBER(Sounds.NUMBER, Images.NUMBER),
    MINE(Sounds.MINE, Images.MINE),
    FLAGGED(Sounds.FLAG, Images.FLAGGED);

    private final String soundPath;
    private final Image icon;

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
