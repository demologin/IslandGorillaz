package com.javarush.island.nikitin.domain.entity.map.navigation;

import com.javarush.island.nikitin.domain.entity.map.Location;

public class MoveEast implements MoveStrategy{
    @Override
    public Location findTargetLocation(Navigator navigator, Location current, int steps) {
        int newCol = Math.min(navigator.getCountCols() - 1, navigator.getCurrentCol(current) + steps);
        int currentRow = navigator.getCurrentRow(current);
        return navigator.getLocation(currentRow, newCol);
    }
}
