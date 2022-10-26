package org.leviatan.core.jdkutils.maps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.leviatan.core.jdkutils.maps.beans.KeyDoubleBean;

/**
 * MapObjectToDouble.
 *
 * @author acc
 *
 */
public class MapObjectToDouble<K> {

    private final Map<K, Double> map = new HashMap<K, Double>();

    private final Double initialValue;

    /** Constructor for MapObjectToDouble. */
    public MapObjectToDouble() {
        this(0.0);
    }

    /**
     * Constructor for MapObjectToDouble.
     *
     * @param initialValue
     *            initialValue
     */
    public MapObjectToDouble(final Number initialValue) {
        this.initialValue = initialValue.doubleValue();
    }

    /**
     * Gets the Double by key.
     *
     * @param key
     *            key
     * @return the Set by key
     */
    public Double get(final K key) {

        final Double value = this.map.get(key);

        if (value == null) {
            this.map.put(key, this.initialValue);
            return this.initialValue;
        }

        return value;
    }

    /**
     * Adds a value.
     *
     * @param key
     *            key
     * @param valueToAdd
     *            valueToAdd
     */
    public void addToValue(final K key, final Number valueToAdd) {

        final Double value = get(key);
        this.map.put(key, value + valueToAdd.doubleValue());
    }

    /**
     * Returns if the map is empty.
     *
     * @return if the map is empty
     */
    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    /**
     * Returns the key set.
     *
     * @return key set
     */
    public Set<K> keySet() {
        return this.map.keySet();
    }

    /**
     * Returns the entries ordered.
     *
     * @param asc
     *            asc
     * @return the entries ordered
     */
    public List<KeyDoubleBean<K>> getListOrdered(final boolean asc) {

        final List<KeyDoubleBean<K>> listEntry = new ArrayList<KeyDoubleBean<K>>();

        for (final Entry<K, Double> entry : this.map.entrySet()) {
            listEntry.add(new KeyDoubleBean<K>(entry.getKey(), entry.getValue()));
        }

        Collections.sort(listEntry, new Comparator<KeyDoubleBean<K>>() {

            @Override
            public int compare(final KeyDoubleBean<K> o1, final KeyDoubleBean<K> o2) {

                int comparation = o1.getD().compareTo(o2.getD());

                if (!asc) {
                    comparation = -comparation;
                }

                return comparation;
            }
        });

        return listEntry;
    }
}
