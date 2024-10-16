package com.javarush.island.kozlov.map;

import java.util.concurrent.ThreadLocalRandom;

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

    public Location getRandomNeighboringLocation(Location currentLocation) {
        int currentX = -1, currentY = -1;
        for (int i = 0; i < locations.length; i++) {
            for (int j = 0; j < locations[i].length; j++) {
                if (locations[i][j] == currentLocation) {
                    currentX = i;
                    currentY = j;
                    break;
                }
            }
        }

        if (currentX == -1 || currentY == -1) {
            return null;
        }

        int newX = currentX + ThreadLocalRandom.current().nextInt(-1, 2);
        int newY = currentY + ThreadLocalRandom.current().nextInt(-1, 2);

        if (newX >= 0 && newX < locations.length && newY >= 0 && newY < locations[0].length) {
            return locations[newX][newY];
        } else {
            return null;
        }
    }
}
