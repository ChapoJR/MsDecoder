package com.robert.service.util;

import java.util.Set;
import java.util.Map.Entry;

import com.google.common.collect.MapDifference.ValueDifference;

/**
 *
 * @author Roberto Armenta Class with generic functions that can be used just
 * for the classes which extends from this class
 */
public class GenericFunction {

    /**
     * Method to check if an String is null or empty
     *
     * @param str String that will be evaluated
     * @return this method return a boolean value 
     */
    protected boolean isNullOrEmpty(String str) {
        return !(str != null && !str.trim().isEmpty());
    }

    /**
     * Method to iterate a Entry Set Collection and generate a String with the
     * Keys and Values
     *
     * @param entrySet Collection that will be iterable
     * @return This method return a concatenated string with the key and values of the entry collection
     */
    protected String buildDifferenceComment(Set<Entry<String, Object>> entrySet) {
        String differenceComment = "";
        for (Entry<String, Object> entryIt : entrySet) {
            differenceComment += "," + entryIt.getKey() + " = " + entryIt.getValue();
        }
        return isNullOrEmpty(differenceComment) ? null : differenceComment.substring(1);
    }

    /**
     * Method to iterate a Entry Set Collection and generate a String with the
     * Keys and Values but this just Iterate a Special class from Gson Library
     * (ValueDifference)
     *
     * @param entrySetValue Collection that will be iterable
     * @return This method return a concatenated string with the key and values of the entry collection
     */
    protected String buildDifferenceCommentValue(Set<Entry<String, ValueDifference<Object>>> entrySetValue) {
        String differenceComment = "";
        for (Entry<String, ValueDifference<Object>> entryIt : entrySetValue) {
            differenceComment += "," + entryIt.getKey() + " = " + entryIt.getValue();
        }
        return isNullOrEmpty(differenceComment) ? null : differenceComment.substring(1);
    }
}
