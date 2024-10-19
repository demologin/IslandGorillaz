package com.javarush.island.kozlov.actions;

import com.javarush.island.kozlov.exception.ReproductionException;
import com.javarush.island.kozlov.map.Location;

public interface Reproduce {
    void reproduce(Location currentLocation) throws ReproductionException;
}
