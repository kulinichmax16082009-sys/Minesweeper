package utils.constants;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class CellTextures {
    public static final Image MINE = load("resources/cellTypesIcons/mine.png");
    public static final Image HIDDEN = load("resources/cellTypesIcons/hidden.png");
    public static final Image FLAGGED = load("resources/cellTypesIcons/flagged.png");
    public static final Image NUMBER = load("resources/cellTypesIcons/number.png");

    private static Image load(String path) {
        File file = new File(path);
        if (!file.exists()) return null;
        return new ImageIcon(path).getImage();
    }
}
