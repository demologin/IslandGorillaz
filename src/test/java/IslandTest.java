import com.javarush.island.nikitin.application.services.PreparationService;
import com.javarush.island.nikitin.domain.entity.biota.Biota;
import com.javarush.island.nikitin.domain.entity.map.Location;
import com.javarush.island.nikitin.domain.usecase.EcoSystem;
import org.junit.jupiter.api.Test;

public class IslandTest {
    @Test
    public void fillLocation() {
        //BootConfiguration.load(biotaPrototype);
        EcoSystem ecoSystem = new EcoSystem();
        //new PreparationService(ecoSystem, entityService).prepareIslandData();
        Location[][] locations = ecoSystem.getIsland().getLocation();
        for (Location[] location : locations) {
            for (Location value : location) {
                for (var m : value.getPopulations().entrySet()) {
                    System.out.println(m.getKey() + " :" + m.getValue().size());
                    for (Biota biota : m.getValue()) {
                        System.out.println("\t" + biota.getProps().getName() + " :" + biota.getId());
                    }
                }
            }
        }
    }
}
