package borisov.api;

import borisov.config.Action;
import borisov.entity.Animals;

public class ChooseActionUtil {

    public static Action action(Animals animal){
        int weightAnimal = animal.getWeight();
        if (weightAnimal <= 0 || !animal.isAlive()){return Action.DIE_YOU_SON_OF_THE_ANIMAL;}
        if (weightAnimal < weightAnimal * 0.6){return Action.EAT;}
        return null;

    }
}
