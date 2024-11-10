package borisov.entity.predatoranimal;


import borisov.entity.map.GameMap;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@JsonPropertyOrder({"fullName","simpleName", "weight", "moveSpeed"})
public class Wolf extends Predators {
    @Getter
    private int moveSpeed;
    public final String fullName = "Wolf";
    @Getter@Setter
    private int weight;
    @Setter@Getter
    private Map<String,Integer> chances;
    @Getter@Setter
    public boolean isAlive ;




    public Wolf(){
        super();
    }


    @Override
    public char getSimpleName() {
        return simpleName;
    }

    @Override
    public void eat() {

    }

    @Override
    public void reproduce() {

    }

    @Override
    public String toString() {
        return String.valueOf(simpleName);
    }
}
