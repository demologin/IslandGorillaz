package shosafoev.organizm.herbivore;


import shosafoev.map.IslandMap;
import shosafoev.map.Location;
import shosafoev.organizm.Animal;

public class Sheep extends Herbivore{
    /**
     * Constructor of the Sheep class.
     * Sets the values of the characteristics for the sheep.
     */
    public Sheep() {
        super(70, 3, 15, 140, "Sheep");
    }

    /**
     * Reproduces with a partner.
     * If the partner is a sheep, a new sheep is created at the same location.
     *
     * @param partner Breeding Partner
     */
    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Sheep){
            Location location = IslandMap.getInstance().getLocation(partner.getRow(), partner.getColumn());
            IslandMap.getInstance().addAnimal(new Sheep(), location.getRow(), location.getColumn());
        }
    }
}
