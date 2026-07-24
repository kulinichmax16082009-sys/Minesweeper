package enums;

import utils.constants.BoardDifficulties;

/**
 * Difficulty enum is used to assign chosen difficulty to a board. Also giving it special path to a .json file.
 *
 * @author Maksym Kulynych
 */
public enum Difficulty {
    EASY(BoardDifficulties.EASY),
    MEDIUM(BoardDifficulties.MEDIUM),
    HARD(BoardDifficulties.HARD);

    private final String boardPath;

    /**
     * Constructor sets value of boardPath.
     * @param boardPath used for setting boardPath
     */
    Difficulty(String boardPath) {
        this.boardPath = boardPath;
    }

    public String getBoardPath() {
        return boardPath;
    }
}
