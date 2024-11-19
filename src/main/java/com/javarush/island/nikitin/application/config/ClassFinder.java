package com.javarush.island.nikitin.application.config;

import com.javarush.island.nikitin.application.exception.AppException;
import com.javarush.island.nikitin.domain.api.GameUnit;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


//TODO класс сканирует директорию и находит конкретные классы живующие на острове
// возвращает карту в которой тип класса в виде строки и значение как Class объект
public class ClassFinder {
    private static final Class<? extends Annotation> GAME_UNIT_CLASS = GameUnit.class;
    private final Set<Class<?>> cashUnitClasses = new HashSet<>();

    public void start(String[] pathToClassesUnits) {
        if (cashUnitClasses.isEmpty()) {
            Arrays.stream(pathToClassesUnits)
                    .forEach(this::tryFindClass);
        }
    }
    //метод получает путь до директории с классами которые ааннотированы
    private void tryFindClass(String pathToClass) {
        String normalizedPath = pathToClass.replace(".", File.separator);
        Path directory = returnPathDirectoryToClass(normalizedPath);
        if (Files.isDirectory(directory)) {
            try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(directory)) {
                for (Path pathClass : directoryStream) {
                    String fullNameClass = makeFullNameClass(pathToClass, pathClass);
                    Class<?> aClass = Class.forName(fullNameClass);
                    if (aClass.isAnnotationPresent(GAME_UNIT_CLASS)) {
                        cashUnitClasses.add(aClass);
                    }
                }
            } catch (ClassNotFoundException | IOException e) {
                throw new AppException(e);
            }
        } else {
            throw new AppException("Неверный каталог");
        }
    }

    public Set<Class<?>> getCashUnitClasses() {
        return new HashSet<>(cashUnitClasses);
    }

    private String makeFullNameClass(String pathToClass, Path pathClass) {
        String classSimpleName = pathClass.getFileName().toString();
        classSimpleName = classSimpleName.replace(".class", "");

        return pathToClass + "." + classSimpleName;
    }


    private Path returnPathDirectoryToClass(String path) {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        URI resourceURI = null;
        try {
            resourceURI = contextClassLoader.getResource(path).toURI();
        } catch (URISyntaxException | NullPointerException e) {
            throw new AppException(e);
        }
        return Paths.get(resourceURI);
    }


}
