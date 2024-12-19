package com.javarush.island.nikitin.application.config;

import com.javarush.island.nikitin.application.constants.FailMessagesApp;
import com.javarush.island.nikitin.application.exception.AppException;
import com.javarush.island.nikitin.domain.api.InjectProps;
import com.javarush.island.nikitin.domain.entity.biota.Biota;
import com.javarush.island.nikitin.domain.entity.biota.Props;
import com.javarush.island.nikitin.domain.entity.map.navigation.Navigator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PrototypeCreator {
    private final Map<String, Biota> cashPrototypes = new HashMap<>();

    public void configure(Set<Class<?>> typeClasses) {
        typeClasses.forEach(this::createBiotaInstance);
    }

    public Map<String, Biota> flush() {
        return new HashMap<>(cashPrototypes);
    }

    private void createBiotaInstance(Class<?> clazz) {
        String nameCommunity = clazz.getSimpleName();
        if (!cashPrototypes.containsKey(nameCommunity)) {
            Biota unit;
            Props props = scanUseAnnotations(clazz);
            try {
                Constructor<?> constructor = clazz.getConstructor(Props.class);
                unit = (Biota) constructor.newInstance(props);
                cashPrototypes.put(nameCommunity, unit);
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                     InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private Props scanUseAnnotations(Class<?> clazz) {
        if (!clazz.isAnnotationPresent(AnnotationGoal.INJECT_PROPS.getValue())) {
            return new Props.PropsBuilder().build();
        }
        InjectProps annotation = (InjectProps) clazz.getAnnotation(AnnotationGoal.INJECT_PROPS.getValue());
        return new Props.PropsBuilder()
                .setName(annotation.name())
                .setIcon(annotation.icon())
                .setMaxWeight(annotation.maxWeight())
                .setMaxSpeed(annotation.maxSpeed())
                .setMaxFoodFeed(annotation.maxFoodFeed())
                .setMaxCountUnit(annotation.maxCountUnit())
                .build();
    }

    public void setNavigatorIntoProto(Navigator navigator) {
        if(cashPrototypes.isEmpty()){
            throw new AppException(FailMessagesApp.CASH_PROTO_IS_EMPTY);
        }
        cashPrototypes.values().forEach(el -> el.setNavigator(navigator));
    }
}
