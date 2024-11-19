package com.javarush.island.nikitin.domain.entity.map.navigation;

import com.javarush.island.nikitin.domain.entity.map.Location;

public class MoveWest implements MoveStrategy{
    @Override
    public Location findTargetLocation(Navigator navigator, Location current, int steps) {
        int newCol = Math.max(0, navigator.getCurrentCol(current) - steps);
        return navigator.getLocation(navigator.getCurrentRow(current), newCol);
    }
}
