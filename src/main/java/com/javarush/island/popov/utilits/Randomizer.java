package com.javarush.island.popov.utilits;

import java.util.concurrent.ThreadLocalRandom;

public class Randomizer {

    public static boolean getRandom(int persentProbably){
        if (persentProbably >100){
            throw new IllegalArgumentException("процент больше 100");
        }
        int resultRnd = rnd(0,100);
        if(resultRnd < persentProbably){
            return true;
        }
        return false;
    }
    public static int rnd(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max);
    }
}
