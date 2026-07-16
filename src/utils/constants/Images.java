package utils.constants;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Images {
    public static final Image MINE = load("resources/cellTypesIcons/mine.png");
    public static final Image HIDDEN = load("resources/cellTypesIcons/hidden.png");
    public static final Image FLAG = load("resources/cellTypesIcons/flag.png");
    public static final Image NUMBER = load("resources/cellTypesIcons/number.png");
    public static final Image PLAYER_ALIVE = load("resources/playerIcons/alive.png");
    public static final Image PLAYER_DEAD = load("resources/playerIcons/dead.png");
    public static final Image PLAYER_WON = load("resources/playerIcons/won.png");

    private static Image load(String path) {
        File file = new File(path);
        if (!file.exists()) return null;
        return new ImageIcon(path).getImage();
    }
}
