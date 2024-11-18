package shosafoev.organizm.herbivore;


import shosafoev.map.IslandMap;
import shosafoev.map.Location;
import shosafoev.organizm.Animal;

public class Deer extends Herbivore {
    /**
     * Constructor of the Deer class.
     * Sets the values of the characteristics for the deer.
     */
    public Deer() {
        super(300, 4, 50, 20, "Deer");
    }

    /**
     * Deer breeding method.
     * If the partner is a deer, then a new deer is created and added to the field.
     *
     * @param partner Breeding Partner
     */
    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Deer){
            Location location = IslandMap.getInstance().getLocation(partner.getRow(), partner.getColumn());
            IslandMap.getInstance().addAnimal(new Deer(), location.getRow(), location.getColumn());
        }
    }
}
