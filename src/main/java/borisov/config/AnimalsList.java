package borisov.config;

import borisov.entity.Animals;
import borisov.entity.herbalanimal.Rabbit;
import borisov.entity.predatoranimal.Wolf;
import lombok.Getter;

@Getter
public enum AnimalsList {

    WOLF (Wolf.class),

    RABBIT (Rabbit.class),;


    private final Class<? extends Animals> animalClass;


    AnimalsList(Class<? extends Animals> animalClass) {


        this.animalClass = animalClass;
    }




}
