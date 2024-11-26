package com.javarush.island.popov.units.flora;

import com.javarush.island.popov.interfaces.units.Reproduceable;
import com.javarush.island.popov.map.Cell;

public class Grass extends Flora implements Reproduceable {
    public Grass(String name, String icon, double maxUnitWeight, int maxUnitsInCell, int maxUnitSpeedPerStep, double maxFoodForSaturation, int percentProbably) {
        super(name, icon, maxUnitWeight, maxUnitsInCell, maxUnitSpeedPerStep, maxFoodForSaturation, percentProbably);
    }

    public Grass() {
        super();
    }

    @Override
    public boolean eat(Cell cell) {
        return false;
    }

    @Override
    public void move(Cell cell) {

    }
}
