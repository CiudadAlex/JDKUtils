package org.leviatan.core.jdkutils.thread;

/**
 * ThreadUtils.
 *
 * @author alciucam
 *
 */
public final class ThreadUtils {

    private ThreadUtils() {
    }

    /**
     * Returns the thread by Id.
     *
     * @param threadId
     *            threadId
     * @return the thread found
     */
    public static Thread getThreadById(final long threadId) {

        final Thread[] allActiveThreads = getAllActiveThreads();

        for (final Thread thread : allActiveThreads) {

            if (threadId == thread.getId()) {
                return thread;
            }
        }

        return null;
    }

    private static Thread[] getAllActiveThreads() {

        final ThreadGroup rootThreadGroup = getRootThreadGroup();
        final int countActiveThreads = rootThreadGroup.activeCount();

        final Thread[] allActiveThreads = new Thread[countActiveThreads];
        rootThreadGroup.enumerate(allActiveThreads);

        return allActiveThreads;
    }

    private static ThreadGroup getRootThreadGroup() {

        ThreadGroup rootGroup = Thread.currentThread().getThreadGroup();
        ThreadGroup parentGroup = rootGroup.getParent();

        while (parentGroup != null) {

            rootGroup = parentGroup;
            parentGroup = rootGroup.getParent();
        }

        return rootGroup;
    }
}
