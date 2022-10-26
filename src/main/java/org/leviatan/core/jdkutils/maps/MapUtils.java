package org.leviatan.core.jdkutils.maps;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.leviatan.core.jdkutils.run.Function2Arg;

/**
 * MapUtils.
 *
 * @author acc
 *
 */
public final class MapUtils {

    private MapUtils() {
    }

    /**
     * Distributes the objects by key.
     *
     * @param <K>
     *            key object
     * @param <T>
     *            type of object
     * @param list
     *            list
     * @param getKey
     *            getKey
     * @return distributed objects
     */
    public static <K, T> MapObjectToList<K, T> distribute(final List<T> list, final Function<T, K> getKey) {

        final MapObjectToList<K, T> map = new MapObjectToList<K, T>();

        for (final T obj : list) {

            final K key = getKey.apply(obj);
            map.add(key, obj);
        }

        return map;
    }

    /**
     * Transforms the map.
     *
     * @param <K>
     *            key object
     * @param <VI>
     *            type of object in initial map
     * @param <VR>
     *            type of object in resulting map
     * @param map
     *            initial map
     * @param transformation
     *            transformation
     * @return transformed map
     */
    public static <K, VI, VR> Map<K, VR> transformMap(final Map<K, VI> map, final Function<VI, VR> transformation) {

        final Map<K, VR> mapResult = new HashMap<K, VR>();

        for (final K key : map.keySet()) {

            final VI vi = map.get(key);
            final VR vr = transformation.apply(vi);
            mapResult.put(key, vr);
        }

        return mapResult;
    }

    /**
     * Operation that involves the intersecting keys operatin with the values of
     * two maps.
     *
     * @param <K>
     *            key object
     * @param <VA>
     *            type of object in map A
     * @param <VB>
     *            type of object in map B
     * @param <VR>
     *            type of object in resulting map
     * @param mapA
     *            mapA
     * @param mapB
     *            mapB
     * @param operation
     *            operation
     * @return result map
     */
    public static <K, VA, VB, VR> Map<K, VR> makeOperation(final Map<K, VA> mapA, final Map<K, VB> mapB,
            final Function2Arg<VA, VB, VR> operation) {

        final Map<K, VR> mapResult = new HashMap<K, VR>();

        for (final K key : mapA.keySet()) {

            final VA va = mapA.get(key);
            final VB vb = mapB.get(key);

            if (va != null && vb != null) {
                final VR vr = operation.apply(va, vb);
                mapResult.put(key, vr);
            }
        }

        return mapResult;
    }

    /**
     * Operation that involves the intersecting keys operatin with the values of
     * two maps.
     *
     * @param <K>
     *            key object
     * @param mapA
     *            mapA
     * @param mapB
     *            mapB
     * @param operation
     *            operation
     * @return result map
     */
    public static <K> Map<K, Double> makeOperationDouble(final Map<K, Double> mapA, final Map<K, Double> mapB,
            final Function2Arg<Double, Double, Double> operation) {
        return makeOperation(mapA, mapB, operation);
    }

    /**
     * Sum that involves the intersecting keys operatin with the values of two
     * maps.
     *
     * @param <K>
     *            key object
     * @param mapA
     *            mapA
     * @param mapB
     *            mapB
     * @return result map
     */
    public static <K> Map<K, Double> makeOperationDoubleSum(final Map<K, Double> mapA, final Map<K, Double> mapB) {
        return makeOperation(mapA, mapB, (va, vb) -> va + vb);
    }

    /**
     * Multiplication that involves the intersecting keys operatin with the
     * values of two maps.
     *
     * @param <K>
     *            key object
     * @param mapA
     *            mapA
     * @param mapB
     *            mapB
     * @return result map
     */
    public static <K> Map<K, Double> makeOperationDoubleMultiply(final Map<K, Double> mapA, final Map<K, Double> mapB) {
        return makeOperation(mapA, mapB, (va, vb) -> va * vb);
    }

    /**
     * Substraction that involves the intersecting keys operatin with the values
     * of two maps.
     *
     * @param <K>
     *            key object
     * @param mapA
     *            mapA
     * @param mapB
     *            mapB
     * @return result map
     */
    public static <K> Map<K, Double> makeOperationDoubleSubstraction(final Map<K, Double> mapA, final Map<K, Double> mapB) {
        return makeOperation(mapA, mapB, (va, vb) -> va - vb);
    }

    /**
     * Division that involves the intersecting keys operatin with the values of
     * two maps.
     *
     * @param <K>
     *            key object
     * @param mapA
     *            mapA
     * @param mapB
     *            mapB
     * @return result map
     */
    public static <K> Map<K, Double> makeOperationDoubleDivision(final Map<K, Double> mapA, final Map<K, Double> mapB) {
        return makeOperation(mapA, mapB, (va, vb) -> va / vb);
    }
}
