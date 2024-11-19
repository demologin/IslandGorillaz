package com.javarush.island.nikitin.domain.api;

import com.javarush.island.nikitin.domain.entity.biota.Props;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface InjectProps {
     String name();
     String icon();
     double maxWeight();
     double currentWeight() default 0;
     int maxSpeed() default 0;
     double maxFoodFeed() default 0d;
     int maxCountUnit();

}
