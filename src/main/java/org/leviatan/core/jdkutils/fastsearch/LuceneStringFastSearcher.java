package org.leviatan.core.jdkutils.fastsearch;

import java.util.Arrays;

import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;

import org.leviatan.core.jdkutils.log.AppLogger;

/**
 * LuceneStringFastSearcher.
 *
 * @author acc
 *
 */
public class LuceneStringFastSearcher implements StringFastSearcher {

    private final IndexSearcher searcher;
    private final String fieldName;

    private final StandardAnalyzer analyzer;

    /**
     * Constructor for LuceneStringFastSearcher.
     *
     * @param directory
     *            directory
     * @param fieldName
     *            fieldName
     */
    public LuceneStringFastSearcher(final Directory directory, final String fieldName) throws Exception {

        final IndexReader indexReader = DirectoryReader.open(directory);
        this.searcher = new IndexSearcher(indexReader);
        this.fieldName = fieldName;

        final CharArraySet stopWordsSet = new CharArraySet(Arrays.asList(), false);
        this.analyzer = new StandardAnalyzer(stopWordsSet);
    }

    @Override
    public boolean contains(final String txt) {

        try {
            return internalContains(txt);

        } catch (final Exception e) {
            AppLogger.logError("Error in LuceneStringFastSearcher.contains()", e);
        }

        return false;
    }

    private boolean internalContains(final String txt) throws Exception {

        final Query query = new QueryParser(this.fieldName, this.analyzer).parse(txt);

        final int hitsPerPage = 1;
        final TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage);
        this.searcher.search(query, collector);

        final TopDocs topDocs = collector.topDocs();
        final ScoreDoc[] hits = topDocs.scoreDocs;

        return hits.length > 0;
    }
}
