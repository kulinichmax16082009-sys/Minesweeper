package enums;

public enum Difficulty {
    EASY("resources/boardDifficulties/easy.json"),
    MEDIUM("resources/boardDifficulties/medium.json"),
    HARD("resources/boardDifficulties/hard.json");

    private final String boardPath;

    Difficulty(String boardPath) {
        this.boardPath = boardPath;
    }

    public String getBoardPath() {
        return boardPath;
    }
}
