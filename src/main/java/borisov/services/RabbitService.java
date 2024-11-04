package borisov.services;

import borisov.entity.Animals;
import borisov.entity.herbalanimal.Rabbit;
import borisov.entity.map.GameMap;

import java.util.List;

public class RabbitService implements Runnable {


    private final List<? extends Animals> animals;




    public RabbitService(GameMap map, List<? extends Animals> animals) {

            this.animals = animals;
        }

        @Override
        public void run() {
            //System.out.println(map.toString());

            for (Animals animal : animals) {
                Rabbit animal1 = (Rabbit) animal;
                animal1.move();
            }

        }

}
