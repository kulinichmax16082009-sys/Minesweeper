package utils;

import gameObjects.Player;
import windows.BasicWindow;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class GameData implements Serializable {
    private static final String LOAD_SAVE_PATH = "resources/saves.dat";

    private final ArrayList<String> titles;
    private final ArrayList<Integer> flagsLeft;
    private final ArrayList<Long> playerTimes;
    private final ArrayList<LocalDate> dates;
    private final ArrayList<LocalTime> times;
    private final ArrayList<Board> boards;

    public GameData() {
        flagsLeft = new ArrayList<>();
        playerTimes = new ArrayList<>();
        titles = new ArrayList<>();
        dates = new ArrayList<>();
        times = new ArrayList<>();
        boards = new ArrayList<>();
    }

    public void saveData() {
        try {
            FileOutputStream fos = new FileOutputStream(LOAD_SAVE_PATH);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(this);

            oos.close();

            fos.close();
        } catch (Exception e) {
            BasicWindow.showErrorMessage("Error while saving game");
        }
    }

    public static GameData loadData() {
        try {
            FileInputStream fis = new FileInputStream(LOAD_SAVE_PATH);
            ObjectInputStream ois = new ObjectInputStream(fis);

            GameData gameData = (GameData) ois.readObject();

            ois.close();
            fis.close();

            return gameData;
        } catch (Exception e) {
            return null;
        }
    }

    public void addAll(Player currentPlayer, String title, Board board) {
        flagsLeft.add(currentPlayer.getFlagsLeft());
        playerTimes.add(currentPlayer.getTime());
        titles.add(title);
        dates.add(LocalDate.now());
        times.add(LocalTime.now().withNano(0));
        boards.add(board);
    }

    public void removeAllAtIndex(int index) {
        if (index < 0 || index > times.size()) return;

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
    public String toString() {
        return "GameData{" +
                "titles=" + titles +
                ", flagsLeft=" + flagsLeft +
                ", playerTimes=" + playerTimes +
                ", dates=" + dates +
                ", times=" + times +
                '}';
    }
}
