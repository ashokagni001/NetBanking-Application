package com.i2i.netbankingApplication.exception;

/**
 * <p>
 *     custom exception to check the customer attribute.
 *     If there is an error in the given data send message to class.
 * </p>
 * 
 * @author TEAM-2
 * 
 * @created 2016-09-06 
 */
public class CustomerDataException extends Exception {  
	private String message;
	
    public CustomerDataException() {
    }
    
    /**
	 * <p>
	 *     parameter Constructor.
     *     Passes parameters to the constructor and creates an instance of CustomerDataException.
     * </p>
     * 
	 * @param message
	 *     A String data type.
	 */
    public CustomerDataException(String message) {
        super(message);
    }
    
    /**
     * <p>
	 *     parameter Constructor.
     *     Passes parameters to the constructor and creates an instance of CustomerDataException.
     * </p>
     * 
     * @param message
     *     A String data type.
     * @param cause
     *     A String data type
     */
    public CustomerDataException(String message, Throwable cause) {
        super (message, cause);
    }
     
    public String toString() { 
        return this.message;
    }
}  
