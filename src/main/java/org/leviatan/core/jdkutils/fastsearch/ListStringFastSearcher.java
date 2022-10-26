package org.leviatan.core.jdkutils.fastsearch;

import java.util.ArrayList;
import java.util.List;

/**
 * ListStringFastSearcher.
 *
 * @author acc
 *
 */
public class ListStringFastSearcher implements StringFastSearcher {

    private final List<String> listToSearch;

    /**
     * Constructor for ListStringFastSearcher.
     *
     * @param listTxt
     *            listTxt
     */
    public ListStringFastSearcher(final List<String> listTxt) {

        this.listToSearch = new ArrayList<String>(listTxt.size());
        this.listToSearch.addAll(listTxt);
    }

    @Override
    public boolean contains(final String txt) {
        return this.listToSearch.contains(txt);
    }
}
