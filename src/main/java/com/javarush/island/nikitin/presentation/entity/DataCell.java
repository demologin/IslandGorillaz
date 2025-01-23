package com.javarush.island.nikitin.presentation.entity;

import com.javarush.island.nikitin.domain.entity.map.Location;

public record DataCell(Location[][] locations,
                       int rowsPrint,
                       int columnsPrint,
                       int countBiotaPrint) {
}
