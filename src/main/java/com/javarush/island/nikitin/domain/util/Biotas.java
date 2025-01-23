package com.javarush.island.nikitin.domain.util;

import com.javarush.island.nikitin.domain.constants.FailMessagesDomain;
import com.javarush.island.nikitin.domain.entity.biota.Biota;
import com.javarush.island.nikitin.domain.entity.biota.LimitData;
import com.javarush.island.nikitin.domain.exception.DomainException;
import java.util.concurrent.ThreadLocalRandom;

public final class Biotas {
    private Biotas() {
    }

    public static boolean isCriticalWeight(Biota biota, double targetWeight) {
        LimitData limitData = biota.getLimitData();
        double minWeightForSurvival = limitData.maxWeight() * limitData.pctMinWeightForSurvival();
        return targetWeight <= minWeightForSurvival;
    }

    public static double calculateWeightAfterDailyLoss(Biota biota) {
        LimitData limitData = biota.getLimitData();
        double dailyEnergyExpenditure = limitData.maxWeight() * limitData.pctDailyWeightLoss();
        return fetchWeight(biota) - dailyEnergyExpenditure;
    }

    public static boolean isSuccessRollReproduce(Biota biota) {
        LimitData limitData = biota.getLimitData();
        return ThreadLocalRandom.current().nextDouble() < limitData.pctReproduction();
    }

    public static int makeCountStepRandom(Biota biota) {
        int bound = 1;
        int maxCountStepBound = biota.getLimitData().maxSpeed() + bound;
        return ThreadLocalRandom.current().nextInt(maxCountStepBound);
    }

    public static boolean isAtMaxWeight(Biota biota) {
        double currentWeight = fetchWeight(biota);
        double maxWeight = biota.getLimitData().maxWeight();
        return currentWeight >= maxWeight;
    }

    public static double computeSizeOfPiece(Biota biota) {
        double maxWeight = biota.getLimitData().maxWeight();
        double currentWeight = biota.getProperty().getWeight();
        return maxWeight - currentWeight;
    }

    public static String sayMyNameCommunity(Biota biota) {
        return biota.getClass().getSimpleName();
    }

    public static double fetchWeight(Biota biota) {
        return biota.getProperty().getWeight();
    }

    public static void checkLifeStatus(Biota biota) {
        if (!biota.getIsAlive().get()) {
            throw new DomainException(FailMessagesDomain.IS_NOT_LIVE + biota);
        }
    }

    public static boolean isChoiceSuccess(int choice) {
        int pctBound = 100;
        return choice > ThreadLocalRandom.current().nextInt(pctBound);
    }
}
