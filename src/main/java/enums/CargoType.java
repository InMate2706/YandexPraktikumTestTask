package enums;

import java.util.Random;

/**
 * Тип груза
 */
public enum CargoType {

    /**
     * Большой
     */
    BIG,

    /**
     * Маленький
     */
    LITTLE;

    public static CargoType getRandomCargoType() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}