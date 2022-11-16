package com.epam.mjc;

import java.util.*;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source     source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        List<String> list = new ArrayList<>();
        StringBuilder delimsRegax = new StringBuilder("[");
        for (String delimiter : delimiters) {
            delimsRegax.append(delimiter);
        }
        delimsRegax.append("]");
        String[] split = source.split(delimsRegax.toString());
        for (String s : split) {
            if (!s.isEmpty()) list.add(s);
        }
        return list;
    }
}
