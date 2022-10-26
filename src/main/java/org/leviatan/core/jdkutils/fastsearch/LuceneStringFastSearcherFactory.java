package org.leviatan.core.jdkutils.fastsearch;

import java.util.Arrays;
import java.util.List;

import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

/**
 * LuceneStringFastSearcherFactory.
 *
 * @author acc
 *
 */
public class LuceneStringFastSearcherFactory {

    private static final String FIELD_NAME = "id";

    private final Directory directory;
    private final IndexWriter indexWriter;

    /** Constructor for LuceneStringFastSearcherFactory. */
    public LuceneStringFastSearcherFactory() throws Exception {

        this.directory = new RAMDirectory();

        final CharArraySet voidStopWordsSet = new CharArraySet(Arrays.asList(), false);
        final StandardAnalyzer analyzer = new StandardAnalyzer(voidStopWordsSet);

        final IndexWriterConfig config = new IndexWriterConfig(analyzer);

        this.indexWriter = new IndexWriter(this.directory, config);
    }

    /**
     * Adds a list of Strings.
     *
     * @param listTxt
     *            listTxt
     * @throws Exception
     */
    public void addListString(final List<String> listTxt) throws Exception {

        for (final String txt : listTxt) {
            addString(txt);
        }
    }

    /**
     * Adds a String.
     *
     * @param txt
     *            txt
     * @throws Exception
     */
    public void addString(final String txt) throws Exception {

        final Document doc = new Document();
        doc.add(new StringField(FIELD_NAME, txt, Field.Store.YES));

        this.indexWriter.addDocument(doc);
        this.indexWriter.flush();
    }

    /**
     * Builds the StringFastSearcher.
     *
     * @return the StringFastSearcher
     */
    public LuceneStringFastSearcher build() throws Exception {

        this.indexWriter.flush();
        this.indexWriter.commit();

        return new LuceneStringFastSearcher(this.directory, FIELD_NAME);
    }

}
