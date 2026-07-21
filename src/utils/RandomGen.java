package utils;

import enums.CellTypes;
import gameObjects.Cell;
import gameObjects.Value;

import java.io.Serializable;
import java.util.Random;

public class RandomGen implements Serializable {

    private final Random rnd = new Random();

    public boolean generateProbability(float percent) {
        float randomChance = rnd.nextFloat(101);
        if (percent <= 0) return false;
        if (percent >= 100) return true;
        return randomChance <= percent;
    }

    public int randomNumber(int min, int max) {
        if (max == Integer.MAX_VALUE) return 0;
        return rnd.nextInt(min, max + 1);
    }

    public Cell randomCell() {
        int randomValue = randomNumber(0, 8);
        boolean isRevealed = rnd.nextBoolean();
        CellTypes type = CellTypes.values()[randomNumber(0, CellTypes.values().length - 1)];

        return new Cell(type, new Value(randomValue), isRevealed);
    }
}
