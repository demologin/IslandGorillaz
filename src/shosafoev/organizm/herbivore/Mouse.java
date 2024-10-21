package shosafoev.organizm.herbivore;

import shosafoev.map.IslandMap;
import shosafoev.map.Location;
import shosafoev.organizm.Animal;

public class Mouse extends Herbivore{
    /**
     * Constructor of the Mouse class.
     * Sets the values of the characteristics for the mouse.
     */
    public Mouse() {
        super(0.05, 1, 0.01, 500, "Mouse");
    }

    /**
     * Returns the chance to eat a certain food.
     *
     * @param foodName Food Name
     * @return Chance to eat food
     */
    @Override
    public double getChanceToEat(String foodName) {
        return switch (foodName) {
            case "Caterpillar" -> 0.9;
            case "Plant" -> 1;
            default -> 0;
        };
    }

    /**
     * Reproduces with a partner.
     * If the partner is a mouse, a new mouse is created at the same location.
     *
     * @param partner Breeding Partner
     */
    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Mouse){
            Location location = IslandMap.getInstance().getLocation(partner.getRow(), partner.getColumn());
            IslandMap.getInstance().addAnimal(new Mouse(), location.getRow(), location.getColumn());
        }
    }
}
