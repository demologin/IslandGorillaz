package com.javarush.island.myasnikov.utility;

import java.util.Random;

public class RandomNumberGenerator {

    private static final Random random = new Random();

    public static int getRandomInt(int from , int to) {
        return random.nextInt(from, to);
    }

}
