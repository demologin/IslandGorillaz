package com.javarush.island.nikitin.domain.entity.biota;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Property implements Cloneable {
    private final String name;
    private final String icon;
    @Setter
    private double weight;
    //private double dailyEnergyExpenditure

    @JsonCreator
    private Property(PropertyBuilder propertyBuilder) {
        this.name = propertyBuilder.name;
        this.icon = propertyBuilder.icon;
        this.weight = propertyBuilder.weight;
    }

    public static class PropertyBuilder {
        private String name;
        private String icon;
        private double weight;

        public Property build() {
            return new Property(this);
        }

        public PropertyBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public PropertyBuilder setIcon(String icon) {
            this.icon = icon;
            return this;
        }

        public PropertyBuilder setWeight(double weight) {
            this.weight = weight;
            return this;
        }
    }

    @Override
    public Property clone() {
        try {
            return (Property) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "Property{" +
                "name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", weight=" + weight +
                '}';
    }
}
