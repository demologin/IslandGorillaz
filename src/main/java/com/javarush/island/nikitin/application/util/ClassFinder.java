package com.javarush.island.nikitin.application.util;

import com.javarush.island.nikitin.application.constants.AnnotationGoal;
import com.javarush.island.nikitin.application.exception.AppException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

/**
 * The class finds classes marked with an annotation @GameUnit,
 * which is a marker for the game's unit classes.
 */
public class ClassFinder {
    private static final Set<Class<?>> cacheUnitClasses = new HashSet<>();

    public static Set<Class<?>> getCacheUnitClasses() {
        return new HashSet<>(cacheUnitClasses);
    }

    /**
     * The method tries to find classes marked with the @GameUnit annotation
     *
     * @param namePackage - this is the package name where to start
     *                    searching for the game's unit classes.
     *                    For example, "com.javarush.island.nikitin.domain.entity"
     */

    public static void start(String namePackage) {
        Path directory = PathBuilder.getPathToScanDirectory(namePackage);
        tryFindClass(namePackage, directory);
    }

    private static void tryFindClass(String namePackage, Path directory) {
        try (var directoryStream = Files.newDirectoryStream(directory)) {
            for (Path pathToFile : directoryStream) {
                String fullNameFile = makeFullNameFile(namePackage, pathToFile);
                if (Files.isRegularFile(pathToFile)) {
                    checkClassIsGameUnit(fullNameFile);
                } else if (Files.isDirectory(pathToFile)) {
                    start(fullNameFile);
                }
            }
        } catch (IOException e) {
            throw new AppException(e);
        }
    }

    private static void checkClassIsGameUnit(String fullNameClass) {
        try {
            Class<?> aClass = Class.forName(fullNameClass);
            if (aClass.isAnnotationPresent(AnnotationGoal.GAME_UNIT.getValue())) {
                cacheUnitClasses.add(aClass);
            }
        } catch (ClassNotFoundException e) {
            throw new AppException(e);
        }
    }

    private static String makeFullNameFile(String namePackage, Path pathToFile) {
        String classSimpleName = pathToFile.getFileName().toString();
        if (classSimpleName.endsWith(".class")) {
            classSimpleName = classSimpleName.replace(".class", "");
        }
        return namePackage + "." + classSimpleName;
    }
}
