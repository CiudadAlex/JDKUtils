package org.leviatan.core.jdkutils.email;

import java.io.File;
import java.util.List;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.leviatan.core.jdkutils.log.AppLogger;
import org.leviatan.core.jdkutils.text.TextUtils;

/**
 * EmailUtils.
 *
 * @author acc
 *
 */
public final class EmailUtils {

    private static boolean ENABLE_EMAIL_SEND;

    private EmailUtils() {
    }

    /** Enables the email sending. */
    public static void enableEmailSending() {
        ENABLE_EMAIL_SEND = true;
    }

    /**
     * Sends an email.
     *
     * @param host
     *            host
     * @param data
     *            emailData
     * @return returns true if no errors
     */
    public static boolean sendEmail(final String host, final EmailData data) {
        return sendEmail(new EmailServerConfig(host), data);
    }

    /**
     * Sends an email.
     *
     * @param emailServerConfig
     *            emailServerConfig
     * @param data
     *            emailData
     * @return returns true if no errors
     */
    public static boolean sendEmail(final EmailServerConfig emailServerConfig, final EmailData data) {

        if (!ENABLE_EMAIL_SEND) {
            AppLogger.logDebug("Email sending disabled");
            return true;
        }

        try {

            final Session session = buildSession(emailServerConfig);

            final Message message = buildMessageWithoutContent(data, session);

            message.setContent(data.getBody(), "text/html; charset=ISO-8859-1");

            sendMessage(data, session, message);

            return true;

        } catch (final MessagingException me) {
            me.printStackTrace();
        }

        return false;
    }

    private static void sendMessage(final EmailData data, final Session session, final Message message)
            throws NoSuchProviderException, MessagingException {

        final Transport t = session.getTransport("smtp");
        t.connect();
        t.sendMessage(message, message.getRecipients(Message.RecipientType.TO));

        if (!data.getCc().isEmpty()) {
            t.sendMessage(message, message.getRecipients(Message.RecipientType.CC));
        }
        if (!data.getBcc().isEmpty()) {
            t.sendMessage(message, message.getRecipients(Message.RecipientType.BCC));
        }

        t.close();
    }

    private static Message buildMessageWithoutContent(final EmailData data, final Session session)
            throws MessagingException, AddressException {

        final Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(data.getFrom()));
        message.setSubject(data.getSubject());

        for (final String to : data.getTo()) {
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        }

        for (final String cc : data.getCc()) {
            message.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));
        }

        for (final String bcc : data.getBcc()) {
            message.addRecipient(Message.RecipientType.BCC, new InternetAddress(bcc));
        }

        return message;
    }

    private static Session buildSession(final EmailServerConfig emailServerConfig) {

        final Authenticator authenticator = buildAuthenticator(emailServerConfig);

        final Session session = Session.getInstance(emailServerConfig.getProperties(), authenticator);
        session.setDebug(true);
        return session;
    }

    private static Authenticator buildAuthenticator(final EmailServerConfig emailServerConfig) {

        final String authenticationLogin = emailServerConfig.getAuthenticationLogin();
        final String authenticationPassword = emailServerConfig.getAuthenticationPassword();

        if (authenticationLogin == null || authenticationPassword == null) {
            return null;
        }

        return new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(authenticationLogin, authenticationPassword);
            }
        };
    }

    /**
     * Sends an email with attachments.
     *
     * @param emailServerConfig
     *            emailServerConfig
     * @param data
     *            emailData
     * @param listAttachments
     *            listAttachments
     * @return returns true if no errors
     */
    public static boolean sendEmailWithAttachments(final EmailServerConfig emailServerConfig, final EmailData data,
            final List<File> listAttachments) {

        if (!ENABLE_EMAIL_SEND) {
            AppLogger.logDebug("Email sending disabled");
            return true;
        }

        try {

            final Session session = buildSession(emailServerConfig);

            final Message message = buildMessageWithoutContent(data, session);

            final Multipart multipart = buildMultipartContent(data, listAttachments);

            message.setContent(multipart);

            sendMessage(data, session, message);

            return true;

        } catch (final MessagingException me) {
            me.printStackTrace();
        }

        return false;
    }

    private static Multipart buildMultipartContent(final EmailData data, final List<File> listAttachments) throws MessagingException {

        // Create a multipar message
        final Multipart multipart = new MimeMultipart();

        // Create the message part
        final BodyPart messageBodyPart = new MimeBodyPart();

        // Now set the actual message
        messageBodyPart.setText(data.getBody());

        // Set text message part
        multipart.addBodyPart(messageBodyPart);

        for (final File file : listAttachments) {

            final BodyPart attachmentBodyPart = buildBodyPartAttachment(file);
            multipart.addBodyPart(attachmentBodyPart);
        }

        return multipart;
    }

    private static BodyPart buildBodyPartAttachment(final File file) throws MessagingException {

        final String filePath = file.getAbsolutePath();
        final String fileName = TextUtils.getFinalPartFromLastDelimeter(filePath.replace("\\", "/"), "/");

        final BodyPart attachmentBodyPart = new MimeBodyPart();

        final DataSource source = new FileDataSource(filePath);
        attachmentBodyPart.setDataHandler(new DataHandler(source));
        attachmentBodyPart.setFileName(fileName);

        return attachmentBodyPart;
    }

}
