package com.javarush.island.nikitin.application.services.boot;

import com.javarush.island.nikitin.application.constants.AnnotationGoal;
import com.javarush.island.nikitin.application.exception.AppException;
import com.javarush.island.nikitin.application.util.PathBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

/**
 * The class finds classes marked with an annotation @GameUnit,
 * which is a marker for the game's unit classes.
 */
public final class ClassFinder {
    private final Set<Class<?>> cacheUnitClasses;

    public ClassFinder(Set<Class<?>> gameClasses) {
        cacheUnitClasses = gameClasses;
    }

    /**
     * Scans all folders from the root of the given directory
     * and tries to find classes marked with the @GameUnit annotation.
     *
     * @param namePackage - this is the package name where to start
     *                    searching for the game's unit classes.
     *                    For example, "com.javarush.island.nikitin.domain.entity"
     */

    public void start(String namePackage) {
        Path directory = PathBuilder.getPathToScanDirectory(namePackage);
        tryFindClass(namePackage, directory);
    }

    private void tryFindClass(String namePackage, Path directory) {
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

    private void checkClassIsGameUnit(String fullNameClass) {
        try {
            Class<?> aClass = Class.forName(fullNameClass);
            if (aClass.isAnnotationPresent(AnnotationGoal.GAME_UNIT.getValue())) {
                cacheUnitClasses.add(aClass);
            }
        } catch (ClassNotFoundException e) {
            throw new AppException(e);
        }
    }

    private String makeFullNameFile(String namePackage, Path pathToFile) {
        String fileSimpleName = pathToFile.getFileName().toString();
        if (fileSimpleName.endsWith(".class")) {
            fileSimpleName = fileSimpleName.replace(".class", "");
        }
        return namePackage + "." + fileSimpleName;
    }
}
