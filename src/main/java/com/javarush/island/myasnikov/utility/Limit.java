package com.javarush.island.myasnikov.utility;

import lombok.Getter;

@Getter
public class Limit {

    private final double maxWeight;
    private final int maxCellAmount;
    private final int maxCellMovementPerRound;

    public Limit(double maxWeight, int maxCellAmount, int maxCellMovementPerRound) {
        this.maxWeight = maxWeight;
        this.maxCellAmount = maxCellAmount;
        this.maxCellMovementPerRound = maxCellMovementPerRound;
    }
}
