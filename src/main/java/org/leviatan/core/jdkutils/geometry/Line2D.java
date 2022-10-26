package org.leviatan.core.jdkutils.geometry;

/**
 * Line2D.
 *
 * y = mx + b
 *
 * @author acc
 *
 */
public class Line2D implements FunctionGeometry2D {

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
    public Line2D(final double m, final double b) {
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

        // y = mx + b
        return this.m * x + this.b;
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

        // y = mx + b
        // x = (y - b)/m
        return (y - this.b) / this.m;
    }

    /**
     * Return a new Line2D multiplying the M.
     *
     * @param factor
     *            factor
     * @return a new Line2D multiplying the M
     */
    public Line2D getLine2DMultiplyM(final double factor) {
        return new Line2D(factor * this.m, this.b);
    }

    /**
     * Builds a Line2D from 2 points.
     *
     * @param x1
     *            x1
     * @param y1
     *            y1
     * @param x2
     *            x2
     * @param y2
     *            y2
     * @return a Line2D
     */
    public static Line2D buildLine2DFrom2Points(final double x1, final double y1, final double x2, final double y2) {

        // m = (y2-y1) / (x2-x1)
        final double m = (y2 - y1) / (x2 - x1);

        // y-y1 = m * (x-x1)
        // y = m*x - m*x1 + y1
        final double b = -m * x1 + y1;

        return new Line2D(m, b);
    }

}
