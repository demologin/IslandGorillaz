package shosafoev.organizm.herbivore;


import shosafoev.organizm.Animal;

public abstract class Herbivore extends Animal {
    /**
     * Constructor of the Herbivore class.
     * Sets the values of characteristics for a herbivore.
     *
     * @param weight Animal weight
     * @param step is the step of the animal's movement
     * @param maxHp Maximum number of animal health points
     * @param maxPopulation The maximum number of animals of this species on the island
     * @param name The name of the animal species
     */
    public Herbivore(double weight, int step, double maxHp, int maxPopulation, String name) {
        super(weight, step, maxHp, maxPopulation, name);
    }

    /**
     * Gets the probability of eating a certain type of food.
     * For herbivorous animals, the probability of eating plants is 1,
     * for all other types of food, the probability is 0.
     * @param foodName Name of the type of food
     * @return Probability of eating food
     */
    @Override
    public double getChanceToEat(String foodName) {
        return switch (foodName) {
            case "Plant" -> 1;
            default -> 0;
        };
    }
}

