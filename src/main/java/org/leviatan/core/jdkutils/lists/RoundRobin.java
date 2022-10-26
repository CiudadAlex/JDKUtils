package org.leviatan.core.jdkutils.lists;

import java.util.List;

/**
 * RoundRobin.
 *
 * @author acc
 *
 */
public class RoundRobin<T> {

    private final List<T> list;
    private int index;
    private int countCalls;

    /**
     * Constructor for RoundRobin.
     *
     * @param list
     *            list
     */
    public RoundRobin(final List<T> list) {
        this.list = list;
    }

    /**
     * Returns in a round-robin fashion.
     *
     * @return the next object.
     */
    public T get() {

        final T obj = this.list.get(this.index);

        this.index = (this.index + 1) % this.list.size();

        this.countCalls++;

        return obj;
    }

    public int getCountCalls() {
        return this.countCalls;
    }

}
