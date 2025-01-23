package com.javarush.island.nikitin.presentation.entity;

import java.util.Map;

public record DataStatistic(Map<String, Integer> map,
                            int countAllResidents,
                            int currentCalendarDay,
                            long deathTotalLocation,
                            int allRows,
                            int allColumns) {
}