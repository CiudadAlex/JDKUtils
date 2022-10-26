package org.leviatan.core.jdkutils.time;

import java.util.Date;

import org.leviatan.core.jdkutils.time.constants.QuarterOfYear;

/**
 * QuarterInstance.
 *
 * @author acc
 *
 */
public class QuarterInstance {

    private final QuarterOfYear quarterOfYear;
    private final int year;

    /**
     * Constructor for QuarterInstance.
     *
     * @param quarterOfYear
     *            quarterOfYear
     * @param year
     *            year
     */
    public QuarterInstance(final QuarterOfYear quarterOfYear, final int year) {
        this.quarterOfYear = quarterOfYear;
        this.year = year;
    }

    public QuarterOfYear getQuarterOfYear() {
        return this.quarterOfYear;
    }

    public int getYear() {
        return this.year;
    }

    public SemesterInstance getSemesterInstance() {
        return new SemesterInstance(this.quarterOfYear.getSemesterOfYear(), this.year);
    }

    /**
     * Returns the begin date.
     *
     * @return the begin date
     */
    public Date getBeginDate() {
        return this.quarterOfYear.getBeginDate(this.year);
    }

    /**
     * Returns the end date.
     *
     * @return the end date
     */
    public Date getEndDate() {
        return this.quarterOfYear.getEndDate(this.year);
    }

    /**
     * Returns the next QuarterInstance.
     *
     * @return the next QuarterInstance
     */
    public QuarterInstance next() {

        final QuarterOfYear nextQ = this.quarterOfYear.next();
        int yearOfNextQuarter = this.year;

        if (QuarterOfYear.Q1.equals(nextQ)) {
            yearOfNextQuarter++;
        }

        return new QuarterInstance(nextQ, yearOfNextQuarter);
    }

    @Override
    public String toString() {
        return this.quarterOfYear.name() + "-" + this.year;
    }

}
