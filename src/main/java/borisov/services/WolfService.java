package borisov.services;

import borisov.api.AnimalsFactory;
import borisov.api.ChooseActionUtil;
import borisov.config.Action;
import borisov.config.MyConfig;
import borisov.entity.Animals;
import borisov.entity.map.Cell;
import borisov.entity.map.GameMap;
import java.util.*;
import java.util.concurrent.locks.Lock;

public class WolfService <T extends Animals> implements Runnable {
    private final GameMap map;
    private Set<? extends Animals> animals;
    private final AnimalsFactory animalsFactory;
    private Lock lock;
    private final Class<T> animalClass;

    public WolfService(GameMap map, AnimalsFactory animalsFactory, Class<T> animalClass) {
        this.animalClass = animalClass;
        this.map = map;
        this.animalsFactory = animalsFactory;
    }

    @Override
    public void run() {
        try {

            Set<Animals> animals = animalsFactory.getAllAnimalsMap().get(animalClass);

                List<Animals> animalsToDie = new ArrayList<>();
                List<Animals> animalsToReproduce = new ArrayList<>();
                if (animals != null && !animals.isEmpty()) {
                    try {
                        for (Animals animal : animals) {
                            Action action = ChooseActionUtil.action(animal);
                            System.out.println(animal + " " + action);
                            if (action == Action.DIE_YOU_SON_OF_THE_ANIMAL) {

                                Cell position = animal.getPosition();
                                position.removeFromCell(animal);

                                animalsToDie.add(animal);

                            }else if(action == Action.REPRODUCE){
                                animalsToReproduce.add(animal);

                            } else {
                                try {
                                    animal.doAction(action);

                                } catch (Exception e) {
                                  throw new RuntimeException(e);
                                }
                            }
                            animal.setWeight(animal.getWeight() - MyConfig.LOOSE_WEIGHT_PER_ROUND);

                        }
                        for (int i = 0; i < animalsToDie.size(); i++) {
                           animalsFactory.removeFromMap(animalsToDie.get(i));

                        }
                        for (int i = 0; i < animalsToReproduce.size(); i++) {
                            animalsToReproduce.get(i).doAction(Action.REPRODUCE);
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
