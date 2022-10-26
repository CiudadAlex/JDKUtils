package org.leviatan.core.jdkutils.geometry;

/**
 * FunctionGeometry2D.
 *
 * @author acc
 *
 */
public interface FunctionGeometry2D {

    /**
     * Returns the y value.
     *
     * @param x
     *            x
     * @return the y value
     */
    public double getY(final double x);

    /**
     * Returns the x value.
     *
     * @param y
     *            y
     * @return the x value
     */
    public double getX(final double y);
}
