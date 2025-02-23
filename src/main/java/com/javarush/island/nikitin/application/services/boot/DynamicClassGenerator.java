package com.javarush.island.nikitin.application.services.boot;

import com.javarush.island.nikitin.application.constants.ClazzList;
import com.javarush.island.nikitin.application.constants.FailMessagesApp;
import com.javarush.island.nikitin.application.exception.AppException;
import com.javarush.island.nikitin.application.util.PathBuilder;
import com.sun.tools.javac.Main;
import net.bytebuddy.ByteBuddy;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Set;

public class DynamicClassGenerator {
    private final Yaml yaml;
    private final Set<Class<?>> cacheUnitClasses;
    private final String filterFile = "*.yaml";

    public DynamicClassGenerator(Set<Class<?>> gameClasses) {
        cacheUnitClasses = gameClasses;
        yaml = new Yaml();
    }

    public void findDynamicClass(String namePackage) {
        Path pathToScanDirectory = PathBuilder.getPathToScanDirectory(namePackage);

        try (DirectoryStream<Path> paths = Files.newDirectoryStream(pathToScanDirectory, filterFile)) {
            for (Path path : paths) {
                readYamlFile(path);
            }
        } catch (IOException e) {
            throw new AppException(FailMessagesApp.READING_DIRECTORY, e);
        }
    }

    private void readYamlFile(Path path) {
        try (InputStream inputStream = Files.newInputStream(path)) {
            Map<String, String> fileMap = yaml.load(inputStream);
            String className = fileMap.get("className");
            String superClassString = fileMap.get("superClass");

            Class<?> superClass = ClazzList.valueOf(superClassString).getClazz();
            Class<?> dynamicClass = createClass(superClass, className);

            cacheUnitClasses.add(dynamicClass);
        } catch (IOException e) {
            throw new AppException(FailMessagesApp.READING_FILE_YAML, e);
        }
    }

    private Class<?> createClass(Class<?> superClass, String className) {
        return new ByteBuddy()
                .subclass(superClass)
                .name(className)
                .make()
                .load(Main.class.getClassLoader())
                .getLoaded();
    }
}
