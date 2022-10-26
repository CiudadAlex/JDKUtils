package org.leviatan.core.jdkutils.text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * PatternUtils.
 *
 * @author acc
 *
 */
public final class PatternUtils {

    private PatternUtils() {
    }

    /**
     * Finds a pattern in the text.
     *
     * @param text
     *            text
     * @param patternToFind
     *            patternToFind
     * @return MatcherResult
     */
    public static MatcherResult buildMatcherWithSomethingFound(final String text, final String patternToFind) {

        final Pattern pattern = Pattern.compile(patternToFind, Pattern.CASE_INSENSITIVE);
        final Matcher matcher = pattern.matcher(text);

        if (!matcher.find()) {
            return null;
        }

        final Integer beginIndex = matcher.start();
        final Integer endIndex = matcher.end();

        return new MatcherResult(beginIndex, endIndex);
    }
}
