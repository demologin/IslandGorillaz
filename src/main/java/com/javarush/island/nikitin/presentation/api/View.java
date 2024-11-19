package com.javarush.island.nikitin.presentation.api;

import com.javarush.island.nikitin.domain.entity.map.Location;

public interface View{
    void displayData(Location[][] data);
}
