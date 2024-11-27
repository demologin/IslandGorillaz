package com.javarush.island.popov.processing;

import com.javarush.island.popov.creators.StatisticsCreator;
import com.javarush.island.popov.map.IslandMap;
import com.javarush.island.popov.repo.Constants;
import com.javarush.island.popov.services.EatingService;
import com.javarush.island.popov.view.ConsoleView;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class IslandLifeProcessor extends Thread{
   private final IslandMap map;
   private final List<Runnable> tasks;
    StatisticsCreator statistics = new StatisticsCreator();
    ConsoleView consoleView = new ConsoleView();

    public IslandLifeProcessor(IslandMap map, List<Runnable> tasks) {
        this.map = map;
        this.tasks = tasks;
    }

    @Override
    public void run() {
        ScheduledExecutorService servicePool = Executors.newScheduledThreadPool(Constants.CORE_POOL_SIZE);
            Runnable allTasks = ()->{
                for (Runnable task : tasks) {
                    task.run();
                }
                statistics.getStatisticByUnits(map);
                consoleView.showStaticticByUnits(statistics,map);
            };
            servicePool.scheduleWithFixedDelay(allTasks,0,1000,TimeUnit.MILLISECONDS);
            servicePool.schedule(servicePool::shutdown,Constants.DAYS_NUMBER,TimeUnit.SECONDS);

    }

}
