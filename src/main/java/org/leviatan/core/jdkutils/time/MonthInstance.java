package org.leviatan.core.jdkutils.time;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.leviatan.core.jdkutils.time.constants.MonthOfYear;

/**
 * MonthInstance.
 *
 * @author acc
 *
 */
public class MonthInstance {

    private final MonthOfYear monthOfYear;
    private final int year;

    /**
     * Constructor for MonthInstance.
     *
     * @param monthOfYear
     *            monthOfYear
     * @param year
     *            year
     */
    public MonthInstance(final MonthOfYear monthOfYear, final int year) {
        this.monthOfYear = monthOfYear;
        this.year = year;
    }

    public MonthOfYear getMonthOfYear() {
        return this.monthOfYear;
    }

    public int getYear() {
        return this.year;
    }

    /**
     * Returns the current month.
     *
     * @return the current month
     */
    public static MonthInstance getCurrentMonth() {

        final MonthOfYear monthOfYear = MonthOfYear.getCurrentMonth();

        final Calendar calendarNow = new GregorianCalendar();
        final int year = calendarNow.get(Calendar.YEAR);

        return new MonthInstance(monthOfYear, year);
    }

    /**
     * Returns the begin date.
     *
     * @return the begin date
     */
    public Date getBeginDate() {
        return this.monthOfYear.getBeginDate(this.year);
    }

    /**
     * Returns the end date.
     *
     * @return the end date
     */
    public Date getEndDate() {
        return this.monthOfYear.getEndDate(this.year);
    }

    /**
     * Returns the next MonthInstance.
     *
     * @return the next MonthInstance
     */
    public MonthInstance next() {

        final MonthOfYear nextM = this.monthOfYear.next();
        int yearOfNextMonth = this.year;

        if (MonthOfYear.JANUARY.equals(nextM)) {
            yearOfNextMonth++;
        }

        return new MonthInstance(nextM, yearOfNextMonth);
    }

    /**
     * Returns the string representation.
     *
     * @param format
     *            format
     * @return the string representation
     */
    public String toString(final String format) {

        final Date date = TimeUtils.createDate(this.year, this.monthOfYear.getMonth(), 1);
        return TimeUtils.formatDate(date, format);
    }

    @Override
    public String toString() {
        return toString("MMM-yy");
    }

    public QuarterInstance getQuarterInstance() {
        return new QuarterInstance(this.monthOfYear.getQuarterOfYear(), this.year);
    }

}
