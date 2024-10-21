package shosafoev.map;


import shosafoev.organizm.Animal;
import shosafoev.organizm.plant.Plant;

import java.util.ArrayList;
import java.util.List;

public class IslandMap {

    private Location[][] locations; // A two-dimensional array consisting of locations (cells)
    private final int numRows = 10; //default
    private final int numColumns = 4; //default
    private static volatile IslandMap instance;

    /**
     * Private constructor of the IslandMap class.
     * Used to create a single instance of the class (singleton).
     */
    private IslandMap() {
    }

    /**
     * Private constructor of the IslandMap class.
     * Used to create a single instance of the class (singleton).
     */
    public static IslandMap getInstance() {
        if (instance == null) {
            synchronized (IslandMap.class) {
                if (instance == null) {
                    instance = new IslandMap();
                }
            }
        }
        return instance;
    }

    /**
     * Method for initializing locations (cells) on the island.
     * Sets the dimensions of a two-dimensional array and creates locations.
     *
     * @param numRows    Number of rows
     * @param numColumns Number of columns
     */
    public void initializeLocations(int numRows, int numColumns) {
        locations = new Location[numRows][numColumns];
        for (int i = 0; i < locations.length; i++) {
            for (int j = 0; j < locations[i].length; j++) {
                locations[i][j] = new Location(i, j);
            }
        }
    }

    /**
     * Overloaded method for initializing locations (cells) on the island.
     * Uses default values for dimensions.
     */
    public void initializeLocations() {
        locations = new Location[numRows][numColumns];
        for (int i = 0; i < locations.length; i++) {
            for (int j = 0; j < locations[i].length; j++) {
                locations[i][j] = new Location(i, j);
            }
        }
    }

    /**
     * A method for obtaining a location (cell) at specified coordinates.
     *
     * @param row    Line number
     * @param column Column number
     * @return Location with specified coordinates
     */
    public synchronized Location getLocation(int row, int column) {
        return locations[row][column];
    }

    /**
     * Method for adding an animal to the specified location.
     *
     * @param animal Animal to add
     * @param row    Location row number
     * @param column Location column number
     */
    public void addAnimal(Animal animal, int row, int column) {
        Location location = getLocation(row, column);
        location.addAnimal(animal);
    }

    /**
     * Method for removing an animal from a specified location.
     *
     * @param animal Animal to remove
     * @param row    Location row number
     * @param column Location column number
     */
    public void removeAnimal(Animal animal, int row, int column) {
        Location location = getLocation(row, column);
        location.removeAnimal(animal);
    }

    /**
     * Method for adding a plant to a specified location.
     *
     * @param plant  A plant to add
     * @param row    Location row number
     * @param column Location column number
     */
    public void addPlant(Plant plant, int row, int column) {
        Location location = getLocation(row, column);
        location.addPlant(plant);
    }

    /**
     * A method for removing a plant from a specified location.
     *
     * @param plant  Plant to remove
     * @param row    Location row number
     * @param column Location column number
     */
    public void removePlant(Plant plant, int row, int column) {
        Location location = getLocation(row, column);
        location.removePlant(plant);
    }

    /**
     * Method for getting a list of all animals on the island.
     *
     * @return List of all animals
     */
    public synchronized List<Animal> getAllAnimals() {
        List<Animal> allAnimals = new ArrayList<>();
        for (Location[] row : locations) {
            for (Location location : row) {
                allAnimals.addAll(location.getAnimals());
            }
        }
        return allAnimals;
    }

    /**
     * Method for getting a list of all plants on the island.
     *
     * @return List of all plants
     */
    public List<Plant> getAllPlants() {
        List<Plant> allPlants = new ArrayList<>();
        for (Location[] row : locations) {
            for (Location location : row) {
                allPlants.addAll(location.getPlants());
            }
        }
        return allPlants;
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumColumns() {
        return numColumns;
    }
}
