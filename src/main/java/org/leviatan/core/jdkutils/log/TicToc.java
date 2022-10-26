package org.leviatan.core.jdkutils.log;

import java.util.Date;

import org.leviatan.core.jdkutils.time.TimeUtils;

/**
 * TicToc.
 *
 * @author acc
 *
 */
public class TicToc {

    private static final String DEFAULT_MESSAGE = "Time since last TIC";

    private Date date;

    /** Constructor for TicToc. */
    public TicToc() {
        tic();
    }

    /** Performs a tic. */
    public void tic() {
        this.date = new Date();
    }

    /** Performs a toc. */
    public void toc() {
        toc(DEFAULT_MESSAGE);
    }

    /**
     * Performs a toc.
     *
     * @param txt
     *            txt
     */
    public void toc(final String txt) {

        final Date now = new Date();
        final String timeSinceLastTic = TimeUtils.toStringTimeLapse(this.date, now);
        AppLogger.logDebug(txt + ": " + timeSinceLastTic);
    }

    /** Performs a toc. */
    public void toctic() {
        toctic(DEFAULT_MESSAGE);
    }

    /**
     * Performs a toc.
     *
     * @param txt
     *            txt
     */
    public void toctic(final String txt) {
        toc(txt);
        tic();
    }

}
