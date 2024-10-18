package com.javarush.island.siberia.utils;

import java.util.concurrent.ThreadLocalRandom;

/**
 * The RandomUtils class provides utility methods for generating random values,
 * such as random numbers and probabilities, used throughout the simulation.
 * It uses multithreaded random number generation to ensure thread safety.
 */

public class RandomUtils {

    /**
     * Generates a random double value within the specified range.
     *
     * @param min The minimum value (inclusive).
     * @param max The maximum value (exclusive).
     * @return A random double between min (inclusive) and max (exclusive).
     * @throws IllegalArgumentException If min is not less than max.
     */

    public static double randomDouble(double min, double max) {
        if (min >= max) {
            throw new IllegalArgumentException("min must be < max");
        }
        if (min == max) {
            return min;
        }
        return ThreadLocalRandom.current().nextDouble(min, max);
    }

    /**
     * Generates a random integer value within the specified range.
     *
     * @param min The minimum value (inclusive).
     * @param max The maximum value (inclusive).
     * @return A random integer between min (inclusive) and max (inclusive).
     * @throws IllegalArgumentException If min is not less than max.
     */

    public static int randomInt(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("min must be < max");
        }
        if (min == max) {
            return min;
        }
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    /**
     * Generates a boolean value based on a percentage chance.
     *
     * @param percent The probability of returning true, expressed as a percentage (0-100).
     * @return True if a random value falls within the given percentage, otherwise false.
     * @throws IllegalArgumentException If percent is not between 0 and 100.
     */

    public static boolean chance(int percent) {
        if (percent < 0 || percent > 100) {
            throw new IllegalArgumentException("percent must be from 0 to 100");
        }
        return ThreadLocalRandom.current().nextInt(100) < percent;
    }

}