package com.javarush.island.stepanov.entity.map;

import java.util.*;

public class SortedByValueTreeMap<K, V extends Comparable<V>> {
    private final Map<K, V> map;
    private final TreeMap<K, V> sortedMap;

    public SortedByValueTreeMap() {
        this.map = new HashMap<>();
        this.sortedMap = new TreeMap<>(new ValueComparator(map));
    }

    public void put(K key, V value) {
        map.put(key, value);
        sortedMap.put(key, value);
    }

    public V get(K key) {
        return map.get(key);
    }

    public void remove(K key) {
        map.remove(key);
        sortedMap.remove(key);
    }

    public Set<Map.Entry<K, V>> entrySet() {
        return sortedMap.entrySet();
    }

    public int size() {
        return map.size();
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public void clear() {
        map.clear();
        sortedMap.clear();
    }

    private class ValueComparator implements Comparator<K> {
        private final Map<K, V> map;

        public ValueComparator(Map<K, V> map) {
            this.map = map;
        }

        @Override
        public int compare(K key1, K key2) {
            int compareResult = map.get(key2).compareTo(map.get(key1));
            return compareResult == 0 ? 1 : compareResult;
        }
    }
}