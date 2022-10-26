package org.leviatan.core.jdkutils.email;

import java.security.GeneralSecurityException;
import java.util.Properties;

import com.sun.mail.util.MailSSLSocketFactory;

import org.leviatan.core.jdkutils.log.AppLogger;

/**
 * EmailServerConfig.
 *
 * @author acc
 *
 */
public class EmailServerConfig {

    private final String host;
    private boolean auth;
    private String protocol = "smtp";
    private int port = 25;
    private boolean debug = true;

    private String authenticationLogin;
    private String authenticationPassword;

    private boolean isSSL;

    /**
     * Constructor for EmailServerConfig.
     *
     * @param host
     *            host
     */
    public EmailServerConfig(final String host) {
        this.host = host;
    }

    /**
     * Constructor for EmailServerConfig.
     *
     * @param host
     *            host
     * @param isSSL
     *            isSSL
     */
    public EmailServerConfig(final String host, final boolean isSSL) {
        this.host = host;
        this.isSSL = isSSL;
    }

    /**
     * Returns the properties.
     *
     * @return the properties
     */
    public Properties getProperties() {

        final Properties props = new Properties();

        props.put("mail.smtp.host", this.host);
        props.put("mail.smtp.auth", "" + this.auth);

        props.put("mail.transport.protocol", this.protocol);
        props.put("mail.smtp.port", "" + this.port);
        props.put("mail.debug", "" + this.debug);

        if (this.isSSL) {
            addSSLProperties(props);
        }

        return props;
    }

    private void addSSLProperties(final Properties props) {

        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.socketFactory.port", "" + this.port);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");

        MailSSLSocketFactory sf = null;

        try {
            sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);

        } catch (final GeneralSecurityException e) {
            AppLogger.logError("Error creating MailSSLSocketFactory", e);
        }

        props.put("mail.smtp.ssl.socketFactory", sf);
        props.put("mail.smtps.port", this.port);
        props.put("mail.smtps.host", this.host);
        props.put("mail.smtps.debug", "" + this.debug);
        props.put("mail.smtps.ssl.enable", "true");
    }

    public String getHost() {
        return this.host;
    }

    public boolean isAuth() {
        return this.auth;
    }

    public void setAuth(final boolean auth) {
        this.auth = auth;
    }

    public String getProtocol() {
        return this.protocol;
    }

    public void setProtocol(final String protocol) {
        this.protocol = protocol;
    }

    public int getPort() {
        return this.port;
    }

    public void setPort(final int port) {
        this.port = port;
    }

    public boolean isDebug() {
        return this.debug;
    }

    public void setDebug(final boolean debug) {
        this.debug = debug;
    }

    public String getAuthenticationLogin() {
        return this.authenticationLogin;
    }

    public void setAuthenticationLogin(final String authenticationLogin) {
        this.authenticationLogin = authenticationLogin;
    }

    public String getAuthenticationPassword() {
        return this.authenticationPassword;
    }

    public void setAuthenticationPassword(final String authenticationPassword) {
        this.authenticationPassword = authenticationPassword;
    }

}
