package org.leviatan.core.jdkutils.object;

/**
 * Holder.
 *
 * @author acc
 *
 */
public class Holder<T> {

    private T obj;

    /** Constructor for Holder. */
    public Holder() {
    }

    /**
     * Constructor for Holder.
     *
     * @param obj
     *            obj
     */
    public Holder(final T obj) {
        this.obj = obj;
    }

    public T getObj() {
        return this.obj;
    }

    public void setObj(final T obj) {
        this.obj = obj;
    }
}
