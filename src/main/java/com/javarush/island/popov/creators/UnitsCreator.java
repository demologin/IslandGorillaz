package com.javarush.island.popov.creators;
import com.javarush.island.popov.map.Cell;
import com.javarush.island.popov.repo.Constants;
import com.javarush.island.popov.units.Unit;
import com.javarush.island.popov.utilits.Randomizer;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

public class UnitsCreator {

    public static void fill(Cell cell) {
        Map<Class<? extends Unit>, CopyOnWriteArrayList<Unit>> unitsMapInCell = cell.getAllUnitsInCell();
        Set<Unit> prototypes = Constants.PROTOTYPES;
        for (Unit unit : prototypes) {
            int percentProbably = unit.getPercentProbably();
            boolean bornShance = Randomizer.getRandom(percentProbably);
            if (bornShance) {
                CopyOnWriteArrayList<Unit> units = new CopyOnWriteArrayList<>();
                    int maxUnitsInCell = unit.getMaxUnitsInCell();
                    int countBornesUnit = Randomizer.rnd(0, maxUnitsInCell);
                    for (int i = 0; i < countBornesUnit; i++) {
                        units.add(unit);
                        unitsMapInCell.put(unit.getClass(), units);
                    }
//
            }
        }
    }
}
