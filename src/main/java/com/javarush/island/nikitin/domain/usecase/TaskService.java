package com.javarush.island.nikitin.domain.usecase;

import com.javarush.island.nikitin.domain.entity.map.Location;

import java.util.concurrent.Callable;

public class TaskService {
    public Callable<Long> composeTask(Location habitat, int calendarDay) {
        return new Task(habitat, calendarDay);
    }
}
