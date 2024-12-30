package com.javarush.island.nikitin.domain.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface InjectLimitData {
     double maxWeight();
     int maxSpeed() default 0;
     double maxFoodFeed() default 0d;
     int maxCountUnit();
     double dailyWeightLossPct() default 0d;

}
