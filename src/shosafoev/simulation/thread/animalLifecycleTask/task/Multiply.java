package shosafoev.simulation.thread.animalLifecycleTask.task;
import shosafoev.map.IslandMap;
import shosafoev.map.Location;
import shosafoev.organizm.Animal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Multiply implements Runnable{
    private int babies;
    private final CountDownLatch latch;

    public Multiply(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        babies = 0;
        List<Animal> animals = IslandMap.getInstance().getAllAnimals();
        List<Animal> animalsMultiplied = new ArrayList<>();
        if (animals.size() > 0) {
            for (Animal currentAnimal : animals) {
                if (!animalsMultiplied.contains(currentAnimal)) {
                    Location location = IslandMap.getInstance().
                            getLocation(currentAnimal.getRow(), currentAnimal.getColumn());
                    List<Animal> locationAnimals = location.getAnimals();

                    if (locationAnimals.size() > 1) {
                        locationAnimals = locationAnimals.stream().filter(c -> c.getName().
                                equals(currentAnimal.getName()) && c != currentAnimal).toList();

                        if (locationAnimals.size() > 0) {
                            Animal partner = locationAnimals.get(0);

                            if (!animalsMultiplied.contains(partner)) {
                                currentAnimal.multiply(partner);

                                animalsMultiplied.add(currentAnimal);
                                animalsMultiplied.add(partner);

                                babies++;
                            }
                        }
                    }
                }
            }
        }
        latch.countDown();
    }

    public int getBabies() {
        return babies;
    }
}
