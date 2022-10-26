package org.leviatan.core.jdkutils.maps;

/**
 * TriMapEntry.
 *
 * @author acc
 *
 */
public class TriMapEntry<KO, V> {

    private final KO keyObject;
    private final V value;

    /**
     * Constructor for TriMapEntry.
     *
     * @param keyObject
     *            keyObject
     * @param value
     *            value
     */
    public TriMapEntry(final KO keyObject, final V value) {
        this.keyObject = keyObject;
        this.value = value;
    }

    public KO getKeyObject() {
        return this.keyObject;
    }

    public V getValue() {
        return this.value;
    }

}
