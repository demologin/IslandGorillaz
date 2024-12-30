package com.javarush.island.nikitin.domain.entity.map.navigation;

import com.javarush.island.nikitin.domain.entity.map.Location;

public interface MoveStrategy {
    /**
     * Finds target location based on current position and number of steps.
     *
     * @param navigator navigator object that controls navigation
     * @param current   the current location where the movement starts
     * @param steps     number of steps to take
     * @return the target location after performing the move
     */
    Location findTargetLocation(Navigator navigator, Location current, int steps);
}
