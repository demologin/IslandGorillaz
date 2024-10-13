package com.javarush.island.siberia2.ui.tileFactory;

import javax.swing.*;
import java.awt.*;

public class TilePanel extends JPanel {
    private TileFiller tileFiller;
    private int islandWidth;
    private int islandHeight;
    private int[][] worldMap;

    public TilePanel(TileFiller tileFiller, int islandWidth, int islandHeight) {
        this.tileFiller = tileFiller;
        this.islandWidth = islandWidth;
        this.islandHeight = islandHeight;

        worldMap = tileFiller.generateWorld(islandWidth, islandHeight);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        tileFiller.renderWorld(g, worldMap, getWidth(), getHeight());
    }
}