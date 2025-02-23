package com.javarush.island.nikitin.domain.entity.navigation;

import com.javarush.island.nikitin.domain.entity.map.Location;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

public final class NavigatorCacheLocation {
    private final ConcurrentHashMap<Integer, Location> locationCache;

    public NavigatorCacheLocation() {
        this.locationCache = new ConcurrentHashMap<>();
    }

    public Location cacheContains(Direction direction, Location habitat, int stepRandom, Supplier<Location> navigatorSupplier) {
        Integer cacheKey = cacheKey(direction, habitat, stepRandom);
        if (locationCache.containsKey(cacheKey)) {
            return locationCache.get(cacheKey);
        }
        Location newLocation = navigatorSupplier.get();
        locationCache.putIfAbsent(cacheKey, newLocation);
        return newLocation;
    }

    private int cacheKey(Direction direction, Location habitat, int stepRandom) {
        return (direction.ordinal() + habitat.hashCode() + stepRandom);
    }
}
