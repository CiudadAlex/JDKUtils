package org.leviatan.core.jdkutils.run;

import java.util.ArrayList;
import java.util.List;

import org.leviatan.core.jdkutils.time.TimeUtils;

/**
 * MultiThreadExecutor.
 *
 * @author acc
 *
 */
public class MultiThreadExecutor {

    private final List<Thread> listThread = new ArrayList<Thread>();

    /**
     * Adds a new thread.
     *
     * @param runnable
     *            runnable
     * @param numberOfThreads
     *            numberOfThreads
     */
    public void addThreads(final Runnable runnable, final int numberOfThreads) {

        for (int i = 0; i < numberOfThreads; i++) {

            final Thread thread = new Thread() {

                @Override
                public void run() {
                    runnable.run();
                }
            };

            this.listThread.add(thread);
        }
    }

    /** Starts all the threads. */
    public void startThreads() {

        for (final Thread thread : this.listThread) {
            thread.start();
        }

        TimeUtils.sleep(1000);

        while (isAnyThreadAlive()) {
            TimeUtils.sleep(1000);
        }
    }

    /**
     * Returns if any thread is alive.
     *
     * @return if any thread is alive
     */
    public boolean isAnyThreadAlive() {

        for (final Thread thread : this.listThread) {
            if (thread.isAlive()) {
                return true;
            }
        }

        return false;
    }
}
