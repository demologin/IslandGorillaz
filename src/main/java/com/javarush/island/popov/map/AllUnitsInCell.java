package com.javarush.island.popov.map;

import com.javarush.island.popov.units.Unit;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class  AllUnitsInCell {

    private final Map<Class<? extends Unit>, LinkedHashSet<Unit>> allUnitsInCell = new LinkedHashMap<>();

    public void addUnit(Unit unit){
        Class<? extends Unit> clazz = unit.getClass();
        if(contains(unit)){
            Set<Unit> units = allUnitsInCell.get(clazz);
            units.add(unit);
        }else {
            Set<Unit> units = new LinkedHashSet<>();
            units.add(unit);
            allUnitsInCell.put(clazz, (LinkedHashSet<Unit>) units);
        }

    }
    public void removeUnit(Unit unit){
        Class<? extends Unit> clazz = unit.getClass();
        if (contains(unit)){
            Set<Unit> units = allUnitsInCell.get(clazz);
            units.remove(unit);
        }
    }
    public void removeOneTypeUnits(Unit unit){
        allUnitsInCell.remove(unit.getClass());
    }

    public boolean contains(Unit unit){
        Class<?extends Unit> clazz = unit.getClass();
        if (allUnitsInCell.containsKey(clazz)){
            Set<Unit> units = allUnitsInCell.get(clazz);
            for (Unit unitInCell :units){
                if (unitInCell.equals(unit)){
                    return true;
                }
            }
        }
        return false;
    }
   public Set<Unit> getUnitsList(Unit unit){
       LinkedHashSet<Unit> unitsList = allUnitsInCell.get(unit.getClass());
            return unitsList;
   }

public Set<Unit> getListOneTypeUnits(Unit unit){
    if (contains(unit)){
        LinkedHashSet<Unit> unitsList = allUnitsInCell.get(unit.getClass());
        return unitsList;
    }else {
        LinkedHashSet<Unit> units = new LinkedHashSet<>();
        allUnitsInCell.put(unit.getClass(),units);
        return units;
    }

}
    public Set<Map.Entry<Class<? extends Unit>, LinkedHashSet<Unit>>> getEntrySet(){
       return allUnitsInCell.entrySet();
    }
    public int getSize(){
        return allUnitsInCell.size();
    }
    @Override
    public String toString() {
        return "AllUnitsInCell{" +
                "allUnitsInCell=" + allUnitsInCell +
                '}';
    }
}
