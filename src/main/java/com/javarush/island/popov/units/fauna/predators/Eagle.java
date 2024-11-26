package com.javarush.island.popov.units.fauna.predators;

public class Eagle extends Predators{
    public Eagle(String name, String icon, double maxUnitWeight, int maxUnitsInCell, int maxUnitSpeedPerStep, double maxFoodForSaturation, int percentProbably) {
        super(name, icon, maxUnitWeight, maxUnitsInCell, maxUnitSpeedPerStep, maxFoodForSaturation, percentProbably);
    }

    public Eagle() {
        super();
    }
}
