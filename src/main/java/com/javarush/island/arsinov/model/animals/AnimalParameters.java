package com.javarush.island.arsinov.model.animals;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class AnimalParameters {
    public static final Map<AnimalType, AnimalAttributes> PARAMETERS = new HashMap<>();

    static {
        PARAMETERS.put(AnimalType.WOLF, new AnimalAttributes(8, 3, 30, 50));
        PARAMETERS.put(AnimalType.BOA, new AnimalAttributes(15, 30, 1, 3));
        PARAMETERS.put(AnimalType.FOX, new AnimalAttributes(8, 30, 2, 2));
        PARAMETERS.put(AnimalType.GRIZZLY, new AnimalAttributes(500, 5, 2, 80));
        PARAMETERS.put(AnimalType.EAGLE, new AnimalAttributes(6, 20, 3, 1));
        PARAMETERS.put(AnimalType.DEER, new AnimalAttributes(300, 20, 4, 50));
        PARAMETERS.put(AnimalType.DUCK, new AnimalAttributes(1, 200, 4, 0.15));
        PARAMETERS.put(AnimalType.GOAT, new AnimalAttributes(700, 10, 3, 100));
        PARAMETERS.put(AnimalType.HORSE, new AnimalAttributes(60, 4, 20, 400));
        PARAMETERS.put(AnimalType.MOUSE, new AnimalAttributes(700, 10, 3, 100));
        PARAMETERS.put(AnimalType.RABBIT, new AnimalAttributes(2, 150, 2, 0.45));
        PARAMETERS.put(AnimalType.SHEEP, new AnimalAttributes(700, 10, 3, 100));
        PARAMETERS.put(AnimalType.WILD_BOAR, new AnimalAttributes(700, 10, 3, 100));
        PARAMETERS.put(AnimalType.BUFFALO, new AnimalAttributes(700, 10, 3, 100));
    }

    public static AnimalAttributes getAttributes(AnimalType type) {
        return PARAMETERS.get(type);
    }

    public static Set<AnimalType> getAnimalTypes() {
        return PARAMETERS.keySet();
    }
}
