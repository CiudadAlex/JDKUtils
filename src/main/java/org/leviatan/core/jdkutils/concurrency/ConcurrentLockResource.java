package org.leviatan.core.jdkutils.concurrency;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.leviatan.core.jdkutils.time.TimeUtils;

/**
 * ConcurrentLockResource.
 *
 * @author acc
 *
 */
public class ConcurrentLockResource<RESOURCE, LOCKER> {

    private final Map<RESOURCE, LockerAndDate<LOCKER>> mapResourceLocker = new HashMap<RESOURCE, LockerAndDate<LOCKER>>();
    private final long lockMillisTimeout;
    private boolean trace;

    /** Constructor for ConcurrentLockResource. */
    public ConcurrentLockResource() {
        this(1000 * TimeUtils.YEAR);
    }

    /**
     * Constructor for ConcurrentLockResource.
     *
     * @param lockMillisTimeout
     *            lockMillisTimeout
     */
    public ConcurrentLockResource(final long lockMillisTimeout) {
        this.lockMillisTimeout = lockMillisTimeout;
    }

    /**
     * Returns the lock or null of the resource.
     *
     * @param resource
     *            resource
     * @param locker
     *            locker
     * @param lockerName
     *            lockerName
     * @return the lock or null of the resource
     */
    public synchronized Lock getLock(final RESOURCE resource, final LOCKER locker, final String lockerName) {

        final Lock lock = buildLock(resource, locker);

        if (isLockedResourceByLocker(resource, locker)) {
            return lock;
        }

        if (isLockedResource(resource)) {
            return null;
        }

        lockResource(resource, locker, lockerName);
        return lock;
    }

    private Lock buildLock(final RESOURCE resource, final LOCKER locker) {

        final Lock lock = new Lock() {

            @Override
            public boolean unlock() {
                return unlockResourceIfAlive(resource, locker);
            }

            @Override
            public boolean isLockAlive() {
                return isLockedResourceByLocker(resource, locker);
            }

            @Override
            public boolean renewLock() {
                return renewLockIfAlive(resource, locker);
            }

        };

        return lock;
    }

    private synchronized void lockResource(final RESOURCE resource, final LOCKER locker, final String lockerName) {

        final LockerAndDate<LOCKER> lockerAndDate = new LockerAndDate<LOCKER>(locker, lockerName, new Date());
        this.mapResourceLocker.put(resource, lockerAndDate);

        logResourceLocked(resource, locker, lockerName);
    }

    private synchronized boolean unlockResourceIfAlive(final RESOURCE resource, final LOCKER locker) {

        if (isLockedResourceByLocker(resource, locker)) {

            logResourceUnlocked(resource, locker);

            this.mapResourceLocker.remove(resource);
            return true;
        }

        logResourceUnableToUnlock(resource, locker);
        return false;
    }

    private synchronized boolean renewLockIfAlive(final RESOURCE resource, final LOCKER locker) {

        if (isLockedResourceByLocker(resource, locker)) {

            logResourceRenewLock(resource, locker);

            final LockerAndDate<LOCKER> lockerAndDate = this.mapResourceLocker.get(resource);
            lockerAndDate.setDate(new Date());
            return true;
        }

        logResourceUnableToRenewLock(resource, locker);
        return false;
    }

    private synchronized boolean isLockedResource(final RESOURCE resource) {

        final LockerAndDate<LOCKER> lockerAndDate = this.mapResourceLocker.get(resource);

        if (lockerAndDate == null) {
            return false;
        }

        if (isLockerAndDateExpired(lockerAndDate)) {
            return false;
        }

        return true;
    }

    private synchronized boolean isLockedResourceByLocker(final RESOURCE resource, final LOCKER locker) {

        final LockerAndDate<LOCKER> lockerAndDate = this.mapResourceLocker.get(resource);

        if (lockerAndDate == null) {
            return false;
        }

        final LOCKER lockerInMap = lockerAndDate.getLocker();

        if (!lockerInMap.equals(locker)) {
            return false;
        }

        if (isLockerAndDateExpired(lockerAndDate)) {
            return false;
        }

        return true;
    }

    private boolean isLockerAndDateExpired(final LockerAndDate<LOCKER> lockerAndDate) {

        final Date lastLock = lockerAndDate.getDate();
        final Date now = new Date();

        return now.getTime() - lastLock.getTime() > this.lockMillisTimeout;
    }

    /**
     * Returns the locker of the resource.
     *
     * @param resource
     *            resource
     * @return the locker of the resource
     */
    public synchronized LOCKER getLockerOfTheResource(final RESOURCE resource) {

        final LockerAndDate<LOCKER> lockerAndDate = this.mapResourceLocker.get(resource);

        if (lockerAndDate == null) {
            return null;
        }

        return lockerAndDate.getLocker();
    }

    /**
     * Returns the locker name of the resource.
     *
     * @param resource
     *            resource
     * @return the locker name of the resource
     */
    public synchronized String getLockerNameOfTheResource(final RESOURCE resource) {

        final LockerAndDate<LOCKER> lockerAndDate = this.mapResourceLocker.get(resource);

        if (lockerAndDate == null) {
            return null;
        }

        return lockerAndDate.getLockerName();
    }

    public void setTrace(final boolean trace) {
        this.trace = trace;
    }

    private void logResourceLocked(final RESOURCE resource, final LOCKER locker, final String lockerName) {

        if (this.trace) {
            System.out.println("Resource " + resource + " locked by " + locker + " [" + lockerName + "]");
        }
    }

    private void logResourceUnlocked(final RESOURCE resource, final LOCKER locker) {

        if (this.trace) {
            System.out.println("Resource " + resource + " unlocked by " + locker);
        }
    }

    private void logResourceUnableToUnlock(final RESOURCE resource, final LOCKER locker) {

        if (this.trace) {
            System.out.println("Resource " + resource + " unabled to be unlocked by " + locker);
        }
    }

    private void logResourceRenewLock(final RESOURCE resource, final LOCKER locker) {

        if (this.trace) {
            System.out.println("Resource " + resource + " renewed by " + locker);
        }
    }

    private void logResourceUnableToRenewLock(final RESOURCE resource, final LOCKER locker) {

        if (this.trace) {
            System.out.println("Resource " + resource + " unable to be renewed by " + locker);
        }
    }

}
