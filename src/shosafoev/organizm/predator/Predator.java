package shosafoev.organizm.predator;


import shosafoev.organizm.Animal;

public abstract class Predator extends Animal {
    /**
     * Constructor of the Predator class.
     *
     * @param weight Predator weight
     * @param step Move step
     * @param maxHp Maximum amount of health
     * @param maxPopulation Maximum number of individuals
     * @param name The name of the predator
     */
    public Predator(double weight, int step, double maxHp, int maxPopulation, String name) {
        super(weight, step, maxHp, maxPopulation, name);
    }
}
