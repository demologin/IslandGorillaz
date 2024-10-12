package com.javarush.island.kozlov.map;

public class Island {

    private Location[][] locations;

    public Island(int width, int height) {
        locations = new Location[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                locations[i][j] = new Location();
            }
        }
    }

    public Location[][] getLocations() {
        return locations;
    }
}
