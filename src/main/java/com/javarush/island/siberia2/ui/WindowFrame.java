package com.javarush.island.siberia2.ui;

import com.javarush.island.siberia2.config.ConfigLoader;
import com.javarush.island.siberia2.config.Settings;
import com.javarush.island.siberia2.ui.tileFactory.ObjectLayer;
import com.javarush.island.siberia2.ui.tileFactory.TileFiller;
import com.javarush.island.siberia2.ui.tileFactory.TileManager;
import com.javarush.island.siberia2.ui.tileFactory.TilePanel;
import javax.swing.*;

public class WindowFrame extends JFrame implements Runnable {

    Settings settings = ConfigLoader.loadSettings();
    int islandWidth = settings.getIslandSettings().getWidth();
    int islandHeight = settings.getIslandSettings().getHeight();
    int windowWidth = settings.getWindowSettings().getWidth();
    int windowHeight = settings.getWindowSettings().getHeight();
    int tileSize = settings.getWindowSettings().getTileSize();
    int scale = settings.getWindowSettings().getScale();
    private TileFiller tileFiller;
    private ObjectLayer objectLayer;
    private TilePanel tilePanel;

    public WindowFrame() {
        initUI();
    }

    private void initUI() {
        setTitle("Island simulation");
        setSize(windowWidth, windowHeight);
        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        TileManager groundAssetToTile = new TileManager("/siberia2/tiles/Grass.png", tileSize);
        TileManager rocksAssetToTile = new TileManager("/siberia2/plants/Rocks.png", tileSize);
        TileManager treesAssetToTile = new TileManager("/siberia2/plants/Trees.png", tileSize);
        TileManager wheatAssetToTile = new TileManager("/siberia2/plants/Wheatfield.png", tileSize);

        tileFiller = new TileFiller(groundAssetToTile, tileSize, scale);
        objectLayer = new ObjectLayer(rocksAssetToTile, treesAssetToTile, wheatAssetToTile, tileSize, scale);

        tilePanel = new TilePanel(tileFiller, objectLayer, islandWidth, islandHeight);
        add(tilePanel);

        Sound sound = new Sound();
        sound.setFile(0);
        sound.play();
        sound.loop();

    }

    @Override
    public void run() {
        SwingUtilities.invokeLater(() -> setVisible(true));
    }

}