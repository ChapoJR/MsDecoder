package com.robert.service;

import com.robert.enums.HashJSONKey;
import com.robert.exception.FunctionJSONException;
import com.robert.response.DifferencesResponse;
import com.robert.response.GenericResponse;

/**
 *
 * @author Roberto Armenta
 * @description Interface which declares the methods to realize the operations
 * with the encode JSON
 */
public interface OperationDecoderService {

    /**
     * This method store a JSON in the ConcurrentHashMap collection
     *
     * @param jsonId Defines the ID for the stored Hash Map
     * @param key Defines the side where the JSON will be stored
     * @param jsonStructure Defines the structure that will be stored
     * @return
     * @throws com.robert.exception.FunctionJSONException
     */
    public GenericResponse storeJSON(String jsonId, HashJSONKey key, String jsonStructure) throws FunctionJSONException;

    /**
     * This method realize the comparison between two JSON structures
     *
     * @param jsonStructureLeft Defines the left Structure that will be compared
     * @param jsonStructureRight Defines the right Structure that will be
     * compared
     * @return
     * @throws com.robert.exception.FunctionJSONException
     */
    public DifferencesResponse calculateDifferences(String jsonStructureLeft, String jsonStructureRight)
            throws FunctionJSONException;

    /**
     * This method goes to the collection and try to find the Map Object in base
     * of the jsonId to return the differences found it in the
     * calculateDifferences method
     *
     * @param jsonId Defines the ID to search in the collection and get the
     * differences
     * @return
     * @throws com.robert.exception.FunctionJSONException
     */
    public DifferencesResponse getJSONDifference(String jsonId) throws FunctionJSONException;
}
