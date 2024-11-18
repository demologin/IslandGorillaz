package shosafoev.simulation.thread.animalLifecycleTask.task;
import shosafoev.map.IslandMap;
import shosafoev.map.Location;
import shosafoev.organizm.Animal;
import shosafoev.simulation.SettingUpTheMenu;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class HpDecrease implements Runnable{
    private double percentOfHpToDecrease = 15;
    private final CountDownLatch latch;
    private int animalsDiedByHungry;

    /**
     * Class Constructor
     * @param latch CountDownLatch counter for synchronizing streams
     */
    public HpDecrease(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        animalsDiedByHungry = 0;
        List<Animal> animals = IslandMap.getInstance().getAllAnimals().stream().filter(c -> c.getMaxHp() > 0).toList();
        if (SettingUpTheMenu.getInstance().getTimeNow() / 60 >= 3) {
            percentOfHpToDecrease = percentOfHpToDecrease * 2;
        }
        for (Animal animal : animals) {
            double hpToDecrease = animal.getMaxHp() * percentOfHpToDecrease / 100.0;
            if (animal.getHp() - hpToDecrease > 0) {
                animal.setHp(animal.getHp() - hpToDecrease);
            } else {
                Location location = IslandMap.getInstance().getLocation(animal.getRow(), animal.getColumn());
                IslandMap.getInstance().removeAnimal(animal, location.getRow(), location.getColumn());
                animalsDiedByHungry++;
            }
        }
        latch.countDown();
    }
    public int getAnimalsDiedByHungry() {
        return animalsDiedByHungry;
    }
}
