package org.leviatan.core.jdkutils.run;

/**
 * FunctionVoid.
 *
 * @author acc
 *
 */
@FunctionalInterface
public interface FunctionVoid<T> {

    /**
     * Performs this operation on the given argument.
     *
     * @param t
     *            the input argument
     */
    public void apply(T t);

}
