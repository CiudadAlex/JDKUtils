package org.leviatan.core.jdkutils.run;

/**
 * Callable.
 *
 * @author acc
 *
 */
@FunctionalInterface
public interface Callable<V> {

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception
     *             if unable to compute a result
     */
    public V call();
}
