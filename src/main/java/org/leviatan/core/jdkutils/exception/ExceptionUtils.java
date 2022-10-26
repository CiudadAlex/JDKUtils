package org.leviatan.core.jdkutils.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * ExceptionUtils.
 *
 * @author acc
 *
 */
public final class ExceptionUtils {

    private ExceptionUtils() {
    }

    /**
     * Generates an String of the StackTrace.
     *
     * @param e
     *            Exception
     * @return an String of the StackTrace
     */
    public static String toString(final Exception e) {

        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);

        final String strStackTrace = sw.toString();

        return strStackTrace;
    }
}
