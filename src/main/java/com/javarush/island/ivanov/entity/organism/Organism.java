package com.javarush.island.ivanov.entity.organism;

import com.javarush.island.ivanov.api.entity.Reproducible;
import com.javarush.island.ivanov.entity.map.Cell;
import com.javarush.island.ivanov.utils.OrganismsFactory;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Predicate;


@Getter
@Setter
public class Organism implements Reproducible {
    private String name;
    private String icon;
    private int amountMax;
    private boolean isReproduced;


    @Override
    public <T extends Organism> T reproduce(Cell cell) {
        if (!isReproduced) {
            CopyOnWriteArrayList<Organism> entities = cell.getResidents().get(getClass());
            if (entities.size() < amountMax) {
                Optional<Organism> nextParent = entities.stream().filter(Predicate.not(Organism::isReproduced)).findFirst();
                if (nextParent.isPresent()) {
                    T newEntity = (T) OrganismsFactory.getOrganism(this.getClass());
                    nextParent.get().setReproduced(true);
                    newEntity.setReproduced(true);
                    return newEntity;
                }
            }
        }
        return null;
    }
}
