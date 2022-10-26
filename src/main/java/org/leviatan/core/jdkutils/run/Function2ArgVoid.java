package org.leviatan.core.jdkutils.run;

/**
 * Function2ArgVoid.
 *
 * @author acc
 *
 */
@FunctionalInterface
public interface Function2ArgVoid<A1, A2> {

    /**
     * Function of 2 arguments.
     *
     * @param arg1
     *            arg1
     * @param arg2
     *            arg2
     */
    public void apply(A1 arg1, A2 arg2);
}
