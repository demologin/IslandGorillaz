package borisov.entity.herbalanimal;


import borisov.entity.Animals;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Set;

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
