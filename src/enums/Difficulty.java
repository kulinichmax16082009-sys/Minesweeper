package enums;

import utils.constants.BoardDifficulties;

public enum Difficulty {
    EASY(BoardDifficulties.EASY),
    MEDIUM(BoardDifficulties.MEDIUM),
    HARD(BoardDifficulties.HARD);

    private final String boardPath;

    Difficulty(String boardPath) {
        this.boardPath = boardPath;
    }

    public String getBoardPath() {
        return boardPath;
    }
}
