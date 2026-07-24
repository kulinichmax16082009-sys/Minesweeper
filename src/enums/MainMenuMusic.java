package enums;

import utils.constants.Sounds;

/**
 * MainMenuMusic enum is used for selecting menu music by player.
 *
 * @author Maksym Kulynych
 */
public enum MainMenuMusic {
    CLASSIC_RETRO(Sounds.MAIN_MENU_CLASSIC_RETRO),
    EIGHT_BIT(Sounds.MAIN_MENU_8_BIT),
    BYTE_BLAST(Sounds.BYTE_BLAST),
    LET_IT_HAPPEN(Sounds.LET_IT_HAPPEN);

    private final String musicPath;

    /**
     * Constructor sets value of musicPath.
     * @param musicPath used for setting musicPath
     */
    MainMenuMusic(String musicPath) {
        this.musicPath = musicPath;
    }

    public String getMusicPath() {
        return musicPath;
    }
}
