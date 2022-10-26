package org.leviatan.core.jdkutils.text;

import java.security.SecureRandom;

/**
 * TextGenerator.
 *
 * @author acc
 *
 */
public final class TextGenerator {

    private TextGenerator() {
    }

    private static final String CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final SecureRandom RANDOM = new SecureRandom();

    /**
     * Returns a random String of the given length.
     *
     * @param len
     *            len
     * @return a random String of the given length
     */
    public static String randomString(final int len) {

        final StringBuilder sb = new StringBuilder(len);

        for (int i = 0; i < len; i++) {

            final int index = RANDOM.nextInt(CHARS.length());
            sb.append(CHARS.charAt(index));
        }

        return sb.toString();
    }
}
