package com.javarush.island.nikitin.domain.entity.biota;

import com.javarush.island.nikitin.domain.entity.biota.animals.herbivores.Rabbit;
import com.javarush.island.nikitin.domain.entity.biota.animals.predators.Wolf;
import com.javarush.island.nikitin.domain.entity.map.Location;
import com.javarush.island.nikitin.domain.util.Biotas;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class BiotaTest {
    Wolf biota;
    LimitData limitData;
    Property property;
    PreferenceMenu preferenceMenu;
    Location habitat;

    @BeforeEach
    void setUp() {
        limitData = Mockito.mock(LimitData.class);
        property = Mockito.mock(Property.class);
        preferenceMenu = Mockito.mock(PreferenceMenu.class);
        habitat = Mockito.mock(Location.class);
        biota = Mockito.spy(new Wolf(limitData, property, preferenceMenu));
    }

    @Test
    void findPrey_whenSuccessFind_thenReturnOptionalPrey() {
        String nameItemMenu = "Rabbit";
        Integer choice = 100;
        boolean isPresentPopulation = true;
        Biota preyRabbit = Mockito.mock(Rabbit.class);
        Optional<Biota> preyExpected = Optional.of(preyRabbit);
        Map.Entry<String, Integer> entry = new AbstractMap.SimpleImmutableEntry<>(nameItemMenu, choice);
        Mockito.when(preferenceMenu.getAnyItemMenu()).thenReturn(Optional.of(entry));
        Mockito.when(habitat.isPresentPopulation(nameItemMenu)).thenReturn(isPresentPopulation);
        Mockito.when(habitat.getRandomBiotaByNameCommunity(nameItemMenu)).thenReturn(preyExpected);

        Optional<Biota> preyActual = biota.findPrey(habitat, preferenceMenu);

        Assertions.assertEquals(preyExpected, preyActual);
    }

    @Test
    void findPrey_whenNoSuccessChoice_thenReturnOptionalEmpty() {
        String nameItemMenu = "Rabbit";
        Integer choice = 0;
        Map.Entry<String, Integer> entry = new AbstractMap.SimpleImmutableEntry<>(nameItemMenu, choice);
        Mockito.when(preferenceMenu.getAnyItemMenu()).thenReturn(Optional.of(entry));
        Optional<Biota> preyExpected = Optional.empty();

        Optional<Biota> preyActual = biota.findPrey(habitat, preferenceMenu);

        Assertions.assertEquals(preyExpected, preyActual);
    }

    @Test
    void eat_temporarySolution() {
        Biota prey = Mockito.mock(Biota.class);
        double sizePiece = 2.0d;
        double futureWeight = 3.0d;
        double preyWeight = 6.0d;
        try (MockedStatic<Biotas> biotas = Mockito.mockStatic(Biotas.class)) {
            biotas.when(() -> Biotas.computeSizeOfPiece(biota)).thenReturn(sizePiece);
            biotas.when(() -> Biotas.fetchWeight(prey)).thenReturn(preyWeight);
            biotas.when(() -> Biotas.isCriticalWeight(prey, futureWeight)).thenReturn(false);

            biota.eat(prey, habitat);

            biotas.verify(() -> Biotas.checkLifeStatus(prey), Mockito.times(1));
            biotas.verify(() -> Biotas.isAtMaxWeight(biota), Mockito.times(1));
            biotas.verify(() -> Biotas.fetchWeight(prey), Mockito.times(2));
        }
    }

    @Test
    void reproduce_whenCriticalWeight_thenUnsuccessfulReproduction() {
        double weight = 6.0d;
        double maxWeight = 6.0d;
        double pctReproduction = 1.0d;
        double pctMinWeightForSurvival = 1.0d;
        boolean hasPartner = true;
        boolean successfulLunch = true;
        Mockito.when(habitat.checkPartner(any(String.class), any(int.class))).thenReturn(hasPartner);
        Mockito.when(limitData.pctReproduction()).thenReturn(pctReproduction);
        Mockito.when(limitData.maxWeight()).thenReturn(maxWeight);
        Mockito.when(limitData.pctMinWeightForSurvival()).thenReturn(pctMinWeightForSurvival);
        Mockito.when(property.getWeight()).thenReturn(weight);

        biota.reproduce(habitat, successfulLunch);

        Mockito.verify(biota, Mockito.never()).updateWeight(Mockito.any(double.class));
        Mockito.verify(property, Mockito.never()).setWeight(weight);
        Mockito.verify(habitat, Mockito.never()).addUnitLocation(biota);
    }

    @Test
    void reproduce_whenAllConditionsMet_thenSuccessfulReproduction() {
        double weight = 6.0d;
        double maxWeight = 6.0d;
        double futureWeight = 3.0d;
        double pctReproduction = 1.0d;
        double pctMinWeightForSurvival = 0.0d;
        boolean hasPartner = true;
        boolean successfulLunch = true;
        Mockito.when(habitat.checkPartner(any(String.class), any(int.class))).thenReturn(hasPartner);
        Mockito.when(limitData.pctReproduction()).thenReturn(pctReproduction);
        Mockito.when(limitData.maxWeight()).thenReturn(maxWeight);
        Mockito.when(limitData.pctMinWeightForSurvival()).thenReturn(pctMinWeightForSurvival);
        Mockito.when(property.getWeight()).thenReturn(weight);

        biota.reproduce(habitat, successfulLunch);

        Mockito.verify(biota, Mockito.times(1)).updateWeight(futureWeight);
        Mockito.verify(property, Mockito.times(1)).setWeight(futureWeight);
        Mockito.verify(habitat, Mockito.times(1)).addUnitLocation(any(Biota.class));
    }
}