package com.javarush.island.levchuk.utils;

import com.javarush.island.levchuk.entities.Entity;
import lombok.Getter;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class EntityFactory {
    @Getter
    private final static Map<Class<? extends Entity>, Entity> entities = new HashMap<>();

    public void registerEntity(List<Entity> prototypes) {
        for (Entity entity : prototypes) {
            entities.put(entity.getClass(), entity);
        }
    }

    public static Entity getEntity(Class<? extends Entity> type) {
        return copyEntity(entities.get(type));
    }

    public static Class<? extends Entity> getEntityClass(String className) {
        return entities.keySet().stream()
                .filter(e -> e.getName().equals(className))
                .findFirst().get();

    }

    public static <T> T copyEntity(T entity) {
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
