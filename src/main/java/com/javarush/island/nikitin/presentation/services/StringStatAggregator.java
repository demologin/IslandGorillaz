package com.javarush.island.nikitin.presentation.services;

import com.javarush.island.nikitin.presentation.constants.Messages;
import com.javarush.island.nikitin.presentation.entity.DataStatistic;
import lombok.Getter;

import java.util.Map;

@Getter
public class StringStatAggregator {
    private final StringBuilder stringBuilder;

    public StringStatAggregator() {
        this.stringBuilder = new StringBuilder();
    }

    public String getString() {
        String string = stringBuilder.toString();
        stringBuilder.setLength(0);
        return string;
    }


    public void appendHeader(DataStatistic data) {
        int countAllResidents = data.countAllResidents();
        int currentCalendarDay = data.currentCalendarDay();
        long deathTotalLocation = data.deathTotalLocation();
        stringBuilder.append(Messages.TODAY_IS_THE_DAY)
                .append('\t')
                .append(currentCalendarDay)
                .append('\n');
        stringBuilder.append(Messages.NUMBER_OF_ALL)
                .append('\t')
                .append(countAllResidents)
                .append('\n');
        stringBuilder.append(Messages.NUMBER_OF_DEAD_RESIDENTS)
                .append('\t')
                .append(deathTotalLocation)
                .append('\n');
    }

    public void appendDiagram(DataStatistic data) {
        int countAllResidents = data.countAllResidents();
        Map<String, Integer> map = data.map();
        for (var entrySet : map.entrySet()) {
            String key = entrySet.getKey();
            Integer value = entrySet.getValue();
            extracted(key, value, countAllResidents);
        }
    }

    private void extracted(String nameCommunity, int sizeCommunity, int countAllResidents) {
        double fractionPct = calculatePctFraction(sizeCommunity, countAllResidents);
        int numberOfSymbols = calculateNumberOfSymbolsForDiagram(fractionPct);
        stringBuilder.append('\t')
                .append(String.format("%-11s", nameCommunity))
                .append(String.format("%,9d", sizeCommunity))
                .append(" - ")
                .append(String.format("%4.1f", fractionPct))
                .append("%");
        appendSymbolsIncrease(numberOfSymbols);
        stringBuilder.append('\n');
    }

    private void appendSymbolsIncrease(double numberOfSymbols) {
        int scaleSubgroup = 5;
        for (int i = 0; i < numberOfSymbols; i++) {
            if (i % scaleSubgroup == 0) {
                stringBuilder.append(" ");
            }
            stringBuilder.append(Messages.SYMBOL_INCREASE);
        }
    }

    private double calculatePctFraction(int sizeCommunity, int countAllResidents) {
        double pct = 100.0d;
        return sizeCommunity * pct / countAllResidents;
    }

    private int calculateNumberOfSymbolsForDiagram(double fractionPct) {
        double minFractionPct = 0.02d;
        fractionPct = fractionPct >= minFractionPct
                ? fractionPct
                : 0;
        int relation = 1;
        return (int) (fractionPct / relation);
    }

    public void appendLineSeparator() {
        int countSeparator = 25;
        String singleSeparator = "*";
        stringBuilder.append(singleSeparator.repeat(countSeparator));
        stringBuilder.append(Messages.NAME_GAME);
        stringBuilder.append(singleSeparator.repeat(countSeparator));
        stringBuilder.append('\n');
    }
}
