package com.javarush.island.nikitin.application.util;

import com.javarush.island.nikitin.application.exception.AppException;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Optional;

public class PathBuilder {
    private PathBuilder() {
    }

    public static Optional<URI> getPathToResourceYaml(Class<?> clazz) {
        String simpleName = clazz.getSimpleName();
        String path = "/nikitin/" + simpleName + ".yaml";
        URL resource = clazz.getResource(path);
        if (resource != null) {
            try {
                return Optional.of(resource.toURI());
            } catch (URISyntaxException e) {
                throw new AppException(e);
            }
        }
        return Optional.empty();
    }

    public static Path getPathToScanDirectory(String namePackage) {
        String namePackageNormalize = namePackage.replace(".", "/");
        ClassLoader contextClassLoader = ClassLoader.getSystemClassLoader();
        try {
            URI uri = Objects.requireNonNull(contextClassLoader.getResource(namePackageNormalize)).toURI();
            return Path.of(uri);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
