package shosafoev.organizm.predator;


import shosafoev.map.IslandMap;
import shosafoev.map.Location;
import shosafoev.organizm.Animal;

public class Bear extends Predator {
    /**
     * Constructor of the Bear class.
     * Sets the values of the characteristics for the bear.
     */
    public Bear() {
        super(500, 2, 80, 5, "Bear");
    }

    /**
     * Gets a chance to eat a certain type of food.
     *
     * @param foodName Food Name
     * @return Chance to eat food
     */
    @Override
    public double getChanceToEat(String foodName) {
        return switch (foodName) {
            case "Duck" -> 0.1;
            case "Buffalo" -> 0.2;
            case "Horse" -> 0.4;
            case "WildBoar" -> 0.5;
            case "Goat", "Sheep" -> 0.7;
            case "Deer", "Rabbit", "Snake" -> 0.8;
            case "Mouse" -> 0.9;
            default -> 0;
        };
    }

    /**
     * Reproduces with a partner.
     * If the partner is a bear, a new bear is created at the same location.
     *
     * @param partner Breeding Partner
     */
    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Bear) {
            Location location = IslandMap.getInstance().getLocation(partner.getRow(), partner.getColumn());
            IslandMap.getInstance().addAnimal(new Bear(), location.getRow(), location.getColumn());
        }
    }
}
