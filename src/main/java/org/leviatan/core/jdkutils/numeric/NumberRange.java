package org.leviatan.core.jdkutils.numeric;

import java.util.List;

import org.leviatan.core.jdkutils.text.TextUtils;

/**
 * NumberRange.
 *
 * @author acc
 *
 */
public class NumberRange<T extends Number> {

    private static final String RANGE_SEPARATOR = "-";

    private final T min;
    private final boolean includedMin;

    private final T max;
    private final boolean includedMax;

    /**
     * Constructor for NumberRange.
     *
     * @param min
     *            min
     * @param max
     *            max
     */
    public NumberRange(final T min, final T max) {
        this(min, max, true, true);
    }

    /**
     * Constructor for NumberRange.
     *
     * @param min
     *            min
     * @param max
     *            max
     * @param includedMin
     *            includedMin
     * @param includedMax
     *            includedMax
     */
    public NumberRange(final T min, final T max, final boolean includedMin, final boolean includedMax) {
        this.min = min;
        this.max = max;
        this.includedMin = includedMin;
        this.includedMax = includedMax;
    }

    public T getMin() {
        return this.min;
    }

    public T getMax() {
        return this.max;
    }

    /**
     * Returns if the number is in the range.
     *
     * @param value
     *            value
     * @return if the number is in the range
     */
    public boolean isNumberInRange(final T value) {

        final boolean okWithMin = isNumberOkWithMin(value);
        final boolean okWithMax = isNumberOkWithMax(value);

        return okWithMin && okWithMax;
    }

    private boolean isNumberOkWithMin(final T value) {

        boolean okWithMin = true;

        if (this.min != null) {

            if (this.includedMin) {
                okWithMin = value.doubleValue() >= this.min.doubleValue();
            } else {
                okWithMin = value.doubleValue() > this.min.doubleValue();
            }
        }

        return okWithMin;
    }

    private boolean isNumberOkWithMax(final T value) {

        boolean okWithMax = true;

        if (this.max != null) {

            if (this.includedMax) {
                okWithMax = value.doubleValue() <= this.max.doubleValue();
            } else {
                okWithMax = value.doubleValue() < this.max.doubleValue();
            }
        }

        return okWithMax;
    }

    /**
     * Returns if the argument range is contained in this range.
     *
     * @param range
     *            range
     * @return if the argument range is contained in this range
     */
    public boolean isRangeContained(final NumberRange<T> range) {

        final boolean isRangeMinContained = isRangeMinContained(range);
        final boolean isRangeMaxContained = isRangeMaxContained(range);

        return isRangeMinContained && isRangeMaxContained;
    }

    private boolean isRangeMinContained(final NumberRange<T> range) {

        final Double minRange = range.min != null ? range.min.doubleValue() : null;
        final Double minThis = this.min != null ? this.min.doubleValue() : null;

        if (minThis == null) {
            return true;
        }

        if (minRange == null) {
            return false;
        }

        if (this.includedMin) {
            return minRange >= minThis;

        } else {

            if (range.includedMin) {
                return minRange > minThis;
            } else {
                return minRange >= minThis;
            }
        }
    }

    private boolean isRangeMaxContained(final NumberRange<T> range) {

        final Double maxRange = range.max != null ? range.max.doubleValue() : null;
        final Double maxThis = this.max != null ? this.max.doubleValue() : null;

        if (maxThis == null) {
            return true;
        }

        if (maxRange == null) {
            return false;
        }

        if (this.includedMax) {
            return maxRange <= maxThis;

        } else {

            if (range.includedMax) {
                return maxRange < maxThis;
            } else {
                return maxRange <= maxThis;
            }
        }
    }

    public boolean isIncludedMin() {
        return this.includedMin;
    }

    public boolean isIncludedMax() {
        return this.includedMax;
    }

    @Override
    public String toString() {

        final String beforeSymbol = this.includedMin ? "[" : "]";
        final String afterSymbol = this.includedMax ? "]" : "[";

        final String equalsMin = this.includedMin ? "=" : "";
        final String equalsMax = this.includedMax ? "=" : "";

        if (this.min != null && this.max != null) {
            return beforeSymbol + this.min + RANGE_SEPARATOR + this.max + afterSymbol;

        } else if (this.min == null) {
            return "<" + equalsMax + " " + this.max;

        } else if (this.max == null) {
            return ">" + equalsMin + " " + this.min;
        }

        return "";
    }

    /**
     * Retuns a String codification.
     *
     * @return a String codification
     */
    public String getStringCodification() {

        final String beforeSymbol = this.includedMin ? "[" : "]";
        final String afterSymbol = this.includedMax ? "]" : "[";

        if (this.min != null && this.max != null) {
            return beforeSymbol + this.min + RANGE_SEPARATOR + this.max + afterSymbol;

        } else if (this.min == null) {
            return RANGE_SEPARATOR + this.max + afterSymbol;

        } else if (this.max == null) {
            return beforeSymbol + this.min + RANGE_SEPARATOR;
        }

        return RANGE_SEPARATOR;
    }

    /**
     * Creates a NumberRange from an String cidification.
     *
     * @param codification
     *            codification
     * @return a NumberRange
     */
    public static NumberRange<Double> fromStringCodification(final String codification) {

        final List<String> listTokens = TextUtils.divideString(codification, RANGE_SEPARATOR);

        String strNumberMin = listTokens.get(0);
        boolean includedMin = true;

        if (strNumberMin.contains("]")) {
            includedMin = false;
        }

        strNumberMin = strNumberMin.replace("[", "");
        strNumberMin = strNumberMin.replace("]", "");

        String strNumberMax = listTokens.get(1);
        boolean includedMax = true;

        if (strNumberMax.contains("[")) {
            includedMax = false;
        }

        strNumberMax = strNumberMax.replace("[", "");
        strNumberMax = strNumberMax.replace("]", "");

        final Double min = TextUtils.isBlank(strNumberMin) ? null : Double.parseDouble(strNumberMin);
        final Double max = TextUtils.isBlank(strNumberMax) ? null : Double.parseDouble(strNumberMax);

        return new NumberRange<Double>(min, max, includedMin, includedMax);
    }

}
