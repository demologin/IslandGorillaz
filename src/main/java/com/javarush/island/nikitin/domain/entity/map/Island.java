package com.javarush.island.nikitin.domain.entity.map;

import lombok.Getter;

@Getter
public class Island {
    private final Location[][] location;
    private final int totalCountLocations;

    public Island(int ROWS, int COLUMNS) {
        this.location = new Location[ROWS][COLUMNS];
        this.totalCountLocations = ROWS * COLUMNS;
    }

}
