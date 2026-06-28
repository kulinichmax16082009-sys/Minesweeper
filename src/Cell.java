public class Cell {
    private int value;
    private CellTypes type;
    private Coordinates coordinates;

    public Cell(CellTypes type) {
        if (type == CellTypes.BOMB) this.value = -1;
        else this.value = 0;

        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public CellTypes getType() {
        return type;
    }

    public void setType(CellTypes type) {
        this.type = type;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "value=" + value +
                ", type=" + type +
                ", coordinates=" + coordinates +
                '}';
    }
}
