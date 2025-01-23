package com.javarush.island.nikitin.domain.usecase;

import com.javarush.island.nikitin.domain.entity.biota.Biota;
import com.javarush.island.nikitin.domain.entity.biota.LimitData;
import com.javarush.island.nikitin.domain.repository.RegistryProto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ExtendWith(MockitoExtension.class)
class PopulationServiceTest {

    private PopulationService populationService;
    private RegistryProto registryProto;
    private Biota biota;
    private LimitData limitData;

    @BeforeEach
    void setUp() {
        populationService = new PopulationService();
        registryProto = Mockito.mock(RegistryProto.class);
        biota = Mockito.mock(Biota.class);
        limitData = Mockito.mock(LimitData.class);
        populationService.setRegistryProto(registryProto);
    }


    @Test
    @DisplayName("When called empty population is returned")
    void createEmptyPopulations_whenCalled_thenReturnEmptyPopulation() {
        String name = "Wolf";
        Mockito.when(registryProto.registry()).thenReturn(Map.of(name, biota));

        Map<String, ConcurrentHashMap.KeySetView<Biota, Boolean>> result =
                populationService.createEmptyPopulationIntoLocation();
        int actual = result.size();
        int expected = 1;

        Assertions.assertTrue(result.get(name).isEmpty());
        Assertions.assertNotNull(result);
        Assertions.assertEquals(expected, actual);
    }


    @Test
    @DisplayName("When called non-empty population is returned")
    void createAllPopulations_whenCalled_thenReturnAllPopulations() {
        String name = "Wolf";
        Biota cloneBiota = Mockito.mock(Biota.class);
        Mockito.when(registryProto.registry()).thenReturn(Map.of(name, biota));
        Mockito.when(biota.getLimitData()).thenReturn(limitData);
        Mockito.when(limitData.maxCountUnit()).thenReturn(1);
        Mockito.when(biota.clone()).thenReturn(cloneBiota);

        Map<String, ConcurrentHashMap.KeySetView<Biota, Boolean>> resultMap =
                populationService.createAllPopulationIntoLocation();
        ConcurrentHashMap.KeySetView<Biota, Boolean> keySetView = resultMap.get(name);
        System.out.println(resultMap);
        int sizeAllActual = resultMap.size();
        int sizeAllExpected = 1;
        int sizePopulationActual = keySetView.size();
        int sizePopulationExpected = 1;

        Mockito.verify(biota).getLimitData();
        Mockito.verify(limitData).maxCountUnit();

        Assertions.assertEquals(sizeAllExpected, sizeAllActual);
        Assertions.assertEquals(sizePopulationExpected, sizePopulationActual);
        Assertions.assertTrue(keySetView.contains(cloneBiota));
        Assertions.assertFalse(keySetView.contains(biota));
    }
}