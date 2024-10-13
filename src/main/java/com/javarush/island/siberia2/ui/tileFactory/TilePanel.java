package com.javarush.island.siberia2.ui.tileFactory;

import javax.swing.*;
import java.awt.*;

public class TilePanel extends JPanel {
    private TileFiller tileFiller;
    private ObjectLayer objectLayer;
    private int islandWidth;
    private int islandHeight;
    private int[][] worldMap;
    private int[][] objectMap;

    public TilePanel(TileFiller tileFiller, ObjectLayer objectLayer, int islandWidth, int islandHeight) {
        this.tileFiller = tileFiller;
        this.objectLayer = objectLayer;
        this.islandWidth = islandWidth;
        this.islandHeight = islandHeight;

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