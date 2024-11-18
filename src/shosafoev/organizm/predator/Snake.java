package shosafoev.organizm.predator;

import shosafoev.map.IslandMap;
import shosafoev.map.Location;
import shosafoev.organizm.Animal;

public class Snake extends Predator {
    public Snake() {
        super(15, 1, 3, 30, "Snake");
    }

    /**
     * Returns the chance to eat the specified food for the snake.
     *
     * @param foodName The name of the food
     * @return Chance to eat food
     */
    @Override
    public double getChanceToEat(String foodName) {
        return switch (foodName) {
            case "Duck" -> 0.1;
            case "Fox" -> 0.15;
            case "Rabbit" -> 0.2;
            case "Mouse" -> 0.4;
            default -> 0;
        };
    }

    /**
     * A method for breeding snakes.
     *
     * @param partner Breeding Partner
     */
    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Snake) {
            Location location = IslandMap.getInstance().getLocation(partner.getRow(), partner.getColumn());
            IslandMap.getInstance().addAnimal(new Snake(), location.getRow(), location.getColumn());
        }
    }
}
