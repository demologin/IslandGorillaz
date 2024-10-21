package shosafoev.map;

import shosafoev.organizm.Animal;
import shosafoev.organizm.DataOrganisms;
import shosafoev.organizm.plant.Plant;

import java.util.ArrayList;
import java.util.List;


public class Location {
    private final int row;
    private final int column;
    private final List<Animal> animals;
    private final List<Plant> plants;

    /**
     * Constructor of the Location class.
     * Sets row and column values for a given location.
     *
     * @param row    Line number
     * @param column Column number
     */
    public Location(int row, int column) {
        this.row = row;
        this.column = column;

        animals = new ArrayList<>();
        plants = new ArrayList<>();
    }

    /**
     * Method for adding an animal to a given location.
     * Sets the coordinates of the animal and adds it to the list of animals.
     *
     * @param animal Animal to add
     */
    public void addAnimal(Animal animal) {
        animal.setRow(row);
        animal.setColumn(column);

        animals.add(animal);
    }

    /**
     * Method for removing an animal from a given location.
     *
     * @param animal Animal to remove
     */
    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    /**
     * Method for adding a plant to a given location.
     * Sets the coordinates of the plant and adds it to the list of plants.
     *
     * @param plant A plant to add
     */
    public void addPlant(Plant plant) {
        plant.setRow(row);
        plant.setColumn(column);
        plants.add(plant);
    }


    /**
     * A method for removing a plant from a given location.
     *
     * @param plant Plant to remove
     */
    public void removePlant(Plant plant) {
        plants.remove(plant);
    }

    /**
     * Method for getting a list of plants in a given location.
     *
     * @return List of plants
     */
    public List<Plant> getPlants() {
        return plants;
    }

    /**
     * Method for getting a list of animals in a given location.
     *
     * @return List of animals
     */
    public List<Animal> getAnimals() {
        return animals;
    }

    /**
     * Method for getting a list of all living forms in a given location.
     * Includes both animals and plants.
     *
     * @return List of living forms
     */
    public List<DataOrganisms> getDataOrganisms() {
        List<DataOrganisms> dataOrganisms = new ArrayList<>();
        dataOrganisms.addAll(animals);
        dataOrganisms.addAll(plants);
        return dataOrganisms;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

}
