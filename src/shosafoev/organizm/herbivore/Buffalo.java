package shosafoev.organizm.herbivore;


import shosafoev.map.IslandMap;
import shosafoev.map.Location;
import shosafoev.organizm.Animal;

public class Buffalo extends Herbivore{
    /**
     * Constructor of the Buffalo class.
     * Sets the values of the characteristics for the buffalo.
     */
    public Buffalo() {
        super(700, 3, 100, 10, "Buffalo");
    }

    /**
     * The method of reproduction of the buffalo.
     * If the partner is a buffalo, then a new buffalo is created and added to the field.
     * @param partner Breeding Partner
     */
    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Buffalo){
            Location location = IslandMap.getInstance().getLocation(partner.getRow(), partner.getColumn());
            IslandMap.getInstance().addAnimal(new Buffalo(), location.getRow(), location.getColumn());
        }
    }
}

