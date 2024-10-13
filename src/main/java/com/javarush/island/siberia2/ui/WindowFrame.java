package com.javarush.island.siberia2.ui;

import com.javarush.island.siberia2.config.ConfigLoader;
import com.javarush.island.siberia2.config.Constants;
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

    public WindowFrame() {
        initUI();
    }

    private void initUI() {
        setTitle(Constants.WINDOW_NAME);
        setSize(windowWidth, windowHeight);
        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        TileManager groundAssetToTile = new TileManager(Constants.GRASS_PATH_X16, tileSize);
        TileManager rocksAssetToTile = new TileManager(Constants.ROCKS_PATH_X16, tileSize);
        TileManager treesAssetToTile = new TileManager(Constants.TREES_PATH_X16, tileSize);
        TileManager wheatAssetToTile = new TileManager(Constants.WHEATS_PATH_X16, tileSize);

        TileFiller tileFiller = new TileFiller(groundAssetToTile, tileSize, scale);
        ObjectLayer objectLayer = new ObjectLayer(rocksAssetToTile, treesAssetToTile, wheatAssetToTile, tileSize, scale);

        TilePanel tilePanel = new TilePanel(tileFiller, objectLayer, islandWidth, islandHeight);
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