package shosafoev.organizm.herbivore;


import shosafoev.map.IslandMap;
import shosafoev.map.Location;
import shosafoev.organizm.Animal;

public class WildBoar extends Herbivore{
    /**
     * Constructor of the WildBoar class.
     * Sets the values of characteristics for a wild pig.
     */
    public WildBoar() {
        super(400, 2, 50, 50, "WildBoar");
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
            case "Mouse" -> 0.5;
            case "Caterpillar" -> 0.9;
            case "Plant" -> 1;
            default -> 0;
        };
    }

    /**
     * Reproduces with a partner.
     * If the partner is a wild pig, a new wild pig is created at the same location.
     *
     * @param partner Breeding Partner
     */
    @Override
    public void multiply(Animal partner) {
        if (partner instanceof WildBoar){
            Location location = IslandMap.getInstance().getLocation(partner.getRow(), partner.getColumn());
            IslandMap.getInstance().addAnimal(new WildBoar(), location.getRow(), location.getColumn());
        }
    }
}
