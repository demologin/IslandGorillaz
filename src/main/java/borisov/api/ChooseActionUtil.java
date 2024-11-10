package borisov.api;

import borisov.config.Action;
import borisov.entity.Animals;

public class ChooseActionUtil {

    public static Action action(Animals animal){
        int weightAnimal = animal.getWeight();

        if (weightAnimal <= 0 || !animal.isAlive()){return Action.DIE_YOU_SON_OF_THE_ANIMAL;}
        else if (weightAnimal >= animal.getChances().get("fullWeight")
                && animal.isAlive()
                && MyRandomUtil.randomPercent(animal.getChances().get("chanceToReproduce"))){
            return Action.REPRODUCE;
        }
        else if (MyRandomUtil.randomPercent(animal.getChances().get("chanceToEat"))){
            return Action.EAT;
        }
        else if (weightAnimal < animal.getChances().get("criticalWeight")){
            return Action.EAT;
        }else {
            return Action.MOVE;
        }



    }
}
