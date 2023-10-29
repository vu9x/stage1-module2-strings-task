package com.epam.mjc;

import java.util.*;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        List<String> result = new ArrayList<String>();
        StringBuilder copy = new StringBuilder(source);
        Set<String> tokens = new HashSet<>(delimiters);
        String tmp = "";

        for (int i = 0; i < copy.length(); i++) {
            if(!tokens.contains(String.valueOf(copy.charAt(i)))){
                tmp +=copy.charAt(i);
            } else if (!tmp.isEmpty()){
                result.add(tmp);
                tmp = "";
            }
        }

        if(!tmp.isEmpty()) {
            result.add(tmp);
        }
        return result;
    }
}
