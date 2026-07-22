package enums;

import utils.constants.Sounds;

public enum MainMenuMusic {
    CLASSIC_RETRO(Sounds.MAIN_MENU_CLASSIC_RETRO),
    EIGHT_BIT(Sounds.MAIN_MENU_8_BIT),
    BYTE_BLAST(Sounds.BYTE_BLAST),
    LET_IT_HAPPEN(Sounds.LET_IT_HAPPEN);


    private final String path;

    MainMenuMusic(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
