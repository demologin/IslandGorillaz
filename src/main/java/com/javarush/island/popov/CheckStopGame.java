package com.javarush.island.popov;

import com.javarush.island.popov.units.fauna.herbivores.Herbivores;

public class CheckStopGame {
    public static boolean notTheEnd(){
        if (Herbivores.countHerbivores!=0){
            return true;
        }
        return false;
    }
}
