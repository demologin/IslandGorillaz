package borisov.entity.herbalanimal;

import borisov.config.Action;
import borisov.entity.Animals;
import borisov.entity.map.Cell;
import borisov.entity.map.GameMap;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Rabbit extends Herbals{

    public final String fullName = "Rabbit";

    public Rabbit(){
        super();
    }



    @Override
    public String toString() {
        return String.valueOf(simpleName);
    }
}
