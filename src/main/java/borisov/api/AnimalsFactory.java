
package borisov.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import borisov.config.AnimalsList;
import borisov.entity.Animals;
import borisov.entity.herbalanimal.Rabbit;
import borisov.entity.map.GameMap;
import borisov.entity.predatoranimal.Wolf;
import lombok.Getter;


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


public class AnimalsFactory {
    private final ObjectMapper mapper = new ObjectMapper();
    private final Map<String, Class<? extends Animals>> animalsClasses = new HashMap<>();
    List<Animals> animalsList = new ArrayList<>();
    @Getter
    public Map<Class<? extends Animals>, Set<Animals>> allAnimalsMap = new ConcurrentHashMap<>();
    @Getter
    List<Wolf> Wolfs = new ArrayList<>();
    @Getter
    List<Rabbit> Rabbits = new ArrayList<>();
    GameMap map;

    public AnimalsFactory(GameMap map) {
//        animalsClasses.put("Rabbit", Rabbit.class);
//        animalsClasses.put("Wolf", Wolf.class);
        this.map = map;
    }

    public void startProduce() {
        List<Map<String, Object>> yamlConfig = ConfigReader.getYamlConfig();

        for (Map<String, Object> stringObjectMap : yamlConfig) {
            //pull out the class name from the config (вытаскиваем из конфига название класса)
            String type = (String) stringObjectMap.get("fullName");
            //look for the extracted class in enum (ищем в enum вытащенный класс)
            Class<? extends Animals> animalClazz =  AnimalsList.valueOf(type.toUpperCase()).getAnimalClass();

            int countAnimals = (int) stringObjectMap.get("defaultCount");

            for (int i = 0; i < countAnimals; i++) {
               Animals animal =  mapper.convertValue(stringObjectMap, animalClazz);
                allAnimalsMap.computeIfAbsent(animal.getClass(), k -> new HashSet<>()).add(animal);
            }
        }
    }
}





