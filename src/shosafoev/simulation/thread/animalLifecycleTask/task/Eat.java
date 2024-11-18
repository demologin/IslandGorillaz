package shosafoev.simulation.thread.animalLifecycleTask.task;
import shosafoev.map.IslandMap;
import shosafoev.map.Location;
import shosafoev.organizm.Animal;
import shosafoev.organizm.DataOrganisms;
import shosafoev.organizm.plant.Plant;
import shosafoev.simulation.SettingUpTheMenu;
import shosafoev.simulation.thread.StatisticsOfTheIsland;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Eat implements Runnable {
    private final CountDownLatch latch;
    private int animalsEaten;

    /**
     * Class Constructor
     *
     * @param latch CountDownLatch counter for synchronizing streams
     */
    public Eat(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        animalsEaten = 0;
        List<Animal> animals = IslandMap.getInstance().getAllAnimals();
        List<DataOrganisms> lifeFormsEaten = new ArrayList<>();
        int countAnimalsStart = animals.size();

        if (countAnimalsStart > 0 && !animals.stream().
                filter(c -> !(c.getName().equals("Caterpillar"))).toList().isEmpty()) {
            Iterator<Animal> iterator = animals.iterator();

            while (iterator.hasNext()) {
                Animal currentAnimal = iterator.next();
                if (currentAnimal.getMaxHp() > 0) {
                    Location location = IslandMap.getInstance().
                            getLocation(currentAnimal.getRow(), currentAnimal.getColumn());
                    List<DataOrganisms> locationDataOrganisms = location.getDataOrganisms();

                    if (!locationDataOrganisms.isEmpty()) {
                        for (DataOrganisms dataOrganisms : locationDataOrganisms) {
                            if (currentAnimal.getChanceToEat(dataOrganisms.getName()) > 0 &&
                                    !(lifeFormsEaten.contains(dataOrganisms))) {
                                boolean isEaten = currentAnimal.eat(dataOrganisms);

                                if (isEaten) {
                                    if (dataOrganisms instanceof Animal animalEaten) {
                                        if (location.getAnimals().contains(animalEaten)) {
                                            IslandMap.getInstance().removeAnimal(animalEaten,
                                                    location.getRow(), location.getColumn());
                                        }
                                        lifeFormsEaten.add(animalEaten);
                                        animalsEaten++;
                                    } else {
                                        Plant plant = (Plant) dataOrganisms;
                                        if (!location.getPlants().isEmpty()) {
                                            IslandMap.getInstance().removePlant
                                                    (plant, location.getRow(), location.getColumn());
                                        }
                                    }
                                }
                                break;
                            }
                        }
                    }
                }
                iterator.remove();
            }
        } else if (countAnimalsStart == 0) {
            System.out.printf("YOU'VE LOST! ALL THE ANIMALS DIED ON %d DAY!",
                    StatisticsOfTheIsland.getCurrentDay());
            SettingUpTheMenu.getInstance().getExecutorService().shutdown();
            System.exit(0);
        } else {
            System.out.printf("THE CATERPILLARS HAVE WON! THEY ARE THE ONLY ONES LEFT ALIVE ON %d DAY!",
                    StatisticsOfTheIsland.getCurrentDay());
            SettingUpTheMenu.getInstance().getExecutorService().shutdown();
            System.exit(0);
        }
        latch.countDown();
    }

    public int getAnimalsEaten() {
        return animalsEaten;
    }
}
