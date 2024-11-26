package com.javarush.island.popov.units.fauna.predators;

public class Anaconda extends Predators {
    public Anaconda(String name, String icon, double maxUnitWeight, int maxUnitsInCell, int maxUnitSpeedPerStep, double maxFoodForSaturation, int percentProbably) {
        super(name, icon, maxUnitWeight, maxUnitsInCell, maxUnitSpeedPerStep, maxFoodForSaturation, percentProbably);
    }

    public Anaconda() {
        super();
    }
}
