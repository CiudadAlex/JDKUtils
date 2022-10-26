package org.leviatan.core.jdkutils.geometry;

/**
 * Exponential2D.
 *
 * y = a * exp(z*x)
 *
 * @author acc
 *
 */
public class Exponential2D implements FunctionGeometry2D {

    private final double a;
    private final double z;

    /**
     * Constructor for Exponential2D.
     *
     * y = a * exp(z*x)
     *
     * @param a
     *            factor of the exponential
     * @param z
     *            factor of the exponent
     */
    public Exponential2D(final double a, final double z) {
        this.a = a;
        this.z = z;
    }

    public double getA() {
        return this.a;
    }

    public double getZ() {
        return this.z;
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

        // y = a * exp(z*x)
        return this.a * Math.exp(this.z * x);
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

        // y = a * exp(z*x)
        // x = [ln(y) - ln(a)] / z
        return (Math.log(y) - Math.log(this.a)) / this.z;
    }

}
