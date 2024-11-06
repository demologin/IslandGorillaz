package com.javarush.island.arsinov.model.plants;

import java.util.Random;

public class Plant implements Cloneable {
    int x,y;
    boolean isMature;

    public Plant(int x, int y) {
        this.x = x;
        this.y = y;
        isMature = false;
    }

    public void grow(){
        if(!isMature){
            isMature = new Random().nextBoolean();
        }
    }

    public boolean isMature() {
        return isMature;
    }

    public double getMass(){
        return isMature ? 1.0 : 0.5;
    }

    @Override
    public Plant clone() {
        try {
            return (Plant) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
