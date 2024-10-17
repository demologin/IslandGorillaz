package com.javarush.island.siberia2.ui.tileFactory;

import com.javarush.island.siberia2.config.Settings;
import com.javarush.island.siberia2.entity.map.Cell;
import com.javarush.island.siberia2.entity.map.Island;
import com.javarush.island.siberia2.entity.plants.Plant;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PlantLayer {

    private final Island island;
    private final int tileSize;
    private final int scale;
    private final Settings settings;
    private final Map<String, BufferedImage> plantImages = new ConcurrentHashMap<>();

    public PlantLayer(Island island, Settings settings, int tileSize, int scale) {
        this.island = island;
        this.settings = settings;
        this.tileSize = tileSize;
        this.scale = scale;

        loadPlantImages();
    }

    private void loadPlantImages() {
        settings.getPlantsSettings().forEach((name, plantSettings) -> {
            BufferedImage plantImage = null;
            try {
                plantImage = ImageIO.read(getClass().getResourceAsStream(plantSettings.getImgPath()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            plantImages.put(name, plantImage);
        });
    }

    public void renderPlants(Graphics g, int width, int height) {
        int scaledTileSize = tileSize * scale;

        for (int y = 0; y < island.getHeight(); y++) {
            for (int x = 0; x < island.getWidth(); x++) {
                Cell cell = island.getCell(x, y);
                List<Plant> plants = cell.getPlants();

                int maxPlantsToDraw = Math.min(plants.size(), 1);
                int plantIndex = 0;

                for (Plant plant : plants.subList(0, maxPlantsToDraw)) {
                    BufferedImage plantImage = plantImages.get(plant.getSettings().getName());

                    if (plantImage != null) {
                        int drawX = x * scaledTileSize + (plantIndex % 2) * (tileSize / 2);
                        int drawY = y * scaledTileSize + (plantIndex / 2) * (tileSize / 2);

                        g.drawImage(plantImage, drawX, drawY, tileSize, tileSize, null);
                        plantIndex++;
                    }
                }
            }
        }
    }

}