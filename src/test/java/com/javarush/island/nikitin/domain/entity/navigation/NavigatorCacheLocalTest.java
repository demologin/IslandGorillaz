package com.javarush.island.nikitin.domain.entity.navigation;

import com.javarush.island.nikitin.domain.entity.map.Location;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.function.Supplier;

class NavigatorCacheLocalTest {

    private NavigatorCacheLocation navigatorCacheLocation;

    @BeforeEach
    void setUp() {
        navigatorCacheLocation = new NavigatorCacheLocation();
    }

    @Test
    @DisplayName("When Cache Is Empty then Return New Location")
    void cacheContains_whenCacheIsEmpty_thenReturnNewLocation() {
        Direction west = Direction.WEST;
        Location mockCurrentLocation = Mockito.mock(Location.class);
        Location mockTargetLocation = Mockito.mock(Location.class);
        int stepRandom = 2;
        Supplier<Location> locationSupplier = () -> mockTargetLocation;

        Location locationActual = navigatorCacheLocation.cacheContains(west, mockCurrentLocation, stepRandom, locationSupplier);

        Assertions.assertNotNull(locationActual);
        Assertions.assertEquals(mockTargetLocation, locationActual);
    }

    @Test
    @DisplayName("When Cache Is Empty then Return Cache Location")
    void cacheContains_whenCacheIsEmpty_thenReturnCacheLocation() {
        Direction west = Direction.WEST;
        Location mockCurrentLocation = Mockito.mock(Location.class);
        Location mockTargetLocation = Mockito.mock(Location.class);
        int stepRandom = 5;
        Supplier<Location> locationSupplier = () -> mockTargetLocation;

        Location firstLocationActual = navigatorCacheLocation.cacheContains(west, mockCurrentLocation, stepRandom, locationSupplier);
        Location secondLocationActual = navigatorCacheLocation.cacheContains(west, mockCurrentLocation, stepRandom, locationSupplier);

        Assertions.assertEquals(firstLocationActual, secondLocationActual);
    }
}