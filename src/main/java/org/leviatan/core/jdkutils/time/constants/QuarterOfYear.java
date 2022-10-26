package org.leviatan.core.jdkutils.time.constants;

import java.util.Calendar;
import java.util.Date;

import org.leviatan.core.jdkutils.time.TimeUtils;

/**
 * QuarterOfYear.
 *
 * @author acc
 *
 */
public enum QuarterOfYear {

    /** Q1. */
    Q1(Calendar.JANUARY, SemesterOfYear.S1),

    /** Q2. */
    Q2(Calendar.APRIL, SemesterOfYear.S1),

    /** Q3. */
    Q3(Calendar.JULY, SemesterOfYear.S2),

    /** Q4. */
    Q4(Calendar.OCTOBER, SemesterOfYear.S2);

    private final int firstMonth;
    private final SemesterOfYear semesterOfYear;

    private QuarterOfYear(final int firstMonth, final SemesterOfYear semesterOfYear) {
        this.firstMonth = firstMonth;
        this.semesterOfYear = semesterOfYear;
    }

    public int getFirstMonth() {
        return this.firstMonth;
    }

    public SemesterOfYear getSemesterOfYear() {
        return this.semesterOfYear;
    }

    /**
     * Returns the next QuarterOfYear.
     *
     * @return the next QuarterOfYear
     */
    public QuarterOfYear next() {

        if (Q1.equals(this)) {
            return Q2;

        } else if (Q2.equals(this)) {
            return Q3;

        } else if (Q3.equals(this)) {
            return Q4;

        } else if (Q4.equals(this)) {
            return Q1;
        }

        return null;
    }

    /**
     * Returns the begin date.
     *
     * @param year
     *            year
     * @return the begin date
     */
    public Date getBeginDate(final int year) {
        return TimeUtils.createDate(year, this.firstMonth, 1);
    }

    /**
     * Returns the end date.
     *
     * @param year
     *            year
     * @return the end date
     */
    public Date getEndDate(final int year) {

        final QuarterOfYear nextQ = next();
        final Date beginNext = TimeUtils.createDate(year, nextQ.firstMonth, 1);

        Date endDate = TimeUtils.addCalendarField(beginNext, Calendar.MILLISECOND, -1);

        if (Q1.equals(nextQ)) {
            endDate = TimeUtils.addCalendarField(endDate, Calendar.YEAR, 1);
        }

        return endDate;
    }

}
