package shosafoev.organizm.herbivore;


import shosafoev.map.IslandMap;
import shosafoev.map.Location;
import shosafoev.organizm.Animal;

public class Rabbit extends Herbivore{
    /**
     * Constructor of the Rabbit class.
     * Sets the values of the characteristics for the rabbit.
     */
    public Rabbit() {
        super(2, 2, 0.45, 150, "Rabbit");
    }

    /**
     * Reproduces with a partner.
     * If the partner is a rabbit, a new rabbit is created at the same location.
     *
     * @param partner Breeding Partner
     */
    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Rabbit){
            Location location = IslandMap.getInstance().getLocation(partner.getRow(), partner.getColumn());
            IslandMap.getInstance().addAnimal(new Rabbit(), location.getRow(), location.getColumn());
        }
    }
}

