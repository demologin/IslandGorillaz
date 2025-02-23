package com.javarush.island.nikitin.domain.entity.navigation;

import com.javarush.island.nikitin.domain.entity.map.Location;

public class MoveNorth implements MoveStrategy{
    @Override
    public Location findTargetLocation(Navigator navigator, Location current, int steps) {

        int newRow = Math.max(0, navigator.getCurrentRow(current) - steps);
        int newCol = navigator.getCurrentCol(current);
        return navigator.getLocation(newRow, newCol);
    }
}
