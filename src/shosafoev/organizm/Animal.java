package shosafoev.organizm;

import shosafoev.eror.LifeFormException;
import shosafoev.map.IslandMap;
import shosafoev.map.Location;
import shosafoev.organizm.plant.Plant;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal extends DataOrganisms {
    private final int step; // The speed of movement, no more than, cells per turn
    private final double maxHp; // The maximum number of kilograms of food an animal needs for full saturation
    private double hp; // Amount of animal health

    public Animal(double weight, int step, double maxHp, int maxPopulation, String name) {
        super(weight, maxPopulation, name);
        this.step = step;
        this.maxHp = maxHp;
        this.hp = maxHp; // At the start, the maximum amount of health
    }

    /**
     * Checks whether the animal can eat the specified food.
     *
     * @param food The food that the animal is trying to eat
     * @return true if the animal has successfully eaten the food, otherwise false
     */
    public boolean eat(Object food) {
        double chanceToEat;
        DataOrganisms dataOrganisms = null;
        boolean animalEatFood;

        if (food instanceof DataOrganisms) {
            dataOrganisms = (DataOrganisms) food;
        } else {
            try {
                throw new LifeFormException("The object is not an animal/plant.");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        String foodName = Objects.requireNonNull(dataOrganisms).getName();
        chanceToEat = getChanceToEat(foodName);

        animalEatFood = ThreadLocalRandom.current().nextDouble() < chanceToEat;
        if (animalEatFood) {
            setHp(Math.min((getHp() + dataOrganisms.getWeight()), getMaxHp()));
            Location location = IslandMap.getInstance().getLocation(dataOrganisms.getRow(),
                    dataOrganisms.getColumn());
            if (dataOrganisms instanceof Animal animal) {
                if (location.getAnimals().contains(animal)) {
                    IslandMap.getInstance().removeAnimal(animal,
                            location.getRow(), location.getColumn());
                } else {
                    return false;
                }
            } else {
                Plant plant = (Plant) dataOrganisms;
                if (location.getPlants().size() > 0) {
                    IslandMap.getInstance().removePlant(plant, location.getRow(), location.getColumn());
                } else {
                    return false;
                }
            }
        }
        return animalEatFood;
    }

    /**
     * An abstract method for getting a chance to eat the specified food.
     *
     * @param foodName The name of the food
     * @return Chance to eat food
     */
    public abstract double getChanceToEat(String foodName);

    /**
     * An abstract method for breeding an animal with a partner.
     *
     * @param partner Breeding Partner
     */
    public abstract void multiply(Animal partner);

    /**
     * Moves the animal by a random number of cells in a random direction.
     */
    public void move() {
        Random random = new Random();
        int randomCells = random.nextInt(getStep()) + 1;
        // Generating a random direction (up, down, left or right)
        int direction = random.nextInt(4);
// Calculating new coordinates depending on the direction
        int newRow = getRow();
        int newColumn = getColumn();
        switch (direction) {
            case 0 -> // Up
                    newRow -= randomCells;
            case 1 -> // Down
                    newRow += randomCells;
            case 2 -> // To the left
                    newColumn -= randomCells;
            case 3 -> // To the right
                    newColumn += randomCells;
        }
        // Check that the new coordinates do not go beyond the boundaries of the field
        while (newRow < 0 || newRow >= IslandMap.getInstance().getNumRows() || newColumn < 0 || newColumn
                >= IslandMap.getInstance().getNumColumns()) {
            randomCells = random.nextInt(getStep()) + 1;
            direction = random.nextInt(4);

            newRow = getRow();
            newColumn = getColumn();
            switch (direction) {
                case 0 -> // Up
                        newRow -= randomCells;
                case 1 -> // Down
                        newRow += randomCells;
                case 2 -> // To the left
                        newColumn -= randomCells;
                case 3 -> // To the right
                        newColumn += randomCells;
            }
        }
        IslandMap.getInstance().removeAnimal(this, getRow(), getColumn());
        // Updating new coordinates
        setRow(newRow);
        setColumn(newColumn);
        IslandMap.getInstance().addAnimal(this, newRow, newColumn);
    }

    public int getStep() {
        return step;
    }

    public double getHp() {
        return hp;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public double getMaxHp() {
        return maxHp;
    }
}

