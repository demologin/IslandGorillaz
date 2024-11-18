package shosafoev.organizm.herbivore;


import shosafoev.map.IslandMap;
import shosafoev.map.Location;
import shosafoev.organizm.Animal;

public class Caterpillar extends Herbivore {
    /**
     * Constructor of the Caterpillar class.
     * Sets the values of the characteristics for the caterpillar.
     */
    public Caterpillar() {
        super(0.01, 0, 0, 1000, "aterpillar");
    }

    /**
     * The method of reproduction of the caterpillar.
     * If the partner is a caterpillar, then a new caterpillar is created and added to the field.
     *
     * @param partner Caterpillar Partner
     */
    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Caterpillar){
            Location location = IslandMap.getInstance().getLocation(partner.getRow(), partner.getColumn());
            IslandMap.getInstance().addAnimal(new Caterpillar(), location.getRow(), location.getColumn());
        }
    }
}

