package org.leviatan.core.jdkutils.concurrency;

import java.util.Date;

/**
 * LockerAndDate.
 *
 * @author acc
 *
 */
public class LockerAndDate<LOCKER> {

    private final LOCKER locker;
    private final String lockerName;

    private Date date;

    /**
     * Constructor for LockerAndDate.
     *
     * @param locker
     *            locker
     * @param lockerName
     *            lockerName
     * @param date
     *            date
     */
    public LockerAndDate(final LOCKER locker, final String lockerName, final Date date) {
        this.locker = locker;
        this.lockerName = lockerName;
        this.date = date;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(final Date date) {
        this.date = date;
    }

    public LOCKER getLocker() {
        return this.locker;
    }

    public String getLockerName() {
        return this.lockerName;
    }

}
