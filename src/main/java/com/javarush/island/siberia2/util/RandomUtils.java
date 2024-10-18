package com.javarush.island.siberia2.util;

import java.util.concurrent.ThreadLocalRandom;

/**
 * The RandomUtils class provides utility methods for generating random values,
 * such as random numbers and probabilities, used throughout the simulation.
 * It uses multithreaded random number generation to ensure thread safety.
 */

public class RandomUtils {

    public static int randomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static boolean chance(int percent) {
        return ThreadLocalRandom.current().nextInt(100) < percent;
    }

}