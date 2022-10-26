package org.leviatan.core.jdkutils.time.constants;

import java.util.Calendar;
import java.util.Date;

import org.leviatan.core.jdkutils.time.TimeUtils;

/**
 * SemesterOfYear.
 *
 * @author acc
 *
 */
public enum SemesterOfYear {

    /** S1. */
    S1(Calendar.JANUARY),

    /** S2. */
    S2(Calendar.JULY);

    private final int firstMonth;

    private SemesterOfYear(final int firstMonth) {
        this.firstMonth = firstMonth;
    }

    public int getFirstMonth() {
        return this.firstMonth;
    }

    /**
     * Returns the next SemesterOfYear.
     *
     * @return the next SemesterOfYear
     */
    public SemesterOfYear next() {

        if (S1.equals(this)) {
            return S2;

        } else if (S2.equals(this)) {
            return S1;
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

        final SemesterOfYear nextS = next();
        final Date beginNext = TimeUtils.createDate(year, nextS.firstMonth, 1);

        Date endDate = TimeUtils.addCalendarField(beginNext, Calendar.MILLISECOND, -1);

        if (S1.equals(nextS)) {
            endDate = TimeUtils.addCalendarField(endDate, Calendar.YEAR, 1);
        }

        return endDate;
    }

}
