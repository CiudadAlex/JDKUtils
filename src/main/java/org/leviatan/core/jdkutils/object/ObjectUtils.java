package org.leviatan.core.jdkutils.object;

/**
 * ObjectUtils.
 *
 * @author acc
 *
 */
public final class ObjectUtils {

    private ObjectUtils() {
    }

    /**
     * Returns if the object is equal to any of the given.
     *
     * @param obj
     *            obj
     * @param arrayObj
     *            arrayObj
     * @return if the object is equal to any of the given
     */
    public static boolean isEqualToAny(final Object obj, final Object... arrayObj) {

        for (final Object item : arrayObj) {

            if (obj.equals(item)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Equals null safe.
     *
     * @param obj1
     *            obj1
     * @param obj2
     *            obj2
     * @param twoNullsIsEquals
     *            twoNullsIsEquals
     * @return Equals null safe
     */
    public static boolean equalsNullSafe(final Object obj1, final Object obj2, final boolean twoNullsIsEquals) {

        if (obj1 == null && obj2 == null) {
            return twoNullsIsEquals;
        }

        if (obj1 == null) {
            return false;
        }

        if (obj2 == null) {
            return false;
        }

        return obj1.equals(obj2);
    }

    /**
     * Returns if there is any null.
     *
     * @param arrayObj
     *            arrayObj
     * @return if there is any null
     */
    public static boolean isThereAnyNull(final Object... arrayObj) {

        for (final Object item : arrayObj) {

            if (item == null) {
                return true;
            }
        }

        return false;
    }
}
