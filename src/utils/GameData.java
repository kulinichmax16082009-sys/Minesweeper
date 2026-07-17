package utils;

import gameObjects.Player;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class GameData implements Serializable {
    private static final String LOAD_SAVE_PATH = "resources/saves.dat";

    private ArrayList<String> titles;
    private ArrayList<Integer> flagsLeft;
    private ArrayList<Long> playerTimes;
    private ArrayList<LocalDate> dates;
    private ArrayList<LocalTime> times;

    public GameData() {
        this.flagsLeft = new ArrayList<>();
        this.playerTimes = new ArrayList<>();
        this.titles = new ArrayList<>();
        this.dates = new ArrayList<>();
        this.times = new ArrayList<>();
    }

    public void saveGame(Player currentPlayer, String title) {
        flagsLeft.add(currentPlayer.getFlagsLeft());
        playerTimes.add(currentPlayer.getTime());
        titles.add(title);
        dates.add(LocalDate.now());
        times.add(LocalTime.now());

        try {
            FileOutputStream fos = new FileOutputStream(LOAD_SAVE_PATH);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(this);

            oos.close();

            fos.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
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
}
