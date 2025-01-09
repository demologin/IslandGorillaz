package com.javarush.island.stepanov.entity;


import com.javarush.island.stepanov.entity.map.GameMap;
import com.javarush.island.stepanov.repository.EntityCreator;
import com.javarush.island.stepanov.view.View;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Game {

    private final GameMap gameMap;
    private final EntityCreator entityCreator;
    private final View view;

}
