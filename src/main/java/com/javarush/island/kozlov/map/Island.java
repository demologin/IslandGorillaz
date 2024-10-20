package com.javarush.island.kozlov.map;

import com.javarush.island.kozlov.entities.animals.herbivores.Bull;
import com.javarush.island.kozlov.entities.animals.herbivores.Duck;
import com.javarush.island.kozlov.entities.animals.herbivores.Goat;
import com.javarush.island.kozlov.entities.animals.herbivores.Hog;
import com.javarush.island.kozlov.entities.animals.herbivores.Horse;
import com.javarush.island.kozlov.entities.animals.herbivores.Mouse;
import com.javarush.island.kozlov.entities.animals.herbivores.Rabbit;
import com.javarush.island.kozlov.entities.animals.herbivores.Sheep;
import com.javarush.island.kozlov.entities.animals.herbivores.Worm;
import com.javarush.island.kozlov.entities.animals.predators.Bear;
import com.javarush.island.kozlov.entities.animals.predators.Eagle;
import com.javarush.island.kozlov.entities.animals.predators.Fox;
import com.javarush.island.kozlov.entities.animals.predators.Snake;
import com.javarush.island.kozlov.entities.animals.predators.Wolf;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Island {
    private final Location[][] locations;
    private final int width;
    private final int height;

    public Island(int width, int height) {
        this.width = width;
        this.height = height;
        locations = new Location[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                locations[i][j] = new Location(i, j);  // Передаем координаты в Location
                populateLocation(locations[i][j]);     // Населяем локацию животными
            }
        }
    }

    // Метод для получения соседних локаций
    public List<Location> getNeighboringLocations(Location currentLocation) {
        List<Location> neighbors = new ArrayList<>();
        int currentX = currentLocation.getX();
        int currentY = currentLocation.getY();

        // Проверяем соседние клетки (включая диагонали)
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue; // Пропускаем текущую клетку
                }
                int newX = currentX + i;
                int newY = currentY + j;

                // Проверяем, что соседние клетки не выходят за пределы карты
                if (newX >= 0 && newX < width && newY >= 0 && newY < height) {
                    neighbors.add(locations[newX][newY]);
                }
            }
        }
        return neighbors;
    }

    // Метод для инициализации животных на клетке
    private void populateLocation(Location location) {
        if (ThreadLocalRandom.current().nextDouble() < 0.2) {  // 20% шанс населить клетку животными
            // Создаем список животных на локации
            location.addAnimal(new Rabbit());
            location.addAnimal(new Bull());
            location.addAnimal(new Duck());
            location.addAnimal(new Hog());
            location.addAnimal(new Horse());
            location.addAnimal(new Mouse());
            location.addAnimal(new Sheep());
            location.addAnimal(new Worm());
            location.addAnimal(new Goat());
            location.addAnimal(new Wolf());
            location.addAnimal(new Snake());
            location.addAnimal(new Fox());
            location.addAnimal(new Eagle());
            location.addAnimal(new Bear());
        }
    }

    // Метод для получения случайной соседней локации
    public Location getRandomNeighboringLocation(Location currentLocation) {
        List<Location> neighbors = getNeighboringLocations(currentLocation);
        if (!neighbors.isEmpty()) {
            return neighbors.get(ThreadLocalRandom.current().nextInt(neighbors.size()));
        }
        return currentLocation;  // Если нет доступных соседей, возвращаем текущее местоположение
    }

    public Location[][] getLocations() {
        return locations;
    }
}
