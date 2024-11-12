package borisov.entity.predatoranimal;


import borisov.entity.Animals;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Set;


@JsonPropertyOrder({"fullName","simpleName", "weight", "moveSpeed"})
public class Wolf extends Predators {

    public final String fullName = "Wolf";


    public Wolf(){
        super();
    }


    public Wolf(Wolf original){
        super(original);
    }

    public Wolf reproduce() {
        Set<Animals> animals = this.getAnimalsFactory().getAllAnimalsMap().get(this.getClass());
        if(this.getChances().get("maxCountOnMap")<=animals.size() ) {
            return new Wolf(this);
        }else return null;
    }

    @Override
    public String toString() {
        return String.valueOf(simpleName);
    }
}
