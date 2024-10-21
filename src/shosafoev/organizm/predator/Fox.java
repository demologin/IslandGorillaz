package shosafoev.organizm.predator;


import shosafoev.map.IslandMap;
import shosafoev.map.Location;
import shosafoev.organizm.Animal;

public class Fox extends Predator{
    /**
     * Constructor of the Fox class.
     * Sets the values of the characteristics for the fox.
     */
    public Fox() {
        super(8, 2, 2, 30, "Fox");
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
            case "Caterpillar" -> 0.4;
            case "Duck" -> 0.6;
            case "Rabbit" -> 0.7;
            case "Mouse" -> 0.9;
            default -> 0;
        };
    }

    /**
     * Reproduces with a partner.
     * If the partner is a fox, a new fox is created at the same location.
     *
     * @param partner Breeding Partner
     */
    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Fox){
            Location location = IslandMap.getInstance().getLocation(partner.getRow(), partner.getColumn());
            IslandMap.getInstance().addAnimal(new Fox(), location.getRow(), location.getColumn());
        }
    }
}
