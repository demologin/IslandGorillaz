package com.javarush.island.stepanov.entity.map;

import java.util.*;

public class SortedByValueTreeMap<K, V extends Comparable<V>> {
    private final Map<K, V> map; // Основная карта для хранения данных
    private final TreeMap<K, V> sortedMap; // Отсортированная карта

    public SortedByValueTreeMap() {
        this.map = new HashMap<>();
        this.sortedMap = new TreeMap<>(new ValueComparator(map));
    }

    // Внутренний компаратор для сортировки по значениям
    private class ValueComparator implements Comparator<K> {
        private final Map<K, V> map;

        public ValueComparator(Map<K, V> map) {
            this.map = map;
        }

        @Override
        public int compare(K key1, K key2) {
            int compareResult = map.get(key2).compareTo(map.get(key1));
            // Если значения равны, сравниваем по ключам, чтобы избежать потери данных
            return compareResult == 0 ? 1 : compareResult;
        }
    }

    // Добавление элемента
    public void put(K key, V value) {
        map.put(key, value);
        sortedMap.put(key, value);
    }

    // Получение значения по ключу
    public V get(K key) {
        return map.get(key);
    }

    // Удаление элемента по ключу
    public void remove(K key) {
        map.remove(key);
        sortedMap.remove(key);
    }

    // Получение всех элементов в отсортированном порядке
    public Set<Map.Entry<K, V>> entrySet() {
        return sortedMap.entrySet();
    }

    // Получение размера карты
    public int size() {
        return map.size();
    }

    // Проверка на пустоту
    public boolean isEmpty() {
        return map.isEmpty();
    }

    // Очистка карты
    public void clear() {
        map.clear();
        sortedMap.clear();
    }
}