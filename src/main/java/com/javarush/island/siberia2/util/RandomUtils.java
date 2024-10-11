package com.javarush.island.siberia2.util;

import java.util.concurrent.ThreadLocalRandom;

/**
 * The RandomUtils class provides utility methods for generating random values,
 * such as random numbers and probabilities, used throughout the simulation.
 * It uses multithreaded random number generation to ensure thread safety.
 */

public class RandomUtils {

    public static double randomDouble(double min, double max) {
        if (min >= max) {
            throw new IllegalArgumentException("min must be < max");
        }
        if (min == max) {
            return min;
        }
        return ThreadLocalRandom.current().nextDouble(min, max);
    }

    public static int randomInt(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("min must be < max");
        }
        if (min == max) {
            return min;
        }
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static boolean chance(int percent) {
        if (percent < 0 || percent > 100) {
            throw new IllegalArgumentException("percent must be from 0 to 100");
        }
        return ThreadLocalRandom.current().nextInt(100) < percent;
    }

}