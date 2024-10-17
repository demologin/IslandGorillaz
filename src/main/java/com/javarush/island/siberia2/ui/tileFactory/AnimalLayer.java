package com.javarush.island.siberia2.ui.tileFactory;

import com.javarush.island.siberia2.config.Settings;
import com.javarush.island.siberia2.entity.animals.Animal;
import com.javarush.island.siberia2.entity.map.Cell;
import com.javarush.island.siberia2.entity.map.Island;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class AnimalLayer {
    private final Island island;
    private final int tileSize;
    private final int scale;
    private final Settings settings;
    private final Map<String, BufferedImage> animalImages = new ConcurrentHashMap<>();

    public AnimalLayer(Island island, Settings settings, int tileSize, int scale) {
        this.island = island;
        this.settings = settings;
        this.tileSize = tileSize;
        this.scale = scale;

        loadAnimalImages();
    }

    private void loadAnimalImages() {
        settings.getAnimalsSettings().forEach((animalName, animalSettings) -> {
            BufferedImage animalImage = null;
            try {
                animalImage = ImageIO.read(Objects.requireNonNull(getClass()
                        .getResourceAsStream(animalSettings.getImgPath())));
            } catch (IOException e) {
                e.printStackTrace();
            }
            animalImages.put(animalName, animalImage);
        });
    }

    public void renderAnimals(Graphics g, int width, int height) {
        int scaledTileSize = tileSize * scale;

        for (int y = 0; y < island.getHeight(); y++) {
            for (int x = 0; x < island.getWidth(); x++) {
                Cell cell = island.getCell(x, y);
                List<Animal> animals = cell.getAnimals();

                int maxAnimalsToDraw = Math.min(animals.size(), 4);
                int animalIndex = 0;

                for (Animal animal : animals.subList(0, maxAnimalsToDraw)) {
                    BufferedImage animalImage = animalImages.get(animal.getSettings().getName());

                    if (animalImage != null) {
                        int drawX = x * scaledTileSize + (animalIndex % 2 * (tileSize));
                        int drawY = y * scaledTileSize + (animalIndex / 2) * (tileSize);

                        if (drawX < width && drawY < height) {
                            g.drawImage(animalImage,
                                    drawX, drawY,
                                    tileSize * 2,
                                    tileSize * 2,
                                    null);
                        }
                        animalIndex++;
                    }
                }
            }
        }
    }

}