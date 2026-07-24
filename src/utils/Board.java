package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import enums.CellTypes;
import enums.Difficulty;
import gameObjects.Cell;
import gameObjects.Value;
import windows.BasicWindow;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;

/**
 * Board class is used to control all cells in game board based on game rules.
 *
 * @author Maksym Kulynych
 */
public class Board implements Serializable {
    private Cell[][] cells;
    private final RandomGen rnd;
    private int numberOfMines;
    private int width;
    private int height;
    private Difficulty difficulty;

    private final int[][] DIRECTIONS = { {-1, 0}, {0, -1}, {0, 1}, {1, 0}, {1, 1}, {-1, -1}, {-1, 1}, {1, -1}};

    public Board(Difficulty difficulty) {
        this.rnd = new RandomGen();
        this.cells = new Cell[1][1];
        this.numberOfMines = 40;
        this.width = 18;
        this.height = 14;

        this.difficulty = difficulty;

        normalizeBoard();
    }

    /**
     * This method generates new completely playable board based on rules (1st cell is empty, etc.)
     * @param firstX X-axis position of mouse first click
     * @param firstY Y-axis position of mouse first click
     */
    public void generateBoard(int firstX, int firstY) {
        initBoard(difficulty.getBoardPath());
        placeZerosNear(firstX, firstY);
        placeMines();
        calculateCellsValue();
    }

    /**
     * This method generates empty board. Used only on start.
     */
    public void generateEmptyBoard() {
        initBoard(difficulty.getBoardPath());
        normalizeBoard();
    }

    /**
     * This method initializes board from .json file.
     * @param jsonFilePath path to a .json file
     */
    private void initBoard(String jsonFilePath) {
        ObjectMapper mapper = new ObjectMapper();

        try (InputStream input = new FileInputStream(jsonFilePath)) {
            mapper.readerForUpdating(this).readValue(input);
        } catch (Exception e) {
            BasicWindow.showErrorMessage("Error while loading board from .json file");
        }

        normalizeBoard();
    }

    /**
     * This method randomly places mines in board.
     */
    private void placeMines() {
        while (numberOfMines > 0) {

            int x = rnd.randomNumber(0, width - 1);
            int y = rnd.randomNumber(0, height - 1);

            if (cells[y][x].getType() == CellTypes.MINE || cells[y][x].getValue().getNumber() == 0) continue;

            cells[y][x] = new Cell(CellTypes.MINE, new Value(0), false);

            numberOfMines--;
        }
    }

    /**
     * This method calculates all cells values after placing the mines.
     */
    private void calculateCellsValue() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {

                if (cells[i][j].getType() == CellTypes.MINE) continue;

                int value = 0;

                for (int[] d : DIRECTIONS) {
                    int x = d[0] + j;
                    int y = d[1] + i;

                    if (y < 0 || y >= cells.length || x < 0 || x >= cells[i].length) continue;
                    if (cells[y][x].getType() == CellTypes.MINE) value++;
                }

                cells[i][j] = new Cell(CellTypes.NUMBER, new Value(value), false);
            }
        }
    }

    /**
     * This method represents rule "1st click is empty" and places 0 valued cells.
     * @param x X-axis position of mouse first click
     * @param y Y-axis position of mouse first click
     */
    private void placeZerosNear(int x, int y) {
        int amount = rnd.randomNumber(2, 10);

        for (int i = 0; i < amount; i++) {
            for (int[] d : DIRECTIONS) {
                int newX = d[0] * i + x;
                int newY = d[1] * i + y;

                if (newY < 0 || newY >= cells.length || newX < 0 || newX >= cells[0].length) continue;

                cells[newY][newX].setValue(new Value(0));
            }
        }
    }

    /**
     * This method represents rule that opens all 0 valued cells near.
     * @param x X-axis position of 0 valued cell
     * @param y Y-axis position of 0 valued cell
     */
    public void openZerosNear(int x, int y) {
        if (cells[y][x].getValue().getNumber() != 0 || cells[y][x].getType() != CellTypes.NUMBER) return;

        for (int[] d : DIRECTIONS) {
            int newX = d[0] + x;
            int newY = d[1] + y;

            if (newY < 0 || newY >= cells.length || newX < 0 || newX >= cells[0].length) continue;

            Cell newCell = cells[newY][newX];

            if (newCell.isRevealed()) continue;

            newCell.reveal();

            if (newCell.getValue().getNumber() == 0) openZerosNear(newX, newY);
        }
    }

    /**
     * This method simply normalizes board to void illegal values in game.
     */
    private void normalizeBoard() {
        if (height <= 0 || width <= 0) {
            height = 1;
            width = 1;
        }

        if (numberOfMines < 0) numberOfMines = 0;
        else if (numberOfMines > height * width) numberOfMines = height * width;

        cells = new Cell[height][width];

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell(CellTypes.NUMBER, new Value(-1), false);
            }
        }
    }

    /**
     * This method simply reveals all mines in board.
     */
    public void revealAllMines() {
        for (Cell[] cell : cells) {
            for (Cell value : cell) {
                if (value.getValue().getNumber() == -1) {
                    value.setType(CellTypes.MINE);
                    value.reveal();
                }
            }
        }
    }

    /**
     * This method simply reveals all cells in board.
     */
    public void revealAll() {
        for (Cell[] cell : cells) {
            for (Cell value : cell) {
                value.reveal();
            }
        }
    }

    /**
     * This method checks if player is dead by checking mines that are revealed.
     * @return true - player is dead, false - otherwise
     */
    public boolean isDead() {
        for (Cell[] cell : cells) {
            for (Cell value : cell) {
                if (value.isRevealed() && value.getType() == CellTypes.MINE) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This method checks if player won by checking all cells that are revealed and none of them is mine.
     * @return true - player won, false - otherwise
     */
    public boolean isWin() {
        for (Cell[] cell : cells) {
            for (Cell value : cell) {
                if (!value.isRevealed() || isDead()) return false;
            }
        }
        return true;
    }

    public Cell[][] getCells() {
        return cells;
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

    public int getNumberOfMines() {
        return numberOfMines;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
}
