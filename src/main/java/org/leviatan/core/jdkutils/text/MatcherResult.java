package org.leviatan.core.jdkutils.text;

/**
 * MatcherResult.
 *
 * @author acc
 *
 */
public class MatcherResult {

    private final Integer beginIndex;
    private final Integer endIndex;

    /**
     * Constructor for MatcherResult.
     *
     * @param beginIndex
     *            beginIndex
     * @param endIndex
     *            endIndex
     */
    public MatcherResult(final Integer beginIndex, final Integer endIndex) {
        this.beginIndex = beginIndex;
        this.endIndex = endIndex;
    }

    public Integer getBeginIndex() {
        return this.beginIndex;
    }

    public Integer getEndIndex() {
        return this.endIndex;
    }

}
