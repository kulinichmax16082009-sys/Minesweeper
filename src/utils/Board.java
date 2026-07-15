package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import enums.CellTypes;
import enums.Difficulty;
import gameObjects.Cell;
import gameObjects.Value;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;

public class Board {
    private Cell[][] cells;
    private final RandomGen rnd;
    private int numberOfMines;
    private int width;
    private int height;
    private Difficulty difficulty;

    private final String EASY_BOARD_PATH = "resources/boardDifficulties/easy.json";
    private final String MEDIUM_BOARD_PATH = "resources/boardDifficulties/medium.json";
    private final String HARD_BOARD_PATH = "resources/boardDifficulties/hard.json";

    private final int[][] DIRECTIONS = { {-1, 0}, {0, -1}, {0, 1}, {1, 0}, {1, 1}, {-1, -1}, {-1, 1}, {1, -1}};

    public Board(Difficulty difficulty) {
        //Handle
        this.rnd = new RandomGen();
        //
        this.cells = new Cell[1][1];
        this.numberOfMines = 0;
        this.width = 1;
        this.height = 1;

        this.difficulty = difficulty;

        normalizeBoard();
    }

    public void generateBoard(int firstX, int firstY) {
        switch (difficulty) {
            case EASY:
                initializeBoard(EASY_BOARD_PATH);
                break;
            case MEDIUM:
                initializeBoard(MEDIUM_BOARD_PATH);
                break;
            case HARD:
                initializeBoard(HARD_BOARD_PATH);
                break;
            default:
                height = 1;
                width = 1;
                numberOfMines = 0;
                break;
        }
        placeZerosNear(firstX, firstY);
        placeMines();
        calculateCellsValue();
    }

    private void initializeBoard(String jsonFilePath) {
        ObjectMapper mapper = new ObjectMapper();

        try (InputStream input = new FileInputStream(jsonFilePath)) {
            mapper.readerForUpdating(this).readValue(input);
        } catch (Exception e) {
            this.cells = new Cell[1][1];
            this.numberOfMines = 0;
            this.width = 1;
            this.height = 1;
        }

        normalizeBoard();
    }

    private void placeMines() {
        while (numberOfMines > 0) {

            int x = rnd.randomNumber(0, width - 1);
            int y = rnd.randomNumber(0, height - 1);

            if (cells[y][x].getType() == CellTypes.MINE || cells[y][x].getValue().getNumber() == 0) continue;

            cells[y][x] = new Cell(CellTypes.MINE, new Value(0), false);

            numberOfMines--;
        }
    }

    private void calculateCellsValue() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {

                if (cells[i][j].getType() == CellTypes.MINE) continue;

                int value = 0;

                for (int[] d : DIRECTIONS) {
                    int x = d[0] + j;
                    int y = d[1] + i;

                    if  (y < 0 || y >= cells.length || x < 0 || x >= cells[i].length) continue;
                    if (cells[y][x].getType() == CellTypes.MINE) value++;
                }

                cells[i][j] = new Cell(CellTypes.NUMBER, new Value(value), false);
            }
        }
    }

    public void placeZerosNear(int x, int y) {
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
                cells[i][j] = new Cell();
            }
        }
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] cells) {
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

    public int getNumberOfMines() {
        return numberOfMines;
    }

    public void setNumberOfMines(int numberOfMines) {
        this.numberOfMines = numberOfMines;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public String toString() {
        return "Board{" +
                "cells=" + Arrays.deepToString(cells) +
                ", numberOfMines=" + numberOfMines +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
