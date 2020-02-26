package com.robert.rest;

import org.springframework.http.ResponseEntity;

import com.robert.request.StoreJSONRequest;

/**
 *
 * @author Roberto Armenta
 * Interface Class where are declared my methods for my decoder controller 
 */
public interface DecoderController {
    
        /**
         * @param jsonRequest This parametrer is the structure that will be stored
         * @param idRequest This parameter is the identifier which will be stored in the collection
         * @return This method return a generic response object
         */
	public ResponseEntity<Object> storeLeftSide(StoreJSONRequest jsonRequest, String idRequest);

        /**
         * @param jsonRequest This parametrer is the structure that will be stored
         * @param idRequest This parameter is the identifier which will be stored in the collection
         * @return This method return a generic response object
         */
	public ResponseEntity<Object> storeRightSide(StoreJSONRequest jsonRequest, String idRequest);

        /**
         * @param idRequest This parameter is the identifier which will be looked in the central collection
         * @return This method return a generic response object
         */
	public ResponseEntity<Object> getDifferences(String idRequest);
}
