package com.javarush.island.kozlov.actions;

import com.javarush.island.kozlov.exception.MoveException;
import com.javarush.island.kozlov.map.Island;
import com.javarush.island.kozlov.map.Location;

public interface Movable {
    void move(Location currentLocation, Island island) throws MoveException;
}
