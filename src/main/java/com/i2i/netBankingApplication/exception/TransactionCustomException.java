package com.i2i.netBankingApplication.exception;
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
public class TransactionCustomException extends Exception {  
    private String message;
    
    public TransactionCustomException() {
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
    public TransactionCustomException(String message) {
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
    public TransactionCustomException(String message, Throwable cause) {
        super (message, cause);
    }
     
    public String toString() { 
        return this.message;
    }
}  

