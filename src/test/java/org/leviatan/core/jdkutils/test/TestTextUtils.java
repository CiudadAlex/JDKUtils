package org.leviatan.core.jdkutils.test;

import java.util.List;

import org.leviatan.core.jdkutils.text.TextUtils;

/**
 * TestTextUtils.
 *
 * @author acc
 *
 */
public final class TestTextUtils {

    private TestTextUtils() {
    }

    /**
     * Main method.
     *
     * @param args
     *            args
     */
    public static void main(final String[] args) {

        // testSplitInPhrases();
        testToLowerCaseAndNormVowelsAndTrim();
    }

    protected static void testSplitInPhrases() {

        final String txt = "Una frase inacabada... otra frase con numero 4.55 e interesante. Finalmente otra frase";
        final List<String> listPhrases = TextUtils.splitInPhrases(txt);

        for (final String phrase : listPhrases) {

            System.out.println("#### " + phrase);
        }
    }

    protected static void testToLowerCaseAndNormVowelsAndTrim() {

        final String txt = "Aquí hay UN ejêmPlÖ cláro de SÍ fUnCiôna èL mètöDO o nO";

        final String result = TextUtils.toLowerCaseAndNormVowelsAndTrim(txt);

        System.out.println(txt);
        System.out.println(result);
    }

}
