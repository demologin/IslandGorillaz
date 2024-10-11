package com.javarush.island.khmelov.config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PROTECTED)
public class Console {
    int showRows = Default.CONSOLE_SHOW_ROWS;
    int showCols = Default.CONSILE_SHOW_COLS;
    int cellCharCount = Default.CONSOLE_CELL_CHAR_COUNT;
}
