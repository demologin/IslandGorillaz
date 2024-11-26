package com.javarush.island.popov.units;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.javarush.island.popov.interfaces.units.Eatable;
import com.javarush.island.popov.interfaces.units.Moveable;
import com.javarush.island.popov.interfaces.units.Reproduceable;
import com.javarush.island.popov.map.Cell;
import com.javarush.island.popov.units.fauna.herbivores.*;
import com.javarush.island.popov.units.fauna.predators.*;
import com.javarush.island.popov.units.flora.Grass;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;
@Getter
@Setter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Boar.class, name = "Boar"),
        @JsonSubTypes.Type(value = Buffalo.class, name = "Buffalo"),
        @JsonSubTypes.Type(value = Caterpillar.class, name = "Caterpillar"),
        @JsonSubTypes.Type(value = Deer.class, name = "Deer"),
        @JsonSubTypes.Type(value = Duck.class, name = "Duck"),
        @JsonSubTypes.Type(value = Goat.class, name = "Goat"),
        @JsonSubTypes.Type(value = Horse.class, name = "Horse"),
        @JsonSubTypes.Type(value = Mouse.class, name = "Mouse"),
        @JsonSubTypes.Type(value = Mouse.class, name = "Mouse"),
        @JsonSubTypes.Type(value = Rabbit.class, name = "Rabbit"),
        @JsonSubTypes.Type(value = Sheep.class, name = "Sheep"),
        @JsonSubTypes.Type(value = Anaconda.class, name = "Anaconda"),
        @JsonSubTypes.Type(value = Bear.class, name = "Bear"),
        @JsonSubTypes.Type(value = Eagle.class, name = "Eagle"),
        @JsonSubTypes.Type(value = Fox.class, name = "Fox"),
        @JsonSubTypes.Type(value = Wolf.class, name = "Wolf"),
        @JsonSubTypes.Type(value = Grass.class, name = "Grass"),
})
public abstract class Unit implements Eatable, Moveable, Reproduceable, Cloneable {

    private  String name;
    private  String icon;
    private  double maxUnitWeight;
    private  int maxUnitsInCell;
    private  int maxUnitSpeedPerStep;
    private  double maxFoodForSaturation;
    private int percentProbably;
    private double weight;
    private Map<String,Integer> menuForAnimals;

    private final static AtomicLong idCounter = new AtomicLong(ThreadLocalRandom.current().nextLong());
    private long id = idCounter.incrementAndGet();
    private Class<? extends Unit> type = this.getClass();

    public Unit(String name, String icon, double maxUnitWeight, int maxUnitsInCell, int maxUnitSpeedPerStep, double maxFoodForSaturation,int percentProbably ) {
        this.name = name;
        this.icon = icon;
        this.maxUnitWeight = maxUnitWeight;
        this.maxUnitsInCell = maxUnitsInCell;
        this.maxUnitSpeedPerStep = maxUnitSpeedPerStep;
        this.maxFoodForSaturation = maxFoodForSaturation;
        this.percentProbably = percentProbably;
        this.weight = ThreadLocalRandom.current()
                .nextDouble(maxUnitWeight/2, maxUnitWeight);
        this.menuForAnimals = new LinkedHashMap<>();
    }

    protected Unit() {

    }

    public double wantsToEat(){
        return Math.min(maxUnitWeight-getWeight(),
                maxFoodForSaturation);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Unit unit = (Unit) o;

        return id == unit.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    protected Unit clone() throws CloneNotSupportedException {
        Unit clone = (Unit) super.clone();
        clone.id = idCounter.incrementAndGet();
        clone.weight = ThreadLocalRandom.current()
                .nextDouble(maxUnitWeight/3, maxUnitWeight);
        return clone;
    }
    public static <T extends Unit> T clone(T incomingUnit){

        try {
            return (T) incomingUnit.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public String toString() {
        return "Unit{" +
                "name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", maxUnitWeight=" + maxUnitWeight +
                ", maxUnitsInCell=" + maxUnitsInCell +
                ", maxUnitSpeedPerStep=" + maxUnitSpeedPerStep +
                ", maxFoodForSaturation=" + maxFoodForSaturation +
                ", weight=" + weight +
                ", id=" + id +
                ", type=" + type +
                ", percentProbably=" + percentProbably +
                ",menuForAnimals =" + menuForAnimals +
                '}';
    }


}
