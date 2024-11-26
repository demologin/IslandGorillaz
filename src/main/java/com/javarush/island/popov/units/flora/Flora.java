package com.javarush.island.popov.units.flora;

import com.javarush.island.popov.interfaces.units.Reproduceable;
import com.javarush.island.popov.map.Cell;
import com.javarush.island.popov.units.Unit;
import com.javarush.island.popov.utilits.Randomizer;

import java.util.concurrent.CopyOnWriteArrayList;

public abstract class Flora extends Unit implements Reproduceable {

    public Flora(String name, String icon, double maxUnitWeight, int maxUnitsInCell, int maxUnitSpeedPerStep, double maxFoodForSaturation, int percentProbably) {
        super(name, icon, maxUnitWeight, maxUnitsInCell, maxUnitSpeedPerStep, maxFoodForSaturation, percentProbably);
    }

    public Flora() {

    }

    @Override
    public boolean reproduce(Cell cell) {
        cell.getLock().lock();
        CopyOnWriteArrayList<Unit> unitsList = cell.getAllUnitsInCell().get(this.getType());
        if (cell.checkUnitInCell(this) && cell.haveEnoughtSpace(this)) {
            int countClones = Randomizer.rnd(0,getMaxUnitsInCell() - unitsList.size());
            for (int i = 0; i < countClones; i++){
                Unit child = Unit.clone(this);
                child.setWeight(this.getWeight());
                unitsList.add(child);
            }
            return true;
        }
        return false;
    }
}
