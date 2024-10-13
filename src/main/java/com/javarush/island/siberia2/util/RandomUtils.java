package com.javarush.island.siberia2.util;

import com.javarush.island.siberia2.config.Constants;
import java.util.concurrent.ThreadLocalRandom;

/**
 * The RandomUtils class provides utility methods for generating random values,
 * such as random numbers and probabilities, used throughout the simulation.
 * It uses multithreaded random number generation to ensure thread safety.
 */

public class RandomUtils {

    public static double randomDouble(double min, double max) {
        if (min >= max) {
            throw new IllegalArgumentException(Constants.RANDOM_UTIL_MIX_MAX);
        }
        if (min == max) {
            return min;
        }
        return ThreadLocalRandom.current().nextDouble(min, max);
    }

    public static int randomInt(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException(Constants.RANDOM_UTIL_MIX_MAX);
        }
        if (min == max) {
            return min;
        }
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static boolean chance(int percent) {
        if (percent < 0 || percent > 100) {
            throw new IllegalArgumentException(Constants.RANDOM_UTIL_PERCENT);
        }
        return ThreadLocalRandom.current().nextInt(100) < percent;
    }

}