package com.javarush.island.nikitin.domain.entity.navigation;

import com.javarush.island.nikitin.domain.entity.map.Location;

public class MoveSouth implements MoveStrategy{
    @Override
    public Location findTargetLocation(Navigator navigator, Location current, int steps) {
        int newRow = Math.min(navigator.getCountRows() - 1, navigator.getCurrentRow(current) + steps);
        return navigator.getLocation(newRow, navigator.getCurrentCol(current));
    }
}
