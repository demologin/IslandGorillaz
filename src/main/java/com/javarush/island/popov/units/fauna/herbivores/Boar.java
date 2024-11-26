package com.javarush.island.popov.units.fauna.herbivores;

import com.javarush.island.popov.map.Cell;

public class Boar extends Herbivores{

    public Boar(String name, String icon, double maxUnitWeight, int maxUnitsInCell, int maxUnitSpeedPerStep, double maxFoodForSaturation, int percentProbably) {
        super(name, icon, maxUnitWeight, maxUnitsInCell, maxUnitSpeedPerStep, maxFoodForSaturation, percentProbably);
    }

    public Boar() {
        super();
    }

}
