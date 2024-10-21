package shosafoev.organizm.predator;


import shosafoev.map.IslandMap;
import shosafoev.map.Location;
import shosafoev.organizm.Animal;

public class Wolf extends Predator {
    public Wolf() {
        super(50, 3, 8, 30, "Wolf");
    }

    /**
     * Returns the chance to eat the specified food for the wolf.
     *
     * @param foodName The name of the food
     * @return Chance to eat food
     */
    @Override
    public double getChanceToEat(String foodName) {
        return switch (foodName) {
            case "Horse", "Buffalo" -> 0.1;
            case "Deer", "WildBoar" -> 0.15;
            case "Duck" -> 0.4;
            case "Goat", "Rabbit" -> 0.6;
            case "Sheep" -> 0.7;
            case "Mouse" -> 0.8;
            default -> 0;
        };
    }

    /**
     * A method for breeding wolves.
     *
     * @param partner Breeding Partner
     */
    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Wolf) {
            Location location = IslandMap.getInstance().getLocation(partner.getRow(), partner.getColumn());
            IslandMap.getInstance().addAnimal(new Wolf(), location.getRow(), location.getColumn());
        }
    }
}
