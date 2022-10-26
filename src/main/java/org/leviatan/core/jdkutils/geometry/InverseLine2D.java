package org.leviatan.core.jdkutils.geometry;

/**
 * InverseLine2D.
 *
 * y = 1 / (m * x + b)
 *
 * @author acc
 *
 */
public class InverseLine2D implements FunctionGeometry2D {

    private final double m;
    private final double b;

    /**
     * Constructor for Line2D.
     *
     * @param m
     *            slope of the line
     * @param b
     *            intersection with x=0
     */
    public InverseLine2D(final double m, final double b) {
        this.m = m;
        this.b = b;
    }

    public double getM() {
        return this.m;
    }

    public double getB() {
        return this.b;
    }

    /**
     * Returns the y value.
     *
     * @param x
     *            x
     * @return the y value
     */
    @Override
    public double getY(final double x) {

        // y = 1 / (m * x + b)
        return 1.0 / (this.m * x + this.b);
    }

    /**
     * Returns the x value.
     *
     * @param y
     *            y
     * @return the x value
     */
    @Override
    public double getX(final double y) {

        // y = 1 / (m * x + b)
        // x = (1/y - b)/m
        return (1 / y - this.b) / this.m;
    }
}
