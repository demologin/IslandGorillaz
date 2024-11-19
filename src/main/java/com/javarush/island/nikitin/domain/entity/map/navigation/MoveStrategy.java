package com.javarush.island.nikitin.domain.entity.map.navigation;

import com.javarush.island.nikitin.domain.entity.map.Location;

public interface MoveStrategy {
    Location findTargetLocation(Navigator navigator, Location current, int steps);
}
