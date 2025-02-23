package com.javarush.island.nikitin.application.entity;

import com.javarush.island.nikitin.domain.entity.map.Location;

/**
 * Represents a container for data related to the game state, including locations,
 * calendar day, death statistics, and dimensions for display.
 */

public record DataContainer(Location[][] locations,
                            int currentCalendarDay,
                            long deathTotalLocation,
                            int allRows,
                            int allColumns,
                            int rowsPrint,
                            int columnsPrint,
                            int countBiotaPrint) {
}
