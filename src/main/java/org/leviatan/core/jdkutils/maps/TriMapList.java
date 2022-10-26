package org.leviatan.core.jdkutils.maps;

import java.util.ArrayList;
import java.util.List;

/**
 * TriMapList.
 *
 * @author acc
 *
 */
public class TriMapList<K, KO, V> extends TriMap<K, KO, List<V>> {

    private static final long serialVersionUID = 368846281504457356L;

    /**
     * Adds a value to the list.
     *
     * @param key
     *            key
     * @param keyObject
     *            keyObject
     * @param value
     *            value
     */
    public void add(final K key, final KO keyObject, final V value) {

        TriMapEntry<KO, List<V>> entry = get(key);

        if (entry == null) {

            entry = new TriMapEntry<KO, List<V>>(keyObject, new ArrayList<V>());
            put(key, entry);
        }

        entry.getValue().add(value);
    }
}
