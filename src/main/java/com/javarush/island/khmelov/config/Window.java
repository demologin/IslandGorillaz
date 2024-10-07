package com.javarush.island.khmelov.config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PROTECTED)
public class Window {
    int width = Default.WINDOW_WIDTH;
    int height = Default.WINDOW_HEIGHT;
    int cellIconCount = Default.WINDOW_CELL_ICON_COUNT;
}
