import java.util.HashMap;

public class Board {
    private HashMap<Coordinates, Cell> cells;
    private int width;
    private int height;

    public Board(int height, int width) {
        cells = new HashMap<>();

        if (height <= 0 || width <= 0) {
            height = 0;
            width = 0;
        }

        this.height = height;
        this.width = width;
    }

    public boolean isValidCoordinate(Coordinates coordinates) {
        return coordinates.getX() >= 0 && coordinates.getX() < width && coordinates.getY() >= 0 && coordinates.getY() < height;
    }

    public void placeCell(Coordinates coordinates, Cell cell) {
        if (isValidCoordinate(coordinates)) {
            cells.put(coordinates, cell);
        } else {
            // Delete this
            System.out.println("Invalid coordinates: " + coordinates);
        }
    }

    public HashMap<Coordinates, Cell> getCells() {
        return cells;
    }

    public void setCells(HashMap<Coordinates, Cell> cells) {
        this.cells = cells;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "BoardManager{" +
                "board=" + cells +
                ", rows=" + width +
                ", cols=" + height +
                '}';
    }
}
