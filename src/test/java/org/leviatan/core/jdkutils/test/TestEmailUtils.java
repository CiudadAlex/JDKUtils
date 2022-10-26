package org.leviatan.core.jdkutils.test;

import org.leviatan.core.jdkutils.email.EmailData;
import org.leviatan.core.jdkutils.email.EmailUtils;

/**
 * TestEmailUtils.
 *
 * @author acc
 *
 */
public final class TestEmailUtils {

    private TestEmailUtils() {
    }

    /**
     * Main method.
     *
     * @param args
     *            args
     */
    public static void main(final String[] args) {

        EmailUtils.enableEmailSending();

        final EmailData emailData = new EmailData("aaa@leviatan.org", "aciudad@leviatan.org", "prueba de email", "cuerpo de la prueba de email");
        final String host = "nombres.leviatan.org";
        EmailUtils.sendEmail(host, emailData);
    }
}
