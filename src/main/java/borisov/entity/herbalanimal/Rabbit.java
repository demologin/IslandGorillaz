package borisov.entity.herbalanimal;

import borisov.config.Action;
import borisov.entity.Animals;
import borisov.entity.map.Cell;
import borisov.entity.map.GameMap;
import borisov.entity.predatoranimal.Wolf;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Rabbit extends Herbals{

    public final String fullName = "Rabbit";

    public Rabbit(){
        super();
    }

    public Rabbit(Rabbit original){
        super(original);
    }
    public Rabbit reproduce() {
        Set<Animals> animals = this.getAnimalsFactory().getAllAnimalsMap().get(this.getClass());
        if(this.getChances().get("maxCountOnMap")<=animals.size() ) {
            return new Rabbit(this);
        }else return null;
    }
    @Override
    public String toString() {
        return String.valueOf(simpleName);
    }
}
