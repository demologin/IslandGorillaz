package com.javarush.island.levchuk.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class EntityFactory {

    private static <T> T copyEntity(T entity) {
        Class<?> entityClass = entity.getClass();
        T newEntity;
        try {
            newEntity = (T) entityClass.getConstructor().newInstance();
            while (entityClass != null) {
                Field[] fields = entityClass.getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    field.set(newEntity, field.get(entity));
                }
                entityClass = entityClass.getSuperclass();
            }
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                 IllegalAccessException e) {
            throw new RuntimeException();
        }
        return newEntity;
    }
}
