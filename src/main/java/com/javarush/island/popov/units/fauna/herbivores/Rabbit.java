package com.javarush.island.popov.units.fauna.herbivores;

public class Rabbit extends Herbivores{
    public Rabbit(String name, String icon, double maxUnitWeight, int maxUnitsInCell, int maxUnitSpeedPerStep, double maxFoodForSaturation, int percentProbably) {
        super(name, icon, maxUnitWeight, maxUnitsInCell, maxUnitSpeedPerStep, maxFoodForSaturation, percentProbably);
    }

    public Rabbit() {
        super();
    }
}
