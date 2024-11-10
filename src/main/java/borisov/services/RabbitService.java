package borisov.services;

import borisov.api.AnimalsFactory;
import borisov.entity.Animals;
import borisov.entity.herbalanimal.Rabbit;
import borisov.entity.map.GameMap;
import borisov.entity.predatoranimal.Wolf;

import java.util.List;
import java.util.Set;

public class RabbitService implements Runnable {
    private GameMap map;
    private final AnimalsFactory animalsFactory;
    private Set<? extends Animals> animals;




    public RabbitService(GameMap map, AnimalsFactory animalsFactory) {
        this.animalsFactory = animalsFactory;
            this.map=map;
        }

        @Override
        public void run() {
            animals = animalsFactory.getAllAnimalsMap().get(Rabbit.class);

            for (Animals animal : animals) {

                animal.move();
            }

        }

}
