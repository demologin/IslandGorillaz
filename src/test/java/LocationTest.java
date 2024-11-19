import com.javarush.island.nikitin.domain.entity.biota.Props;
import org.junit.jupiter.api.Test;

public class LocationTest {
@Test
    public void shouldBeAddUnit(){
        Props props = new Props.PropsBuilder().setIcon("sdhf;as").build();
        System.out.println(props.getIcon());

    }

}
