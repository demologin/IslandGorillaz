package com.javarush.island.siberia2.ui.tileFactory;

import javax.swing.*;
import java.awt.*;

public class TilePanel extends JPanel {
    private final TileFiller tileFiller;
    private final ObjectLayer objectLayer;

    public TilePanel(TileFiller tileFiller, ObjectLayer objectLayer) {
        this.tileFiller = tileFiller;
        this.objectLayer = objectLayer;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        tileFiller.renderWorld(g, getWidth(), getHeight());
        objectLayer.renderObjectLayer(g, getWidth(), getHeight());
    }

}