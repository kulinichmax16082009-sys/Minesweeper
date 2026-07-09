package gameObjects;

import com.fasterxml.jackson.databind.ObjectMapper;
import utils.MyColor;
import windows.BasicWindow;

import java.awt.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class Value {
    private int number;
    private Color color;
    private ArrayList<MyColor> possibleColors;

    private final String JSON_FILE_PATH = "resources/colors.json";

    public Value(int number) {
        if (number < 0 || number > 9) number = 0;

        initPossibleColors();
        this.number = number;
        attachColorToValue();
    }

    private void initPossibleColors() {
        ObjectMapper mapper = new ObjectMapper();

        possibleColors = new ArrayList<>();

        try (InputStream input = new FileInputStream(JSON_FILE_PATH)) {
            mapper.readerForUpdating(this).readValue(input);
        } catch (Exception e) {
            BasicWindow.showErrorMessage("Error while reading colors.json file");
        }

        for (MyColor possibleColor : possibleColors) {
            possibleColor.colorFilter();
        }
    }

    private void attachColorToValue() {
        for (int i = 0; i < possibleColors.size(); i++) {
            if (number == i) {
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
