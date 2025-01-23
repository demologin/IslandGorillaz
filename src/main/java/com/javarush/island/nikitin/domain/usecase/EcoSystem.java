package com.javarush.island.nikitin.domain.usecase;

import com.javarush.island.nikitin.domain.entity.map.Island;
import com.javarush.island.nikitin.domain.entity.map.Location;
import com.javarush.island.nikitin.domain.exception.DomainException;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * A class representing the ecosystem that governs the activities on the island
 * within calendar days. This class uses the task service {@link TaskService} to
 * performing actions on locations on the island.
 */
public class EcoSystem {
    private final TaskService taskService;
    private final Island island;
    @Getter
    private final BlockingQueue<Callable<Long>> taskQueue;
    @Getter
    private int calendarDay;
    @Getter
    @Setter
    private long numberDeathsDay;
    private int row;
    private int column;
    @Getter
    private boolean isSimulateDayFinish;

    public EcoSystem(TaskService taskService, Island island, int calendarDay) {
        this.taskService = taskService;
        this.island = island;
        this.calendarDay = calendarDay;
        int defaultSizeQueue = 500;
        if (island.getTotalCountLocations() < defaultSizeQueue) {
            defaultSizeQueue = island.getTotalCountLocations();
        }
        this.taskQueue = new LinkedBlockingQueue<>(defaultSizeQueue);
    }

    /**
     * Fills the task queue by generating tasks from current locations.
     * If the queue is full, the remaining tasks will not be added.
     * The method must be called again after the queue is freed
     */
    public void fillingQueueTask(){
        simulateDay();
    }


    public Callable<Long> takeTaskFromQueue() {
        try {
            return taskQueue.take();
        } catch (InterruptedException e) {
            throw new DomainException(e);
        }
    }

    public boolean isEmptyQueue() {
        return taskQueue.isEmpty();
    }

    /**
     * Simulates a day in the ecosystem by filling the task queue with tasks.
     * Tasks are generated based on the locations on the island.
     * Remembers the current position in locations to fill the task queue and continue the simulation of the current day
     */
    private void simulateDay() {
        isSimulateDayFinish = false;
        Location[][] locations = island.getLocation();
        int i = row;
        int j = column;
        for (; i < locations.length; i++) {
            for (; j < locations[i].length; j++) {
                Callable<Long> task = taskService.composeTask(locations[i][j], calendarDay);
                boolean isAdded = taskQueue.offer(task);
                if (!isAdded) {
                    rememberPlace(i, j);
                    return;
                }
            }
            j = 0;
        }
        dayIsFinish();
    }

    private void rememberPlace(int i, int j) {
        row = i;
        column = j;
    }

    private void dayIsFinish() {
        calendarDay++;
        row = 0;
        column = 0;
        isSimulateDayFinish = true;
    }

    public Location[][] getLocationsForView() {
        return island.getLocation();
    }
}
