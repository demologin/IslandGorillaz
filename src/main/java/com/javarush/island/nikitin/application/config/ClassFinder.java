package com.javarush.island.nikitin.application.config;

import com.javarush.island.nikitin.application.exception.AppException;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;


//TODO класс сканирует директорию и находит конкретные классы живующие на острове
// возвращает карту в которой тип класса в виде строки и значение как Class объект

/**
 * The class finds classes marked with an annotation @GameUnit,
 * which is a marker for the game's unit classes.
 */
public class ClassFinder {
    private final Set<Class<?>> cashUnitClasses = new HashSet<>();

    public Set<Class<?>> getCashUnitClasses() {
        return new HashSet<>(cashUnitClasses);
    }

    /**
     * The method tries to find classes marked with the @GameUnit annotation
     *
     * @param namePackage - this is the package name where to start
     *                    searching for the game's unit classes.
     *                    For example, "com.javarush.island.nikitin.domain.entity"
     */

    public void start(String namePackage) {
        tryFindClass(namePackage);
    }

    private void tryFindClass(String namePackage) {
        String normalizedPath = namePackage.replace(".", File.separator);
        Path directory = fullPathByNamePackage(normalizedPath);
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(directory)) {
            for (Path pathToFile : directoryStream) {
                String fullNameClass = makeFullNameClass(namePackage, pathToFile);
                if (Files.isRegularFile(pathToFile)) {
                    checkClassIsGameUnit(fullNameClass);
                } else if (Files.isDirectory(pathToFile)) {
                    tryFindClass(fullNameClass);
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
                cashUnitClasses.add(aClass);
            }
        } catch (ClassNotFoundException e) {
            throw new AppException(e);
        }
    }

    private String makeFullNameClass(String namePackage, Path pathToFile) {
        String classSimpleName = pathToFile.getFileName().toString();
        classSimpleName = classSimpleName.replace(".class", "");
        return namePackage + "." + classSimpleName;
    }

    private Path fullPathByNamePackage(String path) {
        ClassLoader contextClassLoader = ClassLoader.getSystemClassLoader();
        URI resourceURI = null;
        try {
            resourceURI = contextClassLoader.getResource(path).toURI();
        } catch (URISyntaxException | NullPointerException e) {
            throw new AppException(e);
        }
        return Paths.get(resourceURI);
    }
}
