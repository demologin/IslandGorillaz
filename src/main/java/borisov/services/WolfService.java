package borisov.services;

import borisov.api.AnimalsFactory;
import borisov.api.ChooseActionUtil;
import borisov.config.Action;
import borisov.entity.Animals;
import borisov.entity.map.Cell;
import borisov.entity.map.GameMap;
import borisov.entity.predatoranimal.Wolf;

import java.util.*;
import java.util.concurrent.locks.Lock;

public class WolfService implements Runnable {
    private GameMap map;

    private Set<? extends Animals> animals;
    private final AnimalsFactory animalsFactory;
    private Lock lock;

    public WolfService(GameMap map, AnimalsFactory animalsFactory) {
        this.map = map;
        this.animalsFactory = animalsFactory;
    }

    @Override
    public void run() {
        try {

            animals = animalsFactory.getAllAnimalsMap().get(Wolf.class);
            if (!(animals == null) && !animals.isEmpty()) {
                for (Iterator<? extends Animals> iterator = animals.iterator(); iterator.hasNext(); ) {
                    Animals animal = iterator.next();

                    Action action = ChooseActionUtil.action(animal);
                    System.out.println(action);
                    if (action == Action.DIE_YOU_SON_OF_THE_ANIMAL) {
                        Cell position = animal.getPosition();
                        position.removeFromCell(animal);

                        iterator.remove();


                    } else {

                        animal.doAction(action);
                        animal.setWeight(animal.getWeight() - 1);
                    }


                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
