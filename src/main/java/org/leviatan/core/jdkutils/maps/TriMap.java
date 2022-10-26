package org.leviatan.core.jdkutils.maps;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * TriMap.
 *
 * @author acc
 *
 */
public class TriMap<K, KO, V> extends HashMap<K, TriMapEntry<KO, V>> {

    private static final long serialVersionUID = 368846281504457356L;

    /**
     * Puts an entry.
     *
     * @param key
     *            key
     * @param keyObject
     *            keyObject
     * @param value
     *            value
     */
    public void put(final K key, final KO keyObject, final V value) {
        put(key, new TriMapEntry<KO, V>(keyObject, value));
    }

    /**
     * Returns the list of key objects.
     *
     * @return the list of key objects
     */
    public List<KO> getListKeyObjects() {

        final List<KO> listKeyObjects = new ArrayList<KO>();

        final Collection<TriMapEntry<KO, V>> collectionEntries = values();

        for (final TriMapEntry<KO, V> entryValue : collectionEntries) {
            listKeyObjects.add(entryValue.getKeyObject());
        }

        return listKeyObjects;
    }

    /**
     * Returns the list of values objects.
     *
     * @return the list of values objects
     */
    public List<V> getListValues() {

        final List<V> listValues = new ArrayList<V>();

        final Collection<TriMapEntry<KO, V>> collectionEntries = values();

        for (final TriMapEntry<KO, V> entryValue : collectionEntries) {
            listValues.add(entryValue.getValue());
        }

        return listValues;
    }
}
