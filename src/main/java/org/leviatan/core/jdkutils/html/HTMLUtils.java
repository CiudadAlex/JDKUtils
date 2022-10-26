package org.leviatan.core.jdkutils.html;

/**
 * HTMLUtils.
 *
 * @author acc
 *
 */
public final class HTMLUtils {

    private HTMLUtils() {
    }

    /**
     * Generates the a HTML hyperlink.
     *
     * @param url
     *            url
     * @param text
     *            text
     * @return the a HTML hyperlink
     */
    public static String generateHTMLHyperlink(final String url, final String text) {
        return generateHTMLHyperlink(url, text, false);
    }

    /**
     * Generates the a HTML hyperlink.
     *
     * @param url
     *            url
     * @param text
     *            text
     * @param openInNewTab
     *            openInNewTab
     * @return the a HTML hyperlink
     */
    public static String generateHTMLHyperlink(final String url, final String text, final boolean openInNewTab) {

        final String tokenOpenInNewTab = openInNewTab ? "target=\"_blank\"" : "";

        return "<a href=\"" + url + "\" " + tokenOpenInNewTab + " >" + text + "</a>";
    }
}
