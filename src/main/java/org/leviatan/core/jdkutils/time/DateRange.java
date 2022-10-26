package org.leviatan.core.jdkutils.time;

import java.util.Date;

/**
 * DateRange.
 *
 * @author acc
 *
 */
public class DateRange {

    private final Date begin;
    private final Date end;

    /**
     * Constructor for DateRange.
     *
     * @param begin
     *            begin
     * @param end
     *            end
     */
    public DateRange(final Date begin, final Date end) {
        this.begin = begin;
        this.end = end;
    }

    public Date getBegin() {
        return this.begin;
    }

    public Date getEnd() {
        return this.end;
    }

    /**
     * Returns if the DateRange is valid.
     *
     * @return if the DateRange is valid
     */
    public boolean isValid() {

        if (this.begin != null && this.end != null) {

            if (this.begin.getTime() > this.end.getTime()) {
                return false;
            }
        }

        return true;
    }

    public long getDiffInMillis() {
        return this.end.getTime() - this.begin.getTime();
    }

}
