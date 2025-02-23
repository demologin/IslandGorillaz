package com.javarush.island.myasnikov.map;

import com.javarush.island.myasnikov.utility.OrganismTypes;

import static com.javarush.island.myasnikov.config.Params.PLAY_DAYS;
import static com.javarush.island.myasnikov.utility.RandomNumberGenerator.getRandomInt;

public class GameTask implements Runnable{

    private int playDays = PLAY_DAYS;

    private final String name;

    private final GameMap gameMap;

    private final OrganismTypes organismType;

    public GameTask (String name, GameMap gameMap, OrganismTypes organismType) {
        this.name = name;
        this.gameMap = gameMap;
        this.organismType = organismType;
    }

    public void runDayForAnimalType () {
        while (playDays > 0) {
            gameMap.getOrganismsOnMap().forEach(organism -> {
                if (organism.getType() == organismType) {
                    try {
                        Thread.sleep(getRandomInt(0, 1000));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    organism.action();
                }
            });
            playDays--;
        }
    }

    @Override
    public void run() {
        runDayForAnimalType();
        System.out.println(name + " task finished");
    }
}
