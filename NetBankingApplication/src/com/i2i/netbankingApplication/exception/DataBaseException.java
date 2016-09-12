package com.i2i.netbankingApplication.exception;

public class DataBaseException extends Exception {
	private String message;

	public DataBaseException() {
	}

	public DataBaseException(String message) {
		super(message);
	}

	public DataBaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public String toString() {
		return this.message;
	}
}
