package com.javarush.island.nikitin.application.util;

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
    @DisplayName("When called then search for annotation @GameUnit and add class into cache")
    void start_whenCall_thenSearchGameUnitClassAndAddCache() {
        TestGameUnit testGameUnit = new TestGameUnit();
        String testPackageName = testGameUnit.getClass().getPackageName();
        Class<?> expectedClass = TestGameUnit.class;

        ClassFinder.start(testPackageName);
        Set<Class<?>> testCashUnitClasses = ClassFinder.getCacheUnitClasses();
        int expectedSize = testCashUnitClasses.size();
        int actualSize = 1;

        Assertions.assertTrue(testCashUnitClasses.contains(expectedClass));
        Assertions.assertEquals(expectedSize, actualSize);
    }

}