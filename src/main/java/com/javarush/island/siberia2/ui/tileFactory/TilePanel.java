package com.javarush.island.siberia2.ui.tileFactory;

import javax.swing.*;
import java.awt.*;

public class TilePanel extends JPanel {
    private final TileFiller tileFiller;
    private final ObjectLayer objectLayer;
    private final AnimalLayer animalLayer;
    private final PlantLayer plantLayer;

    public TilePanel(TileFiller tileFiller, ObjectLayer objectLayer, AnimalLayer animalLayer, PlantLayer plantLayer) {
        this.tileFiller = tileFiller;
        this.objectLayer = objectLayer;
        this.animalLayer = animalLayer;
        this.plantLayer = plantLayer;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        tileFiller.renderWorld(g, getWidth(), getHeight());
        objectLayer.renderObjectLayer(g, getWidth(), getHeight());
        plantLayer.renderPlants(g, getWidth(), getHeight());
        animalLayer.renderAnimals(g, getWidth(), getHeight());
    }

}