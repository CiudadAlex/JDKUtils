package org.leviatan.core.jdkutils.email;

import java.util.ArrayList;
import java.util.List;

public class EmailData {

    private final String from;

    private final List<String> to = new ArrayList<String>();
    private final List<String> cc = new ArrayList<String>();
    private final List<String> bcc = new ArrayList<String>();

    private final String subject;
    private final String body;

    /**
     * Constructor for EmailData.
     *
     * @param from
     *            from
     * @param listTo
     *            listTo
     * @param subject
     *            subject
     * @param body
     *            body
     */
    public EmailData(final String from, final List<String> listTo, final String subject, final String body) {

        this.from = from;
        this.to.addAll(listTo);
        this.subject = subject;
        this.body = body;
    }

    /**
     * Constructor for EmailData.
     *
     * @param from
     *            from
     * @param to
     *            default to
     * @param subject
     *            subject
     * @param body
     *            body
     */
    public EmailData(final String from, final String to, final String subject, final String body) {

        this.from = from;
        this.to.add(to);
        this.subject = subject;
        this.body = body;
    }

    public String getFrom() {
        return this.from;
    }

    public String getSubject() {
        return this.subject;
    }

    public String getBody() {
        return this.body;
    }

    public List<String> getTo() {
        return this.to;
    }

    public List<String> getCc() {
        return this.cc;
    }

    public List<String> getBcc() {
        return this.bcc;
    }

}
