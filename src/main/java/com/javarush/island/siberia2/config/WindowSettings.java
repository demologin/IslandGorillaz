package com.javarush.island.siberia2.config;

import lombok.Data;

@Data
public class WindowSettings {
    private int width;
    private int height;
    private int fps;
    private int tileSize;
    private int scale;
}