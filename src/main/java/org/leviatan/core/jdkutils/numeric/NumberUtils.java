package org.leviatan.core.jdkutils.numeric;

import java.util.List;
import java.util.function.Function;

import org.leviatan.core.jdkutils.log.AppLogger;

/**
 * NumberUtils.
 *
 * @author acc
 *
 */
public final class NumberUtils {

    private NumberUtils() {
    }

    /**
     * Returns the Max Number contained in the objects.
     *
     * @param <N>
     *            number type
     * @param <T>
     *            object type
     * @param listObjects
     *            listObjects
     * @param extractNumber
     *            extractNumber
     * @return the Max Number contained in the objects
     */
    public static <N extends Number, T> N getMax(final List<T> listObjects, final Function<T, N> extractNumber) {

        if (listObjects.isEmpty()) {
            return null;
        }

        N numberMax = null;
        Double doubleMax = Double.NEGATIVE_INFINITY;

        for (final T obj : listObjects) {

            final N num = extractNumber.apply(obj);

            if (num.doubleValue() > doubleMax) {
                numberMax = num;
                doubleMax = num.doubleValue();
            }
        }

        return numberMax;
    }

    /**
     * Returns the Min Number contained in the objects.
     *
     * @param <N>
     *            number type
     * @param <T>
     *            object type
     * @param listObjects
     *            listObjects
     * @param extractNumber
     *            extractNumber
     * @return the Min Number contained in the objects
     */
    public static <N extends Number, T> N getMin(final List<T> listObjects, final Function<T, N> extractNumber) {

        if (listObjects.isEmpty()) {
            return null;
        }

        N numberMin = null;
        Double doubleMin = Double.POSITIVE_INFINITY;

        for (final T obj : listObjects) {

            final N num = extractNumber.apply(obj);

            if (num.doubleValue() < doubleMin) {

                numberMin = num;
                doubleMin = num.doubleValue();
            }
        }

        return numberMin;
    }

    /**
     * Calculates the mean value from the objects.
     *
     * @param <T>
     *            object type
     * @param listData
     *            listData
     * @param extractor
     *            extractor
     * @return the mean value from the objects
     */
    public static <T> double calculateMeanValue(final List<T> listData, final Function<T, Double> extractor) {

        double sum = 0;

        for (final T data : listData) {
            sum = sum + extractor.apply(data);
        }

        return sum / listData.size();
    }

    /**
     * Calculates the weighted mean value from the objects.
     *
     * @param <T>
     *            object type
     * @param listData
     *            listData
     * @param extractorValue
     *            extractorValue
     * @param extractorWeight
     *            extractorWeight
     * @return the the weighted mean value from the objects
     */
    public static <T> double calculateWeightedMeanValue(final List<T> listData, final Function<T, Double> extractorValue,
            final Function<T, Double> extractorWeight) {

        double sumValuesWeighted = 0;
        double sumWeights = 0;

        for (final T data : listData) {

            final double value = extractorValue.apply(data);
            final double weight = extractorWeight.apply(data);

            sumValuesWeighted = sumValuesWeighted + weight * value;
            sumWeights = sumWeights + weight;
        }

        return sumValuesWeighted / sumWeights;
    }

    /**
     * Returns the decimal representation with the given number of decimals.
     *
     * @param f
     *            float
     * @param numberDecimals
     *            numberDecimals
     * @return the decimal representation with the given number of decimals
     */
    public static String getDecimalRepresentation(final float f, final int numberDecimals) {

        float factor = 1;

        for (int i = 0; i < numberDecimals; i++) {
            factor = factor * 10;
        }

        final float fUppedRound = Math.round(f * factor);

        return "" + fUppedRound / factor;
    }

    /**
     * Parses the integer and returns it. If any problem returns the default.
     *
     * @param strInteger
     *            strInteger
     * @param defaultInteger
     *            defaultInteger
     * @return the integer
     */
    public static Integer parseInteger(final String strInteger, final Integer defaultInteger) {

        try {
            return Integer.parseInt(strInteger);

        } catch (final Exception e) {
            AppLogger.logError("Unparseable int: " + strInteger, e);
        }

        return defaultInteger;
    }

    /**
     * Parses the integer and returns it. If any problem returns null.
     *
     * @param txt
     *            txt
     * @return the integer
     */
    public static Integer parseInteger(final String txt) {

        try {
            return Integer.parseInt(txt);

        } catch (final Exception e) {
            // Nothing to do
        }

        return null;
    }

    /**
     * Parses the Float and returns it. If any problem returns null.
     *
     * @param txt
     *            txt
     * @return the Float
     */
    public static Float parseFloat(final String txt) {

        try {
            return Float.parseFloat(txt);

        } catch (final Exception e) {
            // Nothing to do
        }

        return null;
    }

    /**
     * Parses the Long and returns it. If any problem returns null.
     *
     * @param txt
     *            txt
     * @return the Long
     */
    public static Long parseLong(final String txt) {

        try {
            return Long.parseLong(txt);

        } catch (final Exception e) {
            // Nothing to do
        }

        return null;
    }

    /**
     * Parses the Double and returns it. If any problem returns null.
     *
     * @param txt
     *            txt
     * @return the Double
     */
    public static Double parseDouble(final String txt) {

        try {
            return Double.parseDouble(txt);

        } catch (final Exception e) {
            // Nothing to do
        }

        return null;
    }

    /**
     * Returns the sum of the number contained in the objects.
     *
     * @param <N>
     *            number type
     * @param <T>
     *            object type
     * @param listObjects
     *            listObjects
     * @param extractNumber
     *            extractNumber
     * @return the sum of the number contained in the objects
     */
    public static <N extends Number, T> Double getSum(final List<T> listObjects, final Function<T, N> extractNumber) {

        double sum = 0.0;

        for (final T obj : listObjects) {

            final N num = extractNumber.apply(obj);
            sum = sum + num.doubleValue();
        }

        return sum;
    }

    /**
     * Returns the object with the closest value.
     *
     * @param <N>
     *            number type
     * @param <T>
     *            object type
     * @param listObjects
     *            listObjects
     * @param extractNumber
     *            extractNumber
     * @param numberTarget
     *            numberTarget
     * @return the object with the closest value
     */
    public static <N extends Number, T> T getObjectWithClosestValue(final List<T> listObjects, final Function<T, N> extractNumber,
            final N numberTarget) {

        if (listObjects.isEmpty()) {
            return null;
        }

        final double doubleTarget = numberTarget.doubleValue();

        T closestObj = null;
        double minCloseness = Double.POSITIVE_INFINITY;

        for (final T obj : listObjects) {

            final double doubleItem = extractNumber.apply(obj).doubleValue();
            final double closeness = Math.abs(doubleTarget - doubleItem);

            if (closeness < minCloseness) {

                minCloseness = closeness;
                closestObj = obj;
            }
        }

        return closestObj;
    }
}
