package utils.constants;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Images class represents all constant images loaded from paths.
 *
 * @author Maksym Kulynych
 */
public class Images {
    public static final Image MINE = load("/images/cellTypesIcons/mine.png");
    public static final Image HIDDEN = load("/images/cellTypesIcons/hidden.png");
    public static final Image FLAG = load("/images/cellTypesIcons/flag.png");
    public static final Image NUMBER = load("/images/cellTypesIcons/number.png");

    public static final Image PLAYER_ALIVE = load("/images/playerIcons/alive.png");
    public static final Image PLAYER_DEAD = load("/images/playerIcons/dead.png");
    public static final Image PLAYER_WON = load("/images/playerIcons/won.png");


    /**
     * This method creates an instance of image by loading it from path.
     * @param path path to an image
     * @return new image instance
     */
    private static Image load(String path) {
        URL url = Images.class.getResource(path);
        if (url == null) return null;
        return new ImageIcon(url).getImage();
    }
}