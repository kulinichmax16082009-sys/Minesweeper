import java.util.HashMap;

public class BoardManager {
    private HashMap<Coordinates, Cell> board;
    private int rows;
    private int cols;

    public BoardManager(int cols, int rows) {
        board = new HashMap<>();

        if (cols <= 0 || rows <= 0) {
            cols = 0;
            rows = 0;
        }

        this.cols = cols;
        this.rows = rows;
    }

    public boolean isValidCoordinate(Coordinates coordinates) {
        return coordinates.getX() >= 0 && coordinates.getX() < cols && coordinates.getY() >= 0 && coordinates.getY() < rows;
    }

    public void placeCell(Coordinates coordinates, Cell cell) {
        if (isValidCoordinate(coordinates)) {
            board.put(coordinates, cell);
        } else {
            // Delete this
            System.out.println("Invalid coordinates: " + coordinates);
        }
    }

    public HashMap<Coordinates, Cell> getBoard() {
        return board;
    }

    public void setBoard(HashMap<Coordinates, Cell> board) {
        this.board = board;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    @Override
    public String toString() {
        return "BoardManager{" +
                "board=" + board +
                ", rows=" + rows +
                ", cols=" + cols +
                '}';
    }
}
