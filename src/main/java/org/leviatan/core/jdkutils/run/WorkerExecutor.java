package org.leviatan.core.jdkutils.run;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import org.leviatan.core.jdkutils.log.AppLogger;

/**
 * WorkerExecutor.
 *
 * @author acc
 *
 */
public final class WorkerExecutor {

    private WorkerExecutor() {
    }

    /**
     * Executes the workers in serial mode.
     *
     * @param listWorkers
     *            listWorkers
     */
    public static void executeInSerial(final List<Callable<?>> listWorkers) {

        for (final Callable<?> worker : listWorkers) {

            try {
                worker.call();

            } catch (final Exception e) {
                AppLogger.logError("Error executing worker", e);
            }
        }
    }

    /**
     * Executes the workers in larallel mode.
     *
     * @param listWorkers
     *            listWorkers
     * @param executorService
     *            executorService
     */
    public static void executeInParallelAndWaitToFinish(final List<Callable<?>> listWorkers, final ExecutorService executorService) {

        final List<Future<?>> listFuture = new ArrayList<Future<?>>();

        for (final Callable<?> worker : listWorkers) {

            final Future<?> future = executorService.submit(worker);
            listFuture.add(future);
        }

        for (final Future<?> future : listFuture) {

            try {
                future.get();

            } catch (final Exception e) {
                AppLogger.logError("Error waiting for future works to be done", e);
            }
        }

    }

}
