package com.javarush.island.popov.creators;

import com.javarush.island.popov.map.IslandMap;
import com.javarush.island.popov.services.EatingService;
import com.javarush.island.popov.services.MovingService;
import com.javarush.island.popov.services.ReproducingService;

import java.util.List;

public class TasksCreator {
    public static List<Runnable> createTasks(IslandMap map){
        List<Runnable> tasks= List.of(
                new EatingService(map),
                new MovingService(map),
                new ReproducingService(map));
        return tasks;
    }
}
