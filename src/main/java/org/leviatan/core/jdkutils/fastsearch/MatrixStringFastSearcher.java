package org.leviatan.core.jdkutils.fastsearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.leviatan.core.jdkutils.object.ObjectUtils;

/**
 * MatrixStringFastSearcher.
 *
 * @author acc
 *
 */
public class MatrixStringFastSearcher implements StringFastSearcher {

    private static final int MATRIX_DIMENSION = 5;

    private final Map<Character, Integer> mapCharIndex = new HashMap<Character, Integer>();

    private final List<String> listNonConsideredChars = new ArrayList<String>();
    private final List<String> listShortTexts = new ArrayList<String>();
    private final List<String>[][][][][] matrix;

    /**
     * Constructor for MatrixStringFastSearcher.
     *
     * @param listTxt
     *            listTxt
     * @param listConsideredCharacters
     *            listConsideredCharacters
     */
    @SuppressWarnings("unchecked")
    public MatrixStringFastSearcher(final List<String> listTxt, final List<Character> listConsideredCharacters) {

        int count = 0;
        for (final Character character : listConsideredCharacters) {
            this.mapCharIndex.put(character, count);
            count++;
        }

        final int numCharsConsidered = listConsideredCharacters.size();

        this.matrix = new List[numCharsConsidered][numCharsConsidered][numCharsConsidered][numCharsConsidered][numCharsConsidered];

        for (final String txt : listTxt) {
            processText(txt);
        }
    }

    private void processText(final String txt) {

        if (txt.length() <= MATRIX_DIMENSION) {
            // Short text
            this.listShortTexts.add(txt);
            return;
        }

        final char ch0 = txt.charAt(0);
        final char ch1 = txt.charAt(1);
        final char ch2 = txt.charAt(2);
        final char ch3 = txt.charAt(3);
        final char ch4 = txt.charAt(4);

        final Integer idx0 = this.mapCharIndex.get(ch0);
        final Integer idx1 = this.mapCharIndex.get(ch1);
        final Integer idx2 = this.mapCharIndex.get(ch2);
        final Integer idx3 = this.mapCharIndex.get(ch3);
        final Integer idx4 = this.mapCharIndex.get(ch4);

        if (ObjectUtils.isThereAnyNull(idx0, idx1, idx2, idx3, idx4)) {
            // Non considered chars involved in index
            this.listNonConsideredChars.add(txt);
            return;
        }

        if (this.matrix[idx0][idx1][idx2][idx3][idx4] == null) {
            this.matrix[idx0][idx1][idx2][idx3][idx4] = new ArrayList<String>();
        }

        this.matrix[idx0][idx1][idx2][idx3][idx4].add(txt);
    }

    @Override
    public boolean contains(final String txt) {

        if (txt.length() <= MATRIX_DIMENSION) {
            // Short text
            return this.listShortTexts.contains(txt);
        }

        final char ch0 = txt.charAt(0);
        final char ch1 = txt.charAt(1);
        final char ch2 = txt.charAt(2);
        final char ch3 = txt.charAt(3);
        final char ch4 = txt.charAt(4);

        final Integer idx0 = this.mapCharIndex.get(ch0);
        final Integer idx1 = this.mapCharIndex.get(ch1);
        final Integer idx2 = this.mapCharIndex.get(ch2);
        final Integer idx3 = this.mapCharIndex.get(ch3);
        final Integer idx4 = this.mapCharIndex.get(ch4);

        if (ObjectUtils.isThereAnyNull(idx0, idx1, idx2, idx3, idx4)) {
            // Non considered chars involved in index
            return this.listNonConsideredChars.contains(txt);
        }

        if (this.matrix[idx0][idx1][idx2][idx3][idx4] == null) {
            this.matrix[idx0][idx1][idx2][idx3][idx4] = new ArrayList<String>();
        }

        return this.matrix[idx0][idx1][idx2][idx3][idx4].contains(txt);
    }
}
