package org.leviatan.core.jdkutils.file;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

import org.leviatan.core.jdkutils.log.AppLogger;

/**
 * FileUtils.
 *
 * @author acc
 *
 */
public final class FileUtils {

    private FileUtils() {
    }

    /**
     * Creates the file with the content.
     *
     * @param path
     *            path
     * @param content
     *            content
     * @throws IOException
     */
    public static void createFileWithContent(final String path, final String content) throws IOException {

        final File file = new File(path);

        if (!file.exists()) {
            file.createNewFile();
        }

        final FileOutputStream fos = new FileOutputStream(file);
        fos.write(content.getBytes());
        fos.flush();
        fos.close();
    }

    /**
     * Creates a temp file.
     *
     * @param prefix
     *            prefix
     * @param suffix
     *            suffix
     * @param content
     *            content
     * @return the path of the temp file
     */
    public static String createTempFileAndReturnPath(final String prefix, final String suffix, final String content) {
        return createTempFileAndReturnPath(prefix, suffix, new ByteArrayInputStream(content.getBytes()));
    }

    /**
     * Creates a temp file.
     *
     * @param prefix
     *            prefix
     * @param suffix
     *            suffix
     * @param is
     *            InputStream
     * @return the path of the temp file
     */
    public static String createTempFileAndReturnPath(final String prefix, final String suffix, final InputStream is) {

        FileOutputStream fos = null;

        try {
            final File temp = File.createTempFile(prefix, suffix);

            fos = new FileOutputStream(temp);

            while (is.available() > 0) {

                final int b = is.read();
                fos.write(b);
            }

            temp.deleteOnExit();

            return temp.getAbsolutePath();

        } catch (final Exception e) {
            AppLogger.logError("Error creating a temp file", e);

        } finally {

            if (fos != null) {
                try {
                    fos.close();
                } catch (final IOException e) {
                    AppLogger.logError("Error closing a FileOutputStream", e);
                }
            }
        }

        return null;
    }

    /**
     * Creates a temp file.
     *
     * @param prefix
     *            prefix
     * @param suffix
     *            suffix
     * @param is
     *            InputStream
     * @param charset
     *            charset. Example: StandardCharsets.UTF_8
     * @return the path of the temp file
     */
    public static String createTempFileAndReturnPath(final String prefix, final String suffix, final InputStream is,
            final Charset charset) {

        OutputStreamWriter writer = null;

        try {
            final File temp = File.createTempFile(prefix, suffix);

            final FileOutputStream fos = new FileOutputStream(temp);

            writer = new OutputStreamWriter(fos, charset);

            while (is.available() > 0) {

                final int b = is.read();
                writer.write(b);
            }

            writer.flush();

            temp.deleteOnExit();

            return temp.getAbsolutePath();

        } catch (final Exception e) {
            AppLogger.logError("Error creating a temp file", e);

        } finally {

            if (writer != null) {
                try {
                    writer.close();
                } catch (final IOException e) {
                    AppLogger.logError("Error closing a FileOutputStream", e);
                }
            }
        }

        return null;
    }

    /**
     * Return the file content.
     *
     * @param file
     *            file
     * @return the file content
     */
    public static byte[] getFileContent(final File file) {

        FileInputStream fis = null;

        try {
            fis = new FileInputStream(file);

            final ByteArrayOutputStream baos = new ByteArrayOutputStream();

            while (fis.available() > 0) {
                baos.write(fis.read());
            }

            return baos.toByteArray();

        } catch (final Exception e) {
            AppLogger.logError("Error getting the file content", e);
            return null;

        } finally {

            if (fis != null) {

                try {
                    fis.close();
                } catch (final IOException e) {
                    AppLogger.logError("Error closing a FileInputStream", e);
                }
            }
        }
    }
}
