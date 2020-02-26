package com.robert.rest.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.robert.enums.HashJSONKey;
import com.robert.exception.FunctionJSONException;
import com.robert.request.StoreJSONRequest;
import com.robert.response.DifferencesResponse;
import com.robert.response.GenericResponse;
import com.robert.rest.DecoderController;
import com.robert.service.impl.OperationDecoderWaesImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Roberto Armenta
 * Implementation of the DecoderController Interface for WAES, the
 * API was documented with Swagger2
 * 
 */
@RestController
@RequestMapping("/v1/diff")
@Slf4j
@Api(value = "DecoderControllerWAES", description = "This API provides methos to compare two JSON String encoded in Base64 and show the differences betweem them")
public class DecoderControllerWaesImpl implements DecoderController {

	@Autowired
	OperationDecoderWaesImpl waesService;

	@PostMapping(path = "/{id}/left", consumes = "application/json", produces = "application/json")
	@ApiOperation(value = "/storeLeftSide")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = GenericResponse.class),
			@ApiResponse(code = 400, message = "BadRequest", response = GenericResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = GenericResponse.class) })
	@Override
	public ResponseEntity<Object> storeLeftSide(@RequestBody StoreJSONRequest jsonRequest,
			@ApiParam(value = "ID to store a new JSON encode", required = true) @PathVariable("id") String idRequest) {
		log.info("storeLeftJSON Request");
		log.info("jsonRequest:" + jsonRequest);
		log.info("idRequest:" + idRequest);
		try {
			return new ResponseEntity<>(waesService.storeJSON(idRequest, HashJSONKey.LEFT, jsonRequest.getJsonObject()),
					HttpStatus.OK);
		} catch (FunctionJSONException ex) {
			GenericResponse response = new GenericResponse(ex.getMessage());
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		} catch (Exception exAlt) {
			GenericResponse response = new GenericResponse(exAlt.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(path = "/{id}/right", consumes = "application/json", produces = "application/json")
	@ApiOperation(value = "/storeRightSide")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = GenericResponse.class),
			@ApiResponse(code = 400, message = "BadRequest", response = GenericResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = GenericResponse.class) })
	@Override
	public ResponseEntity<Object> storeRightSide(@RequestBody StoreJSONRequest jsonRequest,
			@ApiParam(value = "ID to store a new JSON encode", required = true) @PathVariable("id") String idRequest) {
		log.info("storeRightJSON Request:");
		log.info("jsonRequest:" + jsonRequest);
		log.info("idRequest:" + idRequest);
		try {
			return new ResponseEntity<>(
					waesService.storeJSON(idRequest, HashJSONKey.RIGHT, jsonRequest.getJsonObject()), HttpStatus.OK);
		} catch (FunctionJSONException ex) {
			GenericResponse response = new GenericResponse(ex.getMessage());
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		} catch (Exception exAlt) {
			GenericResponse response = new GenericResponse(exAlt.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(path = "/{id}", produces = "application/json")
	@ApiOperation(value = "/getDifferences")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = DifferencesResponse.class),
			@ApiResponse(code = 400, message = "BadRequest", response = GenericResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = GenericResponse.class) })
	@Override
	public ResponseEntity<Object> getDifferences(
			@ApiParam(value = "ID to find an stored comparation", required = true) @PathVariable("id") String idRequest) {
		try {
			return new ResponseEntity<>(waesService.getJSONDifference(idRequest), HttpStatus.OK);
		} catch (FunctionJSONException ex) {
			GenericResponse response = new GenericResponse(ex.getMessage());
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		} catch (Exception exAlt) {
			GenericResponse response = new GenericResponse(exAlt.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
