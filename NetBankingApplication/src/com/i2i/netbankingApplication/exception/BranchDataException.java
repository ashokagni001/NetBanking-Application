package com.i2i.netbankingApplication.exception;

/**
 * <p>
 *     custom exception to check the branch attribute.
 *     If there is an error in the given data send message to class.
 * </p>
 * 
 * @author TEAM-2
 * 
 * @created 2016-09-06 
 */
public class BranchDataException extends Exception {
    private String message;

    public BranchDataException() {}

    /**
     * <p>
     *     parameter Constructor.
     *     Passes parameters to the constructor and creates an instance of BranchDataException.
     * </p>
     * 
     * @param message
     *     A String data type.
     */
    public BranchDataException(String message) {
        super(message);
    }

    /**
     * <p>
     *     parameter Constructor.
     *     Passes parameters to the constructor and creates an instance of BranchDataException.
     * </p>
     * 
     * @param message
     *     A String data type.
     * @param cause
     *     A String data type
     */
    public BranchDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public String toString() {
        return this.message;
    }
}
