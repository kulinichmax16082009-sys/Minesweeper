package utils.saveUtils;

import gameObjects.Player;
import utils.Board;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * GameData class represents all data that was used in a game.
 *
 * @author Maksym Kulynych
 */
public class GameData extends Data implements Serializable {
    private ArrayList<String> titles;
    private ArrayList<Integer> flagsLeft;
    private ArrayList<Long> playerTimes;
    private ArrayList<LocalDate> dates;
    private ArrayList<LocalTime> times;
    private ArrayList<Board> boards;

    /**
     * Empty constructor that creates new ArrayLists by default.
     */
    public GameData() {
        flagsLeft = new ArrayList<>();
        playerTimes = new ArrayList<>();
        titles = new ArrayList<>();
        dates = new ArrayList<>();
        times = new ArrayList<>();
        boards = new ArrayList<>();
    }

    /**
     * This method adds all data to all ArrayLists in a class.
     * @param currentPlayer player that was playing a game
     * @param title text title that player named this save
     * @param board board that was in a game
     */
    public void addAll(Player currentPlayer, String title, Board board) {
        flagsLeft.add(currentPlayer.getFlagsLeft());
        playerTimes.add(currentPlayer.getTime());
        titles.add(title);
        dates.add(LocalDate.now());
        times.add(LocalTime.now().withNano(0));
        boards.add(board);
    }

    /**
     * This method simply deletes all data in ArrayLists by index.
     * @param index index in all ArrayLists
     */
    public void removeAllAtIndex(int index) {
        if (index < 0 || index >= times.size()) return;

        flagsLeft.remove(index);
        playerTimes.remove(index);
        titles.remove(index);
        dates.remove(index);
        times.remove(index);
        boards.remove(index);
    }

    public ArrayList<String> getTitles() {
        return titles;
    }

    public ArrayList<Integer> getFlagsLeft() {
        return flagsLeft;
    }

    public ArrayList<Long> getPlayerTimes() {
        return playerTimes;
    }

    public ArrayList<LocalDate> getDates() {
        return dates;
    }

    public ArrayList<LocalTime> getTimes() {
        return times;
    }

    public ArrayList<Board> getBoards() {
        return boards;
    }

    @Override
    protected String getFilePath() {
        File folder = new File(System.getenv("APPDATA") + "/.minesweeper");
        if (!folder.exists()) folder.mkdirs();
        return folder.getPath() + "/saves.dat";
    }

    @Override
    protected Data createEmpty() {
        return new GameData();
    }

    @Override
    public String toString() {
        return "GameData{" +
                "titles=" + titles +
                ", flagsLeft=" + flagsLeft +
                ", playerTimes=" + playerTimes +
                ", dates=" + dates +
                ", times=" + times +
                ", boards=" + boards +
                '}';
    }
}
