package com.javarush.island.stepanov.repository;

import com.javarush.island.stepanov.config.Setting;
import com.javarush.island.stepanov.entity.map.Cell;
import com.javarush.island.stepanov.entity.oganism.Organism;
import com.javarush.island.stepanov.services.AnimalService;
import com.javarush.island.stepanov.services.PlantService;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class EntityCreator {

    public Cell createRandomCell() {
        Cell cell = new Cell();
        cell.getResidentMap().put("Animal",getAllPrototipes());
        return cell;
    }

    public List<Organism> getAllPrototipes() {
        return Arrays.asList(Setting.PROTOTYPES);
    }



}
