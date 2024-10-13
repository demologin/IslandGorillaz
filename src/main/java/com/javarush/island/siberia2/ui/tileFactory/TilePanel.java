package com.javarush.island.siberia2.ui.tileFactory;

import javax.swing.*;
import java.awt.*;

public class TilePanel extends JPanel {
    private final TileFiller tileFiller;
    private final ObjectLayer objectLayer;
    private final int[][] worldMap;
    private final int[][] objectMap;

    public TilePanel(TileFiller tileFiller, ObjectLayer objectLayer, int islandWidth, int islandHeight) {
        this.tileFiller = tileFiller;
        this.objectLayer = objectLayer;

        worldMap = tileFiller.generateWorld(islandWidth, islandHeight);
        objectMap = objectLayer.generateObjectLayer(worldMap, islandWidth, islandHeight);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        tileFiller.renderWorld(g, worldMap, getWidth(), getHeight());
        objectLayer.renderObjectLayer(g, objectMap, getWidth(), getHeight());
    }
}