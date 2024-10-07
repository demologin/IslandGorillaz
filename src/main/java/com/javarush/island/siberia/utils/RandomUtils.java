package com.javarush.island.siberia.utils;

import java.util.concurrent.ThreadLocalRandom;

public class RandomUtils {
    public static double randomDouble(double min, double max) {
        if (min >= max) {
            throw new IllegalArgumentException("min must be > max");
        }
        return ThreadLocalRandom.current().nextDouble(min, max);
    }

    public static int randomInt(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("min must be > max");
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