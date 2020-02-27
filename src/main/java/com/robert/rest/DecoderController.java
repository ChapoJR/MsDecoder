package com.robert.rest;

import org.springframework.http.ResponseEntity;

import com.robert.request.StoreJSONRequest;

/**
 *
 * @author Roberto Armenta Interface Class where are declared my methods for my
 *         decoder controller
 */
public interface DecoderController {

	/**
	 * This method store the JSON Structure in the left side in the object defined by the idRequest
	 * @param jsonRequest This parameter is the structure that will be stored
	 * @param idRequest   This parameter is the identifier which will be stored in
	 *                    the collection
	 * @return This method return a generic response object
	 */
	public ResponseEntity<Object> storeLeftSide(StoreJSONRequest jsonRequest, String idRequest);

	/**
	 * This method store the JSON Structure in the right side in the object defined by the idRequest
	 * @param jsonRequest This parameter is the structure that will be stored
	 * @param idRequest   This parameter is the identifier which will be stored in
	 *                    the collection
	 * @return This method return a generic response object
	 */
	public ResponseEntity<Object> storeRightSide(StoreJSONRequest jsonRequest, String idRequest);

	/**
	 * This method return the differences by Id
	 * @param idRequest This parameter is the identifier which will be looked in the
	 *                  central collection
	 * @return This method return a generic response object
	 */
	public ResponseEntity<Object> getDifferences(String idRequest);

	/**
	 * This method return all of the difference collection
	 */
	public ResponseEntity<Object> getAllDifferences();
}
