package com.javarush.island.nikitin.domain.entity.map;


import lombok.Getter;


public class Island {
    @Getter
    private Location[][] location;

    public Island(int ROWS, int COLUMNS) {
        this.location = new Location[ROWS][COLUMNS];
    }
}
