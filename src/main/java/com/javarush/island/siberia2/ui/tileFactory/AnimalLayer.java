package com.javarush.island.siberia2.ui.tileFactory;

import com.javarush.island.siberia2.config.Constants;
import com.javarush.island.siberia2.config.Settings;
import com.javarush.island.siberia2.entity.animals.Animal;
import com.javarush.island.siberia2.entity.map.Cell;
import com.javarush.island.siberia2.entity.map.Island;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
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
        settings.getAnimalsSettings().forEach((name, animalSettings) -> {
            String imgPath = animalSettings.getImgPath();
            BufferedImage image = loadImageFromResources(imgPath);
            if (image != null) {
                animalImages.put(name, image);
            }
        });
    }

    private BufferedImage loadImageFromResources(String resourcePath) {
        try {
            return ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(resourcePath)));
        } catch (Exception e) {
            System.err.println(Constants.ERROR_LOAD_IMG + resourcePath);
            e.printStackTrace();
            return null;
        }
    }

    public void renderAnimals(Graphics g) {
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
                        int drawX = x * scaledTileSize + (animalIndex % 2) * (tileSize / 2);
                        int drawY = y * scaledTileSize + (animalIndex / 2) * (tileSize / 2);

                        g.drawImage(animalImage, drawX, drawY, tileSize, tileSize, null);
                        animalIndex++;
                    }
                }

                String countText = String.valueOf(animals.size());
                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial", Font.BOLD, 12));
                g.drawString(countText, x * scaledTileSize + 2, y * scaledTileSize + 12);
            }
        }
    }

}
