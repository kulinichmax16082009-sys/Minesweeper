package utils.constants;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Images class represents all constant images loaded from paths.
 *
 * @author Maksym Kulynych
 */
public class Images {
    public static final Image MINE = load("resources/images/cellTypesIcons/mine.png");
    public static final Image HIDDEN = load("resources/images/cellTypesIcons/hidden.png");
    public static final Image FLAG = load("resources/images/cellTypesIcons/flag.png");
    public static final Image NUMBER = load("resources/images/cellTypesIcons/number.png");

    public static final Image PLAYER_ALIVE = load("resources/images/playerIcons/alive.png");
    public static final Image PLAYER_DEAD = load("resources/images/playerIcons/dead.png");
    public static final Image PLAYER_WON = load("resources/images/playerIcons/won.png");

    /**
     * This method creates an instance of image by loading it from path.
     * @param path path to an image
     * @return new image instance
     */
    private static Image load(String path) {
        File file = new File(path);
        if (!file.exists()) return null;
        return new ImageIcon(path).getImage();
    }
}
