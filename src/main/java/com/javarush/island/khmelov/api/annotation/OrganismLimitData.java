package com.javarush.island.khmelov.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface OrganismLimitData {
    String name();

    String icon();

    double maxWeight();

    int maxCountInCell();

    int flockSize() default 1;

    int maxSpeed();

    double maxFood();

}
