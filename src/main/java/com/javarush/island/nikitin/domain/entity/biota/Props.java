package com.javarush.island.nikitin.domain.entity.biota;

import lombok.Getter;

@Getter
public class Props implements Cloneable {
    private final String name;
    private final String icon;
    private final double maxWeight;
    private final int maxSpeed;
    private final double maxFoodFeed;
    private final int maxCountUnit;
    private final double weight;

    private Props(PropsBuilder propsBuilder) {
        this.name = propsBuilder.name;
        this.icon = propsBuilder.icon;
        this.maxWeight = propsBuilder.maxWeight;
        this.maxSpeed = propsBuilder.maxSpeed;
        this.maxFoodFeed = propsBuilder.maxFoodFeed;
        this.maxCountUnit = propsBuilder.maxCountUnit;
        this.weight = propsBuilder.weight;
    }

    public static class PropsBuilder {
        private String name;
        private String icon;
        private double maxWeight;
        private int maxSpeed;
        private double maxFoodFeed;
        private int maxCountUnit;
        private double weight;

        public Props build() {
            return new Props(this);
        }

        public PropsBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public PropsBuilder setIcon(String icon) {
            this.icon = icon;
            return this;
        }

        public PropsBuilder setMaxWeight(double maxWeight) {
            this.maxWeight = maxWeight;
            return this;
        }

        public PropsBuilder setMaxSpeed(int maxSpeed) {
            this.maxSpeed = maxSpeed;
            return this;
        }

        public PropsBuilder setMaxFoodFeed(double maxFoodFeed) {
            this.maxFoodFeed = maxFoodFeed;
            return this;
        }

        public PropsBuilder setMaxCountUnit(int maxCountUnit) {
            this.maxCountUnit = maxCountUnit;
            return this;
        }

        public PropsBuilder setWeight(double weight) {
            this.weight = weight;
            return this;
        }
    }

    @Override
    public Props clone() {
        try {
            return (Props) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "Props{" +
                "name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", maxWeight=" + maxWeight +
                ", maxSpeed=" + maxSpeed +
                ", maxFoodFeed=" + maxFoodFeed +
                ", maxCountUnit=" + maxCountUnit +
                '}';
    }
}
