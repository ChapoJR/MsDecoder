package com.robert.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.bind.DatatypeConverter;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.robert.enums.HashJSONKey;
import com.robert.exception.FunctionJSONException;
import com.robert.response.DifferenceResponse;
import com.robert.response.GenericResponse;
import com.robert.response.GetAllDifferenceResponse;
import com.robert.service.OperationDecoderService;
import com.robert.service.util.GenericFunction;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Roberto Armenta
 * @description Implementation of the OperationDecoderService for Waes, this
 *              implementation was made it with assumptions described in the
 *              readme.txt, also this class extends from GenericFunction class
 *              to use generic validation methods
 */
@Service
@Slf4j
public class OperationDecoderWaesImpl extends GenericFunction implements OperationDecoderService {

	/**
	 * Collection that will store all of the JSON structures, I used the Class
	 * ConcurrentHashMap because in this case we aren't implementing a data base or
	 * any other storage service, basically this class works exactly like a HashMap
	 * but the stored information remains in the run time
	 */
	private final ConcurrentHashMap<String, HashMap<HashJSONKey, Object>> jsonCentralCollection = new ConcurrentHashMap<>();

	@Override
	public GenericResponse storeJSON(String jsonId, HashJSONKey key, String jsonStructure)
			throws FunctionJSONException {
		/**
		 * Validation to check if the jsonId is empty or null
		 */
		if (!isNullOrEmpty(jsonId)) {
			/**
			 * Validation to check if the jsonStructure is empty or null
			 */
			if (!isNullOrEmpty(jsonStructure)) {
				/**
				 * Trying to convert into byte array the received jsonStructure *
				 */
				byte[] base64Decoded = DatatypeConverter.parseBase64Binary(jsonStructure);
				/**
				 * I check if the length is greater than zero, then the received JSON is a valid
				 * structure but else then I throw an exception
				 */
				if (base64Decoded.length > 0) {
					/**
					 * Here I try to found if I have stored a HashMap with that jsonId, if it
					 * doesn't exist then I create a new Instance of HashMap
					 */
					HashMap<HashJSONKey, Object> operationHashJSON = jsonCentralCollection.get(jsonId);
					if (operationHashJSON == null) {
						log.info("JsonId " + jsonId + " not found");
						log.info("Creating new HashMap Instance");
						operationHashJSON = new HashMap<>();
					}
					/**
					 * Now I transform that byte array to a Json String
					 */
					String decodedJsonString = new String(base64Decoded);
					/**
					 * I check if the side received in the key parameter is null or if this side
					 * already has the same jsonString structure
					 */
					if (operationHashJSON.get(key) == null || !operationHashJSON.get(key).equals(decodedJsonString)) {
						/**
						 * I store the jsonString in the selected position
						 */
						log.info("Saving in " + key.toString() + " in JsonId:" + jsonId);
						operationHashJSON.put(key, decodedJsonString);
						/**
						 * I check if the HashMap Id has in both sides a valid Json String structure
						 * then automatically I look for the differences
						 */
						if (operationHashJSON.get(HashJSONKey.LEFT) != null
								&& operationHashJSON.get(HashJSONKey.RIGHT) != null) {
							operationHashJSON.put(HashJSONKey.RESULT,
									calculateDifferences((String) operationHashJSON.get(HashJSONKey.LEFT),
											(String) operationHashJSON.get(HashJSONKey.RIGHT)));
						}
					} else {
						/**
						 * If the structure is already in that position, I skip the storage operation
						 */
						log.info("JSON Structure already in " + key.toString() + " side");
					}
					/**
					 * After all of this process I store the HasMap in my jsonCentralCollection
					 */
					jsonCentralCollection.put(jsonId, operationHashJSON);
					return new GenericResponse("JSON stored in the " + key.toString() + " side");
				} else {
					/**
					 * Exception throwed when the structure it's not valid
					 */
					throw new FunctionJSONException("Structure it's not decodable");
				}

			} else {
				/**
				 * Exception throwed when the structure it's empty
				 */
				throw new FunctionJSONException("The received JSON Structure is empty");
			}
		} else {
			/**
			 * Exception throwed when the jsonId is null or empty
			 */
			throw new FunctionJSONException("Empty jsonId now allowed");
		}
	}

	@Override
	public DifferenceResponse calculateDifferences(String jsonStructureLeft, String jsonStructureRight)
			throws FunctionJSONException {
		/**
		 * I instance my return model
		 */
		DifferenceResponse model = new DifferenceResponse();
		/**
		 * I evaluate if the structures are exactly the is obvious they will have the
		 * same length and it's not necessary look for another differences
		 */
		if (jsonStructureLeft.equals(jsonStructureRight)) {
			log.info("Length is equal, calculate differences is not necessary");
			model.setLength("Length is equal");
		} else {
			/**
			 * In case of the structures aren't equal I use the Google library GSON to look
			 * to transform the JSON String to a Map<String,Object> structure and find the
			 * differences
			 */
			log.info("Calculating differences");
			model.setLength("Length is not equal");
			log.info("LeftDecode:" + jsonStructureLeft);
			log.info("RightDecode:" + jsonStructureRight);
			Gson g = new Gson();
			Map<String, Object> leftMap = g.fromJson(jsonStructureLeft, Map.class);
			Map<String, Object> rightMap = g.fromJson(jsonStructureRight, Map.class);
			MapDifference<String, Object> resultDiference = Maps.difference(leftMap, rightMap);
			model.setLeftDifference(buildDifferenceComment(resultDiference.entriesOnlyOnLeft().entrySet()));
			model.setRightDifference(buildDifferenceComment(resultDiference.entriesOnlyOnRight().entrySet()));
			model.setValueDifference(buildDifferenceCommentValue(resultDiference.entriesDiffering().entrySet()));
		}
		/**
		 * Print in log to validate my model and return it
		 */
		log.info(model.toStringWithoutNull());
		return model;
	}

	@Override
	public DifferenceResponse getJSONDifference(String jsonId) throws FunctionJSONException {
		/**
		 * Same than another methods, I check if my inputs are valid
		 */
		if (!isNullOrEmpty(jsonId)) {
			HashMap<HashJSONKey, Object> operationHashJSON = jsonCentralCollection.get(jsonId);
			if (operationHashJSON != null) {
				if (operationHashJSON.get(HashJSONKey.LEFT) != null
						&& operationHashJSON.get(HashJSONKey.RIGHT) != null) {
					/**
					 * return the comparison previously calculated
					 */
					return (DifferenceResponse) operationHashJSON.get(HashJSONKey.RESULT);
				} else {
					/**
					 * Exception throwed when the HashMap exist but one of the sides it's still
					 * empty and it's not comparable
					 */
					throw new FunctionJSONException("Id:" + jsonId + " is not completed yet");
				}
			} else {
				/**
				 * Exception throwed when the jsonId don't exist yet
				 */
				throw new FunctionJSONException("Id:" + jsonId + " not found");
			}
		} else {
			/**
			 * Exception throwed when the jsonId is null or empty
			 */
			throw new FunctionJSONException("Empty JSONId now allowed");
		}
	}

	@Override
	public List<GetAllDifferenceResponse> getAllDifferences() throws FunctionJSONException {
		/**
		 * Same than another methods, I check if my inputs parametros are valid
		 */
		if (!jsonCentralCollection.isEmpty()) {
			List<GetAllDifferenceResponse> differencesList = new ArrayList<>();
			/**
			 * I iterate my central collection to find the stored differences
			 */
			jsonCentralCollection.forEach((id, value) -> {
				/**
				 * I create my response to the service and I check if it's already differenced
				 * or not
				 */
				GetAllDifferenceResponse allDifferenceItem = new GetAllDifferenceResponse();
				allDifferenceItem.setId(id);
				DifferenceResponse differenceResponseItem = (DifferenceResponse) value.get(HashJSONKey.RESULT);
				if (differenceResponseItem != null) {
					allDifferenceItem.setLength(differenceResponseItem.getLength());
					allDifferenceItem.setLeftDifference(differenceResponseItem.getLeftDifference());
					allDifferenceItem.setRightDifference(differenceResponseItem.getRightDifference());
					allDifferenceItem.setValueDifference(differenceResponseItem.getValueDifference());
				} else {
					allDifferenceItem.setMessage(value.get(HashJSONKey.LEFT) == null
							? "The collection has the " + HashJSONKey.LEFT.toString() + " side empty"
							: "The collection has the " + HashJSONKey.RIGHT.toString() + " side empty");
				}
				differencesList.add(allDifferenceItem);
			});
			return differencesList;
		} else {
			/**
			 * I iterate my central collection to find the stored differences
			 */
			throw new FunctionJSONException("Central Collection is empty");
		}
	}

}
