package shosafoev.simulation;
import shosafoev.map.IslandMap;
import shosafoev.map.Location;
import shosafoev.organizm.herbivore.*;
import shosafoev.organizm.plant.Plant;
import shosafoev.organizm.predator.*;
import shosafoev.simulation.thread.PlantGrow;
import shosafoev.simulation.thread.StatisticsOfTheIsland;
import shosafoev.simulation.thread.animalLifecycleTask.AnimalLife;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class SettingUpTheMenu {
    private final long startTime;

    private final int countHerbivores = 35;
    private final int countPlants = 40;
    private final int countPredators = 20;
    private static volatile SettingUpTheMenu instance;
    private volatile ScheduledExecutorService executorService;

    private SettingUpTheMenu() {
        startTime = System.currentTimeMillis();
    }

    /**
     * Get an instance of the IslandSimulation (Singleton) class
     *
     * @return instance An instance of the IslandSimulation class
     */
    public static SettingUpTheMenu getInstance() {
        if (instance == null) {
            synchronized (SettingUpTheMenu.class) {
                if (instance == null) {
                    instance = new SettingUpTheMenu();
                }
            }
        }
        return instance;
    }

    /**
     * Create an island model with a given number of herbivores, predators and plants
     *
     * @param countHerbivores Number of herbivores
     * @param countPredators Number of predators
     * @param countPlants Number of plants
     */
    public void createIslandModel(int countHerbivores, int countPredators, int countPlants) {
        placeHerbivores(countHerbivores);
        placePredators(countPredators);
        placePlants(countPlants);

        runIslandModel();
    }

    /**
     * Create an island model with the number of herbivores, predators and plants set by default
     */
    public void createIslandModel() {
        placeHerbivores(countHerbivores);
        placePredators(countPredators);
        placePlants(countPlants);

        runIslandModel();
    }

    /**
     * Launch the island model
     */
    private void runIslandModel() {
        executorService = Executors.newScheduledThreadPool(3);

        AnimalLife animalLife = new AnimalLife();
        PlantGrow plantGrow = new PlantGrow();

        StatisticsOfTheIsland statisticsOfTheIsland = new StatisticsOfTheIsland(animalLife.getAnimalEatTask(),
                animalLife.getAnimalHpDecreaseTask(), animalLife.getObjectMultiplyTask());

        executorService.scheduleAtFixedRate(animalLife, 1, 8, TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(plantGrow, 40, 30, TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(statisticsOfTheIsland, 0, 8, TimeUnit.SECONDS);
    }

    /**
     * Create a list of herbivores with a given number
     *
     * @param countHerbivores Number of herbivores
     * @return herbivores List of herbivores
     */
    private List<Herbivore> createHerbivores(int countHerbivores) {
        List<Herbivore> herbivores = new ArrayList<>();
        Random random = new Random();

        // Creating one animal of each species
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

        // We generate a random number of animals of each species, at least 1
        int remainingCount = countHerbivores - herbivores.size();
        for (int i = 0; i < remainingCount; i++) {
            // Generating a random index to select the type of animal
            int randomIndex = random.nextInt(herbivores.size());
            Herbivore randomHerbivore = herbivores.get(randomIndex);
            try {
                // Creating an instance of an animal through reflection
                Herbivore newHerbivore = randomHerbivore.getClass().newInstance();
                herbivores.add(newHerbivore);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return herbivores;
    }

    /**
     * Create a list of predators with a given number
     *
     * @param countPredators Number of predators
     * @return predators List of predators
     */
    private List<Predator> createPredators(int countPredators) {
        List<Predator> predators = new ArrayList<>();
        Random random = new Random();

        // Creating one animal of each species
        predators.add(new Bear());
        predators.add(new Eagle());
        predators.add(new Fox());
        predators.add(new Snake());
        predators.add(new Wolf());

        // We generate a random number of animals of each species, at least 1
        int remainingCount = countPredators - predators.size();
        for (int i = 0; i < remainingCount; i++) {
            // Generating a random index to select the type of animal
            int randomIndex = random.nextInt(predators.size());
            Predator randomPredator = predators.get(randomIndex);
            try {
                // Creating an instance of an animal through reflection
                Predator newPredator = randomPredator.getClass().newInstance();
                predators.add(newPredator);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return predators;
    }

    /**
     * Create a list of plants with a given number
     *
     * @param countPlants Number of plants
     * @return plants List of plants
     */
    private List<Plant> createPlants(int countPlants) {
        List<Plant> plants = new ArrayList<>();
        for (int i = 0; i < countPlants; i++) {
            plants.add(new Plant());
        }
        return plants;
    }

    /**
     * Place herbivores on the island
     *
     * @param countHerbivores Number of herbivores
     */
    public void placeHerbivores(int countHerbivores) {
        List<Herbivore> herbivores = createHerbivores(countHerbivores);
        Random random = ThreadLocalRandom.current();
        for (Herbivore herbivore : herbivores) {
            boolean placed = false;
            while (!placed) {
                int row = random.nextInt(IslandMap.getInstance().getNumRows());
                int column = random.nextInt(IslandMap.getInstance().getNumColumns());
                Location location = IslandMap.getInstance().getLocation(row, column);
                if (location.getAnimals().stream().filter(c -> c.getName().equals(herbivore.getName()))
                        .toList().size() <= herbivore.getMaxPopulation()) {
                    IslandMap.getInstance().addAnimal(herbivore, row, column);
                    placed = true;
                }
            }
        }
    }

    /**
     * Place predators on the island
     *
     * @param countPredators Number of predators
     */
    public void placePredators(int countPredators) {
        List<Predator> predators = createPredators(countPredators);

        Random random = ThreadLocalRandom.current();
        for (Predator predator : predators) {
            boolean placed = false;
            while (!placed) {
                int row = random.nextInt(IslandMap.getInstance().getNumRows());
                int column = random.nextInt(IslandMap.getInstance().getNumColumns());
                Location location = IslandMap.getInstance().getLocation(row, column);
                if (location.getAnimals().stream().filter(c -> c.getName().equals(predator.getName())).toList().size()
                        <= predator.getMaxPopulation()) {
                    IslandMap.getInstance().addAnimal(predator, row, column);
                    placed = true;
                }
            }
        }
    }

    /**
     * Place plants on the island
     *
     * @param countPlants Number of plants
     */
    public void placePlants(int countPlants) {
        List<Plant> plants = createPlants(countPlants);

        Random random = ThreadLocalRandom.current();
        for (Plant plant : plants) {
            boolean placed = false;
            while (!placed) {
                int row = random.nextInt(IslandMap.getInstance().getNumRows());
                int column = random.nextInt(IslandMap.getInstance().getNumColumns());
                Location location = IslandMap.getInstance().getLocation(row, column);
                if (location.getPlants().size() <= plant.getMaxPopulation()) {
                    IslandMap.getInstance().addPlant(plant, row, column);
                    placed = true;
                }
            }
        }
    }

    /**
     * Get the current simulation time
     *
     * @return timeNow The current simulation time
     */
    public long getTimeNow() {
        return (System.currentTimeMillis() - startTime) / 1000;
    }

    public ScheduledExecutorService getExecutorService() {
        return executorService;
    }

    public int getCountHerbivores() {
        return countHerbivores;
    }

    public int getCountPlants() {
        return countPlants;
    }

    public int getCountPredators() {
        return countPredators;
    }
}