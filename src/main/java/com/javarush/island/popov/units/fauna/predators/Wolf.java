package com.javarush.island.popov.units.fauna.predators;

public class Wolf extends Predators{
    public Wolf(String name, String icon, double maxUnitWeight, int maxUnitsInCell, int maxUnitSpeedPerStep, double maxFoodForSaturation, int percentProbably) {
        super(name, icon, maxUnitWeight, maxUnitsInCell, maxUnitSpeedPerStep, maxFoodForSaturation, percentProbably);
    }

    public Wolf() {
        super();
    }
}
