package org.leviatan.core.jdkutils.time;

import java.util.Date;

import org.leviatan.core.jdkutils.time.constants.SemesterOfYear;

/**
 * SemesterInstance.
 *
 * @author acc
 *
 */
public class SemesterInstance {

    private final SemesterOfYear semesterOfYear;
    private final int year;

    /**
     * Constructor for QuarterInstance.
     *
     * @param semesterOfYear
     *            semesterOfYear
     * @param year
     *            year
     */
    public SemesterInstance(final SemesterOfYear semesterOfYear, final int year) {
        this.semesterOfYear = semesterOfYear;
        this.year = year;
    }

    public SemesterOfYear getSemesterOfYear() {
        return this.semesterOfYear;
    }

    public int getYear() {
        return this.year;
    }

    /**
     * Returns the begin date.
     *
     * @return the begin date
     */
    public Date getBeginDate() {
        return this.semesterOfYear.getBeginDate(this.year);
    }

    /**
     * Returns the end date.
     *
     * @return the end date
     */
    public Date getEndDate() {
        return this.semesterOfYear.getEndDate(this.year);
    }

    /**
     * Returns the next SemesterInstance.
     *
     * @return the next SemesterInstance
     */
    public SemesterInstance next() {

        final SemesterOfYear nextS = this.semesterOfYear.next();
        int yearOfNextSemester = this.year;

        if (SemesterOfYear.S1.equals(nextS)) {
            yearOfNextSemester++;
        }

        return new SemesterInstance(nextS, yearOfNextSemester);
    }

    @Override
    public String toString() {
        return this.semesterOfYear.name() + "-" + this.year;
    }

}
