package com.javarush.island.ivanov.utils;

import java.util.concurrent.ThreadLocalRandom;

public class Randomizer {

    public static int getRandomInt(int bound){
        return ThreadLocalRandom.current().nextInt(bound);
    }
}
