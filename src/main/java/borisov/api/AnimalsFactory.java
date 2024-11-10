
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

    @Getter
    public Map<Class<? extends Animals>, Set<Animals>> allAnimalsMap = new ConcurrentHashMap<>();

    GameMap map;

    public AnimalsFactory(GameMap map) {
        this.map = map;
    }
    public synchronized void removeFromMap(Animals animal) {
        allAnimalsMap.remove(animal.getClass()).remove(animal);
    }

    public void startProduce() {
        List<Map<String, Object>> yamlConfig = ConfigReader.getYamlConfig();

        for (Map<String, Object> stringObjectMap : yamlConfig) {
            //pull out the class name from the config (вытаскиваем из конфига название класса)
            String type = (String) stringObjectMap.get("fullName");
            //look for the extracted class in enum (ищем в enum вытащенный класс)
            Class<? extends Animals> animalClazz =  AnimalsList.valueOf(type.toUpperCase()).getAnimalClass();

             Map<String,Integer> chances = new HashMap<>();
             // take chances (вытаскиваем шансы объекта)
            if (stringObjectMap.get("chancesPercent") instanceof Map<?, ?> chancesMap){
                for (Map.Entry<?, ?> entry : chancesMap.entrySet()) {
                    chances.put((String) entry.getKey(),(Integer) entry.getValue());
                }
            }


            int countAnimals = (int) stringObjectMap.get("defaultCount");


            for (int i = 0; i < countAnimals; i++) {
               Animals animal =  mapper.convertValue(stringObjectMap, animalClazz);
                allAnimalsMap.computeIfAbsent(animal.getClass(), k -> new HashSet<>()).add(animal);
                animal.setMap(map);
                animal.setChances(chances);
                animal.setAlive(true);
            }
        }
    }
}





