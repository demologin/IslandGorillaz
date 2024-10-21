package shosafoev.organizm.predator;


import shosafoev.map.IslandMap;
import shosafoev.map.Location;
import shosafoev.organizm.Animal;

public class Eagle extends Predator {
    /**
     * Constructor of the Eagle class.
     * Sets the values of the characteristics for the eagle.
     */
    public Eagle() {
        super(6, 3, 1, 20, "Eagle");
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
            case "Fox" -> 0.1;
            case "Duck" -> 0.8;
            case "Rabbit", "Mouse" -> 0.9;
            default -> 0;
        };
    }

    /**
     * Reproduces with a partner.
     * If the partner is an eagle, a new eagle is created at the same location.
     *
     * @param partner Breeding Partner
     */
    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Eagle) {
            Location location = IslandMap.getInstance().getLocation(partner.getRow(), partner.getColumn());
            IslandMap.getInstance().addAnimal(new Eagle(), location.getRow(), location.getColumn());
        }
    }
}
