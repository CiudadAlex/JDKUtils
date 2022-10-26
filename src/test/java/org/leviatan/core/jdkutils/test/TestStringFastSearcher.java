package org.leviatan.core.jdkutils.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.leviatan.core.jdkutils.fastsearch.LuceneStringFastSearcher;
import org.leviatan.core.jdkutils.fastsearch.LuceneStringFastSearcherFactory;
import org.leviatan.core.jdkutils.fastsearch.MatrixStringFastSearcher;
import org.leviatan.core.jdkutils.log.TimeProgressLogger;

/**
 * TestStringFastSearcher.
 *
 * @author acc
 *
 */
public final class TestStringFastSearcher {

    private TestStringFastSearcher() {
    }

    /**
     * Main method.
     *
     * @param args
     *            args
     */
    public static void main(final String[] args) throws Exception {

        final int mil = 1000;
        final int sizeList = 20 * mil;
        testStringFastSearcher(sizeList);
    }

    private static void testStringFastSearcher(final int sizeList) throws Exception {

        System.out.println("### creating cospus");
        final List<String> list = new ArrayList<String>();

        for (int i = 0; i < sizeList; i++) {

            list.add("" + i);
        }

        System.out.println("### Indexing StringFastSearcher");
        final LuceneStringFastSearcherFactory luceneFactory = new LuceneStringFastSearcherFactory();
        luceneFactory.addListString(list);
        final LuceneStringFastSearcher luceneSearcher = luceneFactory.build();

        System.out.println("### Indexing MatrixStringFastSearcher");
        final List<Character> listConsideredCharacters = Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
        final MatrixStringFastSearcher matrixSearcher = new MatrixStringFastSearcher(list, listConsideredCharacters);

        final String wordFound = "" + sizeList / 2;
        final String wordNOTFound = "" + sizeList * 2;

        System.out.println("### true");
        checkTime(list, luceneSearcher, matrixSearcher, wordFound);

        System.out.println("### false");
        checkTime(list, luceneSearcher, matrixSearcher, wordNOTFound);

        checkCorrectness(sizeList, list, luceneSearcher, matrixSearcher);
    }

    private static void checkCorrectness(final int sizeList, final List<String> list, final LuceneStringFastSearcher luceneSearcher,
            final MatrixStringFastSearcher matrixSearcher) throws Exception {

        final int sizeListToCheck = sizeList * 2;
        final TimeProgressLogger timeProgressLogger = new TimeProgressLogger(sizeListToCheck, 100);

        for (int i = 0; i < sizeListToCheck; i++) {

            final String word = "" + i;

            final boolean resultList = list.contains(word);
            final boolean resultLucene = luceneSearcher.contains(word);
            final boolean resultMatrixSearcher = matrixSearcher.contains(word);

            final boolean correct = resultList == resultLucene && resultList == resultMatrixSearcher;

            if (!correct) {
                System.out.println("### ERROR");
                System.out.println("word = " + word);
                System.out.println("List     = " + resultList);
                System.out.println("Lucene   = " + resultLucene);
                System.out.println("Matrix   = " + resultMatrixSearcher);
                return;
            }

            timeProgressLogger.stepFinishedAndPrintProgress();
        }

        System.out.println("### All correct");
    }

    private static void checkTime(final List<String> list, final LuceneStringFastSearcher luceneSearcher,
            final MatrixStringFastSearcher matrixSearcher, final String word) throws Exception {

        final long date1 = System.nanoTime();

        final boolean resultList = list.contains(word);

        final long date2 = System.nanoTime();

        final boolean resultLucene = luceneSearcher.contains(word);

        final long date3 = System.nanoTime();

        final boolean resultMatrixSearcher = matrixSearcher.contains(word);

        final long date4 = System.nanoTime();

        final long diffList = date2 - date1;
        final long diffSearcher = date3 - date2;
        final long diffMatrix = date4 - date3;

        System.out.println("List     >> " + diffList / 1000.0 + " >> " + resultList);
        System.out.println("Lucene   >> " + diffSearcher / 1000.0 + " >> " + resultLucene);
        System.out.println("Matrix   >> " + diffMatrix / 1000.0 + " >> " + resultMatrixSearcher);

        System.out.println("#####################");
    }

}
