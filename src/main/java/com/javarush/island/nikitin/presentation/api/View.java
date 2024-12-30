package com.javarush.island.nikitin.presentation.api;

import com.javarush.island.nikitin.domain.usecase.EcoSystem;

public interface View{
    void displayData(EcoSystem ecoSystem);
}
