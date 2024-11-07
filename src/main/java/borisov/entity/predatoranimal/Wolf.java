package borisov.entity.predatoranimal;


import borisov.entity.map.GameMap;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@JsonPropertyOrder({"fullName","simpleName", "weight", "moveSpeed"})
public class Wolf extends Predators {

    public final String fullName = "Wolf";
    @Getter
    private final int moveSpeed = 4;

    @Getter@Setter
    private int weight = 30;

    public Wolf(){
        super();
    }

    public Wolf(GameMap map) {
        super(map);

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
