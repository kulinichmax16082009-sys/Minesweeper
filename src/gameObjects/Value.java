package gameObjects;

import com.fasterxml.jackson.databind.ObjectMapper;
import utils.MyColor;
import windows.BasicWindow;

import java.awt.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Value class represents value of cell with it number and color.
 *
 * @author Maksym Kulynych
 */
public class Value implements Serializable {
    private int number;
    private Color color;
    private ArrayList<MyColor> possibleColors;
    private static boolean errorShown = false;

    private final String JSON_FILE_PATH = "resources/json/colors.json";

    /**
     * Constructor that sets number and attaches color to them (-1 is reserved for mines)
     * @param number number that must be set
     */
    public Value(int number) {
        if (number < -1 || number > 8) number = 0;

        initPossibleColors();
        this.number = number;
        attachColorToValue();
    }

    /**
     * This method simply initializes all possible colors ArrayList from .json file.
     */
    private void initPossibleColors() {
        ObjectMapper mapper = new ObjectMapper();

        possibleColors = new ArrayList<>();

        try (InputStream input = new FileInputStream(JSON_FILE_PATH)) {
            mapper.readerForUpdating(this).readValue(input);
        } catch (Exception e) {
            if (!errorShown) {
                errorShown = true;
                BasicWindow.showErrorMessage("Error while reading " + JSON_FILE_PATH);
            }
        }

        for (MyColor possibleColor : possibleColors) {
            possibleColor.colorFilter();
        }
    }

    /**
     * This method creates new instance of Color class for graphics based on number of the value.
     */
    private void attachColorToValue() {
        for (int i = 0; i < possibleColors.size(); i++) {
            if (number == i + 1) {
                MyColor myColor = possibleColors.get(i);
                color = new Color(myColor.getR(), myColor.getG(), myColor.getB());
            }
        }
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public ArrayList<MyColor> getPossibleColors() {
        return possibleColors;
    }

    public void setPossibleColors(ArrayList<MyColor> possibleColors) {
        this.possibleColors = possibleColors;
    }

    @Override
    public String toString() {
        return "Value{" +
                "number=" + number +
                ", color=" + color +
                ", possibleColors=" + possibleColors +
                '}';
    }
}
