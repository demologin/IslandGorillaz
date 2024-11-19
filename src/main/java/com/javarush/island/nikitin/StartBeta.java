package com.javarush.island.nikitin;


import com.javarush.island.nikitin.application.config.DefaultSettings;
import com.javarush.island.nikitin.domain.entity.biota.PreferenceMap;

public class StartBeta {
    static int[][] massif;
    public static void main(String[] args) {

        var prefMap = DefaultSettings.getPreferenceMapForUnit("Wolf");
        System.out.println("Wolf ============================");
        extracted(prefMap);

        prefMap = DefaultSettings.getPreferenceMapForUnit("Mouse");
        System.out.println("Mouse ============================");
        extracted(prefMap);


        prefMap = DefaultSettings.getPreferenceMapForUnit("dsfsdf");
        System.out.println("============================");
        extracted(prefMap);



    }

    private static void extracted(PreferenceMap prefMap) {
        var mapa = prefMap.foodChoiceProbabilities;
        for (var x : mapa.entrySet()){
            System.out.println(x.getKey() + " " + x.getValue());
        }
    }
}
