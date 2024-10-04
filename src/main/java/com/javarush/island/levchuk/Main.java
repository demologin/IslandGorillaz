package com.javarush.island.levchuk;

import com.javarush.island.levchuk.constants.Constants;
import com.javarush.island.levchuk.map.IslandMap;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Main {



    public static void main(String[] args) {
        IslandMap islandMap = new IslandMap();
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(Constants.CORE_POOL_SIZE);
    }
}
