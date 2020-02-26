package com.robert.exception;

/**
 *
 * @author Roberto Armenta 
 * I made my own Exception to throw it when a validation
 * is not successful, catch it in the controller and send a generic response
 * json
 */
public class FunctionJSONException extends Exception {

    /**
     * Contructor of the exception
     * @param message String that will be shown when the exception is throwed
     */
    public FunctionJSONException(String message) {
        super(message);
    }
}
