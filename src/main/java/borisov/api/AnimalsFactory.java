
package borisov.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import borisov.config.AnimalsList;
import borisov.entity.Animals;
import borisov.entity.map.GameMap;


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class AnimalsFactory {
    private final ObjectMapper mapper = new ObjectMapper();
    Lock lock = new ReentrantLock();

    public Map<Class<? extends Animals>, Set<Animals>> allAnimalsMap = new ConcurrentHashMap<>();

    GameMap map;

    public AnimalsFactory(GameMap map) {
        this.map = map;
    }

    public void removeFromMap(Animals animal) {
        lock.lock();
        try {
            Set<Animals> animalsSet = allAnimalsMap.get(animal.getClass());
            if (animalsSet != null && !animalsSet.isEmpty()) {

                animalsSet.remove(animal);

                if (animalsSet.isEmpty()) {
                    allAnimalsMap.remove(animal.getClass());
                }
            }

        } finally {
            lock.unlock();
        }
    }

    public void addToMap(Animals animal) {
        lock.lock();
        try {
            allAnimalsMap.computeIfAbsent(animal.getClass(), k -> new HashSet<>()).add(animal);
        } finally {
            lock.unlock();
        }
    }

    public synchronized Map<Class<? extends Animals>, Set<Animals>> getAllAnimalsMap() {
        lock.lock();
        try {
            return allAnimalsMap;
        } finally {
            lock.unlock();
        }
    }

    public void startProduce() {
        List<Map<String, Object>> yamlConfig = ConfigReader.getYamlConfig();

        for (Map<String, Object> stringObjectMap : yamlConfig) {
            //pull out the class name from the config (вытаскиваем из конфига название класса)
            String type = (String) stringObjectMap.get("fullName");
            //look for the extracted class in enum (ищем в enum вытащенный класс)
            Class<? extends Animals> animalClazz = AnimalsList.valueOf(type.toUpperCase()).getAnimalClass();

            Map<String, Integer> chances = new HashMap<>();
            // take chances (вытаскиваем шансы объекта)
            if (stringObjectMap.get("chancesPercent") instanceof Map<?, ?> chancesMap) {
                for (Map.Entry<?, ?> entry : chancesMap.entrySet()) {
                    chances.put((String) entry.getKey(), (Integer) entry.getValue());
                }
            }


            int countAnimals = (int) stringObjectMap.get("defaultCount");


            for (int i = 0; i < countAnimals; i++) {
                Animals animal = mapper.convertValue(stringObjectMap, animalClazz);
                allAnimalsMap.computeIfAbsent(animal.getClass(), k -> new HashSet<>()).add(animal);
                animal.setMap(map);
                animal.setChances(chances);
                animal.setAlive(true);
                animal.setAnimalsFactory(this);
            }
        }
    }
}





