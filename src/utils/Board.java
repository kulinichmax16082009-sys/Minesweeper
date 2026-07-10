package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import enums.Difficulty;
import gameObjects.Cell;

import java.io.FileInputStream;
import java.io.InputStream;

public class Board {
    private Cell[][] cells;
    private int numberOfMines;
    private int width;
    private int height;

    private final String EASY_BOARD_PATH = "resources/boardDifficulties/easy.json";
    private final String MEDIUM_BOARD_PATH = "resources/boardDifficulties/medium.json";
    private final String HARD_BOARD_PATH = "resources/boardDifficulties/hard.json";

//    public Board(int height, int width, int numberOfMines) {
//        if (height <= 0 || width <= 0) {
//            height = 0;
//            width = 0;
//        }
//
//        if (numberOfMines < 0) numberOfMines = 0;
//        else if (numberOfMines > height * width) numberOfMines = height * width;
//
//        this.height = height;
//        this.width = width;
//        this.numberOfMines = numberOfMines;
//        cells = new Cell[height][width];
//    }

    public Board() {
        this.cells = new Cell[1][1];
        this.numberOfMines = 0;
        this.width = 1;
        this.height = 1;
    }

    public void generateBoard(Difficulty difficulty) {
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

    private void normalizeBoard() {
        if (height <= 0 || width <= 0) {
            height = 1;
            width = 1;
        }

        if (numberOfMines < 0) numberOfMines = 0;
        else if (numberOfMines > height * width) numberOfMines = height * width;

        cells = new Cell[height][width];
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

    @Override
    public String toString() {
        return "Board{" +
                "cells=" + cells +
                ", numberOfMines=" + numberOfMines +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
