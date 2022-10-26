package org.leviatan.core.jdkutils.maps;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * MapObjectToSet.
 *
 * @author acc
 *
 */
public class MapObjectToSet<K, V> {

    private final Map<K, Set<V>> map = new HashMap<K, Set<V>>();

    /**
     * Gets the Set by key.
     *
     * @param key
     *            key
     * @return the Set by key
     */
    public Set<V> get(final K key) {

        final Set<V> list = this.map.get(key);

        if (list != null) {
            return list;
        }

        return new HashSet<V>();
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

        Set<V> list = this.map.get(key);

        if (list == null) {
            list = new HashSet<V>();
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
     * Returns the reverse map.
     *
     * @return the reverse map
     */
    public MapObjectToSet<V, K> reverse() {

        final MapObjectToSet<V, K> reversedMap = new MapObjectToSet<V, K>();

        for (final Entry<K, Set<V>> entry : this.map.entrySet()) {

            for (final V value : entry.getValue()) {
                reversedMap.add(value, entry.getKey());
            }
        }

        return reversedMap;
    }
}
