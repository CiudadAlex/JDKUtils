package org.leviatan.core.jdkutils.time.constants;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.leviatan.core.jdkutils.time.TimeUtils;

/**
 * MonthOfYear.
 *
 * @author acc
 *
 */
public enum MonthOfYear {

    /** JANUARY. */
    JANUARY(Calendar.JANUARY, QuarterOfYear.Q1),

    /** FEBRUARY. */
    FEBRUARY(Calendar.FEBRUARY, QuarterOfYear.Q1),

    /** MARCH. */
    MARCH(Calendar.MARCH, QuarterOfYear.Q1),

    /** APRIL. */
    APRIL(Calendar.APRIL, QuarterOfYear.Q2),

    /** MAY. */
    MAY(Calendar.MAY, QuarterOfYear.Q2),

    /** JUNE. */
    JUNE(Calendar.JUNE, QuarterOfYear.Q2),

    /** JULY. */
    JULY(Calendar.JULY, QuarterOfYear.Q3),

    /** AUGUST. */
    AUGUST(Calendar.AUGUST, QuarterOfYear.Q3),

    /** SEPTEMBER. */
    SEPTEMBER(Calendar.SEPTEMBER, QuarterOfYear.Q3),

    /** OCTOBER. */
    OCTOBER(Calendar.OCTOBER, QuarterOfYear.Q4),

    /** NOVEMBER. */
    NOVEMBER(Calendar.NOVEMBER, QuarterOfYear.Q4),

    /** DECEMBER. */
    DECEMBER(Calendar.DECEMBER, QuarterOfYear.Q4);

    private final int month;
    private final QuarterOfYear quarterOfYear;

    private MonthOfYear(final int month, final QuarterOfYear quarterOfYear) {
        this.month = month;
        this.quarterOfYear = quarterOfYear;
    }

    public int getMonth() {
        return this.month;
    }

    public QuarterOfYear getQuarterOfYear() {
        return this.quarterOfYear;
    }

    /**
     * Returns the next MonthOfYear.
     *
     * @return the next MonthOfYear
     */
    public MonthOfYear next() {

        final MonthOfYear nextM = nextSemester1();

        if (nextM != null) {
            return nextM;
        }

        return nextSemester2();
    }

    private MonthOfYear nextSemester1() {

        if (JANUARY.equals(this)) {
            return FEBRUARY;

        } else if (FEBRUARY.equals(this)) {
            return MARCH;

        } else if (MARCH.equals(this)) {
            return APRIL;

        } else if (APRIL.equals(this)) {
            return MAY;

        } else if (MAY.equals(this)) {
            return JUNE;

        } else if (JUNE.equals(this)) {
            return JULY;
        }

        return null;
    }

    private MonthOfYear nextSemester2() {

        if (JULY.equals(this)) {
            return AUGUST;

        } else if (AUGUST.equals(this)) {
            return SEPTEMBER;

        } else if (SEPTEMBER.equals(this)) {
            return OCTOBER;

        } else if (OCTOBER.equals(this)) {
            return NOVEMBER;

        } else if (NOVEMBER.equals(this)) {
            return DECEMBER;

        } else if (DECEMBER.equals(this)) {
            return JANUARY;
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
        return TimeUtils.createDate(year, this.month, 1);
    }

    /**
     * Returns the end date.
     *
     * @param year
     *            year
     * @return the end date
     */
    public Date getEndDate(final int year) {

        final MonthOfYear nextM = next();
        final Date beginNext = TimeUtils.createDate(year, nextM.month, 1);

        Date endDate = TimeUtils.addCalendarField(beginNext, Calendar.MILLISECOND, -1);

        if (JANUARY.equals(nextM)) {
            endDate = TimeUtils.addCalendarField(endDate, Calendar.YEAR, 1);
        }

        return endDate;
    }

    /**
     * Returns the current month.
     *
     * @return the current month
     */
    public static MonthOfYear getCurrentMonth() {

        final Calendar calendarNow = new GregorianCalendar();
        final int month = calendarNow.get(Calendar.MONTH);

        for (final MonthOfYear monthOfYear : values()) {

            if (monthOfYear.month == month) {
                return monthOfYear;
            }
        }

        return null;
    }

}
