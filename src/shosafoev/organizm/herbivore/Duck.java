package shosafoev.organizm.herbivore;


import shosafoev.map.IslandMap;
import shosafoev.map.Location;
import shosafoev.organizm.Animal;

public class Duck extends Herbivore {
    /**
     * Constructor of the Duck class.
     * Sets the values of the characteristics for the duck.
     */
    public Duck() {
        super(1, 4, 0.15, 200, "Duck");
    }

    /**
     * A method that returns the probability of eating a certain food.
     *
     * @param foodName Food Name
     * @return Probability of eating
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
     * Duck breeding method.
     * If the partner is a duck, then a new duck is created and added to the field.
     *
     * @param partner Breeding Partner
     */
    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Duck) {
            Location location = IslandMap.getInstance().getLocation(partner.getRow(), partner.getColumn());
            IslandMap.getInstance().addAnimal(new Duck(), location.getRow(), location.getColumn());
        }
    }
}
