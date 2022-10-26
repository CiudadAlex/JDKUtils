package org.leviatan.core.jdkutils.maps;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * MapMap.
 *
 * @author acc
 *
 */
public class MapMap<KA, KB, V> {

    private final Map<KA, Map<KB, V>> map = new HashMap<KA, Map<KB, V>>();

    /**
     * Gets the Set by key.
     *
     * @param keyA
     *            keyA
     * @return the Set by key
     */
    public Map<KB, V> get(final KA keyA) {

        final Map<KB, V> mapB = this.map.get(keyA);

        if (mapB != null) {
            return mapB;
        }

        return new HashMap<KB, V>();
    }

    /**
     * Adds a value.
     *
     * @param keyA
     *            keyA
     * @param keyB
     *            keyB
     * @param value
     *            value
     */
    public void add(final KA keyA, final KB keyB, final V value) {

        Map<KB, V> mapB = this.map.get(keyA);

        if (mapB == null) {
            mapB = new HashMap<KB, V>();
            this.map.put(keyA, mapB);
        }

        mapB.put(keyB, value);
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
    public Set<KA> keySet() {
        return this.map.keySet();
    }
}
