package com.javarush.island.nikitin.application.config;

import com.javarush.island.nikitin.application.util.ClassFinder;
import com.javarush.island.nikitin.domain.api.GameUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

class ClassFinderTest {
    @GameUnit
    public static class TestGameUnit {
    }

    @Test
    @DisplayName("shouldSearchForGameUnitClasses")
    void start() {
        ClassFinder classFinder = new ClassFinder();
        TestGameUnit testGameUnit = new TestGameUnit();

        String testPackageName = testGameUnit.getClass().getPackageName();
        classFinder.start(testPackageName);
        Set<Class<?>> testCashUnitClasses = classFinder.getCacheUnitClasses();

        Class<?> expectedClass = TestGameUnit.class;
        Assertions.assertTrue(testCashUnitClasses.contains(expectedClass));
    }
}