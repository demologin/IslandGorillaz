package com.javarush.island.khmelov.util;

import java.util.concurrent.ThreadLocalRandom;

public class Rnd {

    private Rnd() {
    }

    public static int random(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    public static double random(double min, double max) {
        return ThreadLocalRandom.current().nextDouble(min, max);
    }

    public static boolean get(int percentProbably) {
        return random(0, 100) < percentProbably;
    }
}
