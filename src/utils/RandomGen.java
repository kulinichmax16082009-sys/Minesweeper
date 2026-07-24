package utils;

import enums.CellTypes;
import gameObjects.Cell;
import gameObjects.Value;

import java.io.Serializable;
import java.util.Random;

/**
 * This class is used solving all problems with random generation.
 *
 * @author Maksym Kulynych
 */
public class RandomGen implements Serializable {
    private final Random rnd = new Random();

    /**
     * This method generates random number in range of min and max.
     *
     * @param min min number
     * @param max max number
     * @return random generated number
     */
    public int randomNumber(int min, int max) {
        if (max == Integer.MAX_VALUE) return 0;
        return rnd.nextInt(min, max + 1);
    }

    /**
     * This method generates completely random cell with random values, textures, etc.
     * @return new random instance of Cell class
     */
    public Cell randomCell() {
        int randomValue = randomNumber(0, 8);
        boolean isRevealed = rnd.nextBoolean();
        CellTypes type = CellTypes.values()[randomNumber(0, CellTypes.values().length - 1)];

        return new Cell(type, new Value(randomValue), isRevealed);
    }
}
