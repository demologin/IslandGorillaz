package com.javarush.island.nikitin.domain.entity.biota;

public record LimitData(
        double maxWeight,
        int maxSpeed,
        double maxFoodFeed,
        int maxCountUnit,
        double dailyWeightLossPct) {
}
