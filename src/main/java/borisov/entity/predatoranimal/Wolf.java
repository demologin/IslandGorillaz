package borisov.entity.predatoranimal;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonPropertyOrder({"fullName","simpleName", "weight", "moveSpeed"})
public class Wolf extends Predators {

    public final String fullName = "Wolf";


    public Wolf(){
        super();
    }

    @Override
    public String toString() {
        return String.valueOf(simpleName);
    }
}
