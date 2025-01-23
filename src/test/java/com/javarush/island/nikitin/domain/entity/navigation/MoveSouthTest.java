package com.javarush.island.nikitin.domain.entity.navigation;

import com.javarush.island.nikitin.domain.entity.map.Location;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class MoveSouthTest {
    private Navigator navigator;
    private Location[][] locationArray;
    MoveStrategy strategy;

    @BeforeEach
    void setUp() {
        strategy = Direction.SOUTH.getStrategy();
        navigator = new Navigator();
        locationArray = new Location[3][3];
        fillArrayLocation();
        navigator.initializeIslandMap(locationArray);
    }

    @Test
    void findTargetLocation_whenMovesSouth_thenNoSteps() {
        int step = 0;
        Location targetLocation = locationArray[1][1];

        Location actualLocation = strategy.findTargetLocation(navigator, targetLocation, step);
        Location expectedLocation = locationArray[1][1];

        Assertions.assertEquals(expectedLocation, actualLocation);
    }

    @Test
    void findTargetLocation_whenMovesSouth_thenMovesSouth() {
        int step = 1;
        Location targetLocation = locationArray[1][1];

        Location actualLocation = strategy.findTargetLocation(navigator, targetLocation, step);
        Location expectedLocation = locationArray[2][1];

        Assertions.assertEquals(expectedLocation, actualLocation);
    }

    @Test
    void findTargetLocation_whenMovesSouth_thenNoBeyondBounds() {
        int step = 5;
        Location targetLocation = locationArray[1][1];

        Location actualLocation = strategy.findTargetLocation(navigator, targetLocation, step);
        Location expectedLocation = locationArray[2][1];

        Assertions.assertEquals(expectedLocation, actualLocation);
    }

    private void fillArrayLocation() {
        Location mock = Mockito.mock(Location.class);
        for (Location[] locations : locationArray) {
            for (int i = 0; i < locations.length; i++) {
                locations[i] = mock;
                mock = Mockito.mock(Location.class);
            }
        }
    }
}