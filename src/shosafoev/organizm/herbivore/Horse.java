package shosafoev.organizm.herbivore;

import shosafoev.map.IslandMap;
import shosafoev.map.Location;
import shosafoev.organizm.Animal;

public class Horse extends Herbivore{
    /**
     * Constructor of the Horse class.
     * Sets the values of the characteristics for the horse.
     */
    public Horse() {
        super(400, 4, 60, 20, "Horse");
    }

    /**
     * Reproduces with a partner.
     * If the partner is a horse, a new horse is created at the same location.
     *
     * @param partner Breeding Partner
     */
    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Horse){
            Location location = IslandMap.getInstance().getLocation(partner.getRow(), partner.getColumn());
            IslandMap.getInstance().addAnimal(new Horse(), location.getRow(), location.getColumn());
        }
    }
}
