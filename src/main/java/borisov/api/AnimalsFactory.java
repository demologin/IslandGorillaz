
package borisov.api;

import borisov.config.AnimalsList;
import borisov.entity.Animals;
import borisov.entity.herbalanimal.Rabbit;
import borisov.entity.map.GameMap;
import borisov.entity.predatoranimal.Wolf;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


public class AnimalsFactory {
    @Getter
    List<Wolf> wolfs = new ArrayList<>();
    @Getter
    List<Rabbit> rabbits = new ArrayList<>();
    GameMap map;

    public AnimalsFactory(GameMap map) {
        this.map = map;
    }
    public void startProduce(AnimalsList animal, int count){
        switch (animal){
            case WOLF -> {
                for (int i = 0; i < count; i++) {
                    Wolf wolf = new Wolf(map);
                    wolfs.add(wolf);
                }
            }case RABBIT -> {
                for (int i = 0; i < count; i++) {
                    Rabbit rabbit = new Rabbit(map);
                    rabbits.add(rabbit);}
            }

        }


    }


}


