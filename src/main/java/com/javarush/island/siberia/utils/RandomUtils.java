package com.javarush.island.siberia.utils;

import java.util.concurrent.ThreadLocalRandom;

public class RandomUtils {
    public static double randomDouble(double min, double max) {
        return ThreadLocalRandom.current().nextDouble(min, max);
    }

    public static int randomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static boolean chance(int percent) {
        return ThreadLocalRandom.current().nextInt(100) < percent;
    }

}