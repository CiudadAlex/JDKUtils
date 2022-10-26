package org.leviatan.core.jdkutils.maps;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * MapObjectToSet.
 *
 * @author acc
 *
 */
public class MapObjectToList<K, V> {

    private final Map<K, List<V>> map = new HashMap<K, List<V>>();

    /**
     * Gets the list by key.
     *
     * @param key
     *            key
     * @return the list by key
     */
    public List<V> get(final K key) {

        final List<V> list = this.map.get(key);

        if (list != null) {
            return list;
        }

        return new ArrayList<V>();
    }

    /**
     * Adds a value.
     *
     * @param key
     *            key
     * @param value
     *            value
     */
    public void add(final K key, final V value) {

        List<V> list = this.map.get(key);

        if (list == null) {
            list = new ArrayList<V>();
            this.map.put(key, list);
        }

        list.add(value);
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
     * Returns the collection of values.
     *
     * @return the collection of values
     */
    public Collection<List<V>> values() {
        return this.map.values();
    }

    /**
     * Returns the reverse map.
     *
     * @return the reverse map
     */
    public MapObjectToList<V, K> reverse() {

        final MapObjectToList<V, K> reversedMap = new MapObjectToList<V, K>();

        for (final Entry<K, List<V>> entry : this.map.entrySet()) {

            for (final V value : entry.getValue()) {
                reversedMap.add(value, entry.getKey());
            }
        }

        return reversedMap;
    }
}
