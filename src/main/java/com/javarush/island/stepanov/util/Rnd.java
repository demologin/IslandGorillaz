package com.javarush.island.stepanov.util;

import java.util.concurrent.ThreadLocalRandom;

import static com.javarush.island.stepanov.constants.Constants.MAX_PERCENT;
import static com.javarush.island.stepanov.constants.Constants.MIN_PERCENT;

public class Rnd {
    private Rnd() {
    }

    public static int random(int min, int max) {
        if (min >= max) {
            return max;
        }
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    public static double random(double min, double max) {
        if (min >= max) {
            return max;
        }
        return ThreadLocalRandom.current().nextDouble(min, max);
    }

    public static boolean getForPercent(int percentProbably) {
        return random(MIN_PERCENT, MAX_PERCENT) < percentProbably;
    }
}
