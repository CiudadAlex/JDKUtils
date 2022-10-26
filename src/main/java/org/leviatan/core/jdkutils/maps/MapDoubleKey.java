package org.leviatan.core.jdkutils.maps;

import java.util.HashMap;
import java.util.Map;

/**
 * MapDoubleKey.
 *
 * @author acc
 *
 */
public class MapDoubleKey<KA extends Number, KB extends Number, V> {

    private static final String SEPARATOR = "|";

    private final Map<String, V> map = new HashMap<String, V>();

    /**
     * Gets the value by the two keys.
     *
     * @param keyA
     *            keyA
     * @param keyB
     *            keyB
     * @return the value by the two keys
     */
    public V get(final KA keyA, final KB keyB) {

        final String key = buildUniqueKey(keyA, keyB);
        final V value = this.map.get(key);
        return value;
    }

    private String buildUniqueKey(final KA keyA, final KB keyB) {
        return keyA.toString() + SEPARATOR + keyB.toString();
    }

    /**
     * Puts a value.
     *
     * @param keyA
     *            keyA
     * @param keyB
     *            keyB
     * @param value
     *            value
     */
    public void put(final KA keyA, final KB keyB, final V value) {

        final String key = buildUniqueKey(keyA, keyB);
        this.map.put(key, value);
    }

    /**
     * Returns if the map is empty.
     *
     * @return if the map is empty
     */
    public boolean isEmpty() {
        return this.map.isEmpty();
    }

}
