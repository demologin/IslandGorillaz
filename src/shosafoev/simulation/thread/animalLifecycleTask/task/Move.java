package shosafoev.simulation.thread.animalLifecycleTask.task;


import shosafoev.map.IslandMap;
import shosafoev.organizm.Animal;

import java.util.List;

public class Move implements Runnable {
    public void run() {
        List<Animal> animals = IslandMap.getInstance().getAllAnimals().stream().filter(c -> c.getStep() > 0).toList();
        for (Animal animal : animals) {
            animal.move();
        }
    }
}
