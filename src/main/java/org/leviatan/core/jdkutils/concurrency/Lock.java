package org.leviatan.core.jdkutils.concurrency;

/**
 * Lock.
 *
 * @author acc
 *
 */
public interface Lock {

    /**
     * Unlocks the resource.
     *
     * @return false if fails since the lock is not currently alive.
     */
    public boolean unlock();

    /**
     * Renews the lock on the resource.
     *
     * @return false if fails since the lock is not currently alive.
     */
    public boolean renewLock();

    /**
     * Returns if the lock is alive.
     *
     * @return returns if the lock is still alive.
     */
    public boolean isLockAlive();

}
