package org.leviatan.core.jdkutils.object;

/**
 * BooleanUtils.
 *
 * @author acc
 *
 */
public final class BooleanUtils {

    private BooleanUtils() {
    }

    /**
     * Counts the number of trues.
     *
     * @param arrayBoolean
     *            arrayBoolean
     * @return the number of trues
     */
    public static int countTrues(final Boolean... arrayBoolean) {

        int count = 0;

        for (final Boolean boolItem : arrayBoolean) {

            if (Boolean.TRUE.equals(boolItem)) {
                count++;
            }
        }

        return count;
    }
}
