package com.javarush.island.nikitin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
@Getter
public class POJO {
    @JsonIgnore
    private final int rows = 5;
    @JsonIgnore
    private final int columns = 5;
    @JsonIgnore
    private final int startData = 10;

    private final double percentFillingLocation = 1d;

    private POJO(){}
}
