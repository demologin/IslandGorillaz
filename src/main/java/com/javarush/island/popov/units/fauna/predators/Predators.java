package com.javarush.island.popov.units.fauna.predators;

import com.javarush.island.popov.units.fauna.Fauna;

public abstract  class Predators extends Fauna {
    public Predators(String name, String icon, double maxUnitWeight, int maxUnitsInCell, int maxUnitSpeedPerStep, double maxFoodForSaturation, int percentProbably) {
        super(name, icon, maxUnitWeight, maxUnitsInCell, maxUnitSpeedPerStep, maxFoodForSaturation, percentProbably);
    }

    public Predators() {
        super();
    }

}
