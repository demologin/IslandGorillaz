package com.javarush.island.zubakha.entity.map;

import com.javarush.island.zubakha.entity.Herbivore;
import com.javarush.island.zubakha.entity.Plant;
import com.javarush.island.zubakha.entity.Predator;
import com.javarush.island.zubakha.entity.herbivores.*;
import com.javarush.island.zubakha.entity.predators.*;
import com.javarush.island.zubakha.services.AnimalLifecycleTask;
import com.javarush.island.zubakha.services.PlantGrowthTask;
import com.javarush.island.zubakha.services.StatisticsTask;
import lombok.Getter;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class IslandSimulation {
    private final long startTime;
    @Getter
    private final int countHerbivores;
    @Getter
    private final int countPlants;
    @Getter
    private final int countPredators;
    private static volatile IslandSimulation instance;
    @Getter
    private volatile ScheduledExecutorService executorService;

    private IslandSimulation(int countHerbivores, int countPlants, int countPredators) {
        this.countHerbivores = countHerbivores;
        this.countPlants = countPlants;
        this.countPredators = countPredators;
        startTime = System.currentTimeMillis();
    }

    public static IslandSimulation getInstance() {
        Random random = new Random();
        if (instance == null) {
            synchronized (IslandSimulation.class) {
                if (instance == null) {
                    instance = new IslandSimulation(random.nextInt(20),
                            random.nextInt(50),
                            random.nextInt(20) );
                }
            }
        }
        return instance;
    }

    public void createIslandModel() {
        placeHerbivores(countHerbivores);
        placePredators(countPredators);
        placePlants(countPlants);

        runIslandModel();
    }

    private void runIslandModel() {
        executorService = Executors.newScheduledThreadPool(3);

        AnimalLifecycleTask animalLifecycleTask = new AnimalLifecycleTask();
        PlantGrowthTask plantGrowthTask = new PlantGrowthTask();
        StatisticsTask statisticsTask = new StatisticsTask(animalLifecycleTask.getAnimalEatTask(),
                animalLifecycleTask.getAnimalHpDecreaseTask(),
                animalLifecycleTask.getObjectMultiplyTask());

        executorService.scheduleAtFixedRate(animalLifecycleTask, 1, 1, TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(plantGrowthTask, 5, 1, TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(statisticsTask, 0, 1, TimeUnit.SECONDS);
    }

    private List<Herbivore> createHerbivores(int countHerbivores) {
        List<Herbivore> herbivores = new ArrayList<>();
        Random random = new Random();
        herbivores.add(new Buffalo());
        herbivores.add(new Caterpillar());
        herbivores.add(new Deer());
        herbivores.add(new Duck());
        herbivores.add(new Goat());
        herbivores.add(new Horse());
        herbivores.add(new Mouse());
        herbivores.add(new Rabbit());
        herbivores.add(new Sheep());
        herbivores.add(new WildBoar());
        int remainingCount = countHerbivores - herbivores.size();
        for (int i = 0; i < remainingCount; i++) {
            int randomIndex = random.nextInt(herbivores.size());
            Herbivore randomHerbivore = herbivores.get(randomIndex);
            try {
                Herbivore newHerbivore = randomHerbivore.getClass().getDeclaredConstructor().newInstance();
                herbivores.add(newHerbivore);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
               throw new RuntimeException(e);
            }
        }

        return herbivores;
    }

    private List<Predator> createPredators(int countPredators) {
        List<Predator> predators = new ArrayList<>();
        Random random = new Random();
        predators.add(new Bear());
        predators.add(new Eagle());
        predators.add(new Fox());
        predators.add(new Snake());
        predators.add(new Wolf());
        int remainingCount = countPredators - predators.size();
        for (int i = 0; i < remainingCount; i++) {
            int randomIndex = random.nextInt(predators.size());
            Predator randomPredator = predators.get(randomIndex);
            try {
                Predator newPredator = randomPredator.getClass().getDeclaredConstructor().newInstance();
                predators.add(newPredator);
            } catch (InstantiationException | IllegalAccessException |InvocationTargetException | NoSuchMethodException e) {
               throw new RuntimeException(e);
            }
        }

        return predators;
    }

    private List<Plant> createPlants(int countPlants) {
        List<Plant> plants = new ArrayList<>();
        for (int i = 0; i < countPlants; i++) {
            plants.add(new Plant());
        }
        return plants;
    }

    public void placeHerbivores(int countHerbivores) {
        List<Herbivore> herbivores = createHerbivores(countHerbivores);
        Random random = ThreadLocalRandom.current();
        for (Herbivore herbivore : herbivores) {
            boolean placed = false;
            while (!placed) {
                int row = random.nextInt(GameMap.getInstance().getRow());
                int column = random.nextInt(GameMap.getInstance().getCol());
                Cell cell = GameMap.
                        getInstance().
                        getLocation(row, column);
                if (cell.
                        getAnimals().
                        stream().
                        filter(c -> c.getName().equals(herbivore.getName())).toList().size() <= herbivore.getMaxPopulation()) {
                    GameMap.
                            getInstance().
                            addAnimal(herbivore, row, column);
                    placed = true;
                }
            }
        }
    }

    public void placePredators(int countPredators) {
        List<Predator> predators = createPredators(countPredators);

        Random random = ThreadLocalRandom.current();
        for (Predator predator : predators) {
            boolean placed = false;
            while (!placed) {
                int row = random.nextInt(GameMap.getInstance().getRow());
                int col = random.nextInt(GameMap.getInstance().getCol());
                Cell cell = GameMap.
                        getInstance().
                        getLocation(row, col);
                if (cell.
                        getAnimals().
                        stream().
                        filter(c -> c.getName().equals(predator.getName())).
                        toList().
                        size() <= predator.getMaxPopulation()) {
                    GameMap.
                            getInstance().
                            addAnimal(predator, row, col);
                    placed = true;
                }
            }
        }
    }

    public void placePlants(int countPlants) {
        List<Plant> plants = createPlants(countPlants);

        Random random = ThreadLocalRandom.current();
        for (Plant plant : plants) {
            boolean placed = false;
            while (!placed) {
                int row = random.nextInt(GameMap.getInstance().getRow());
                int col = random.nextInt(GameMap.getInstance().getCol());
                Cell cell = GameMap.getInstance().getLocation(row, col);
                if (cell.getPlants().size() <= plant.getMaxPopulation()) {
                    GameMap.getInstance().addPlant(plant, row, col);
                    placed = true;
                }
            }
        }
    }

    public long getTimeNow() {
        return (System.currentTimeMillis() - startTime) / 1000;
    }

}
