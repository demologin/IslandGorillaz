package com.javarush.island.khmelov.entity.organizm;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Organisms {
    private final Set<Organism> organisms = new LinkedHashSet<>();
    private Limit limit;
    private String icon = "?";

    private String letter = "?";

    public int size() {
        return organisms.size();
    }

    public double calculateSize() {
        int size = organisms.size();
        if (size > 0 && getLimit().getFlockSize() > 1) {
            double fullWeight = organisms
                    .stream()
                    .mapToDouble(Organism::getWeight)
                    .sum();
            Limit limit = getLimit();
            int flockSize = limit.getFlockSize();
            double part = fullWeight / limit.getMaxWeight();
            return part * flockSize;
        }
        return size;
    }

    public String getIcon() {
        update();
        return icon;
    }

    public String getLetter() {
        update();
        return letter;
    }

    public Limit getLimit() {
        update();
        return limit;
    }

    private void update() {
        if (limit == null) {
            if (!organisms.isEmpty()) {
                Organism organism = organisms
                        .iterator()
                        .next();
                limit = organism.getLimit();
                icon = organism.getIcon();
                letter = organism.getLetter();
            }
        }
    }

    public void addAll(Set<Organism> newOrganisms) {
        organisms.addAll(newOrganisms);
    }

    public boolean add(Organism organism) {
        return organisms.add(organism);
    }

    public boolean contains(Organism organism) {
        return organisms.contains(organism);
    }

    public boolean remove(Organism organism) {
        return organisms.remove(organism);
    }

    public void forEach(Consumer<? super Organism> action) {
        organisms.forEach(action);
    }

    public Stream<Organism> stream() {
        return organisms.stream();
    }

    public Iterator<Organism> iterator() {
        return organisms.iterator();
    }

    public boolean isEmpty() {
        return organisms.isEmpty();
    }
}
