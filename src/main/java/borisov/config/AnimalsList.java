package borisov.config;

import java.util.HashMap;
import java.util.Map;

public enum AnimalsList {
    WOLF (1),
    RABBIT (0),;

    private final int count;
    AnimalsList(int count) {
       this.count = count;
    }

    public static Map<AnimalsList, Integer> getAll() {
        Map<AnimalsList , Integer> mapOfAnimals = new HashMap<>();
        for (AnimalsList value : AnimalsList.values()) {
            mapOfAnimals.put(value, value.count);
        }
        return mapOfAnimals;
    }
}
