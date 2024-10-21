package shosafoev.organizm.herbivore;

import shosafoev.map.IslandMap;
import shosafoev.map.Location;
import shosafoev.organizm.Animal;

public class Goat extends Herbivore{
    /**
     * Constructor of the Goat class.
     * Sets the values of the characteristics for the goat.
     */
    public Goat() {
        super(60, 3, 10, 140, "Goat");
    }

    /**
     * Goat breeding method.
     * If the partner is a goat, then a new goat is created and added to the field.
     *
     * @param partner Breeding Partner
     */
    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Goat){
            Location location = IslandMap.getInstance().getLocation(partner.getRow(), partner.getColumn());
            IslandMap.getInstance().addAnimal(new Goat(), location.getRow(), location.getColumn());
        }
    }
}
