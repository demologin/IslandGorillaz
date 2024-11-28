package com.javarush.island.popov.units.fauna.herbivores;

import com.javarush.island.popov.units.fauna.Fauna;

public abstract class Herbivores extends Fauna {
    public Herbivores(String name, String icon, double maxUnitWeight, int maxUnitsInCell, int maxUnitSpeedPerStep, double maxFoodForSaturation, int percentProbably) {
        super(name, icon, maxUnitWeight, maxUnitsInCell, maxUnitSpeedPerStep, maxFoodForSaturation, percentProbably);
    }

    protected Herbivores() {
        super();
    }

}
