package com.orderprocessing.exception;

public class CustomerNotFoundException extends Exception{
	public CustomerNotFoundException(String msg) {
		super(msg);
	}
	
	public CustomerNotFoundException(String msg, Throwable cause) {
		super(msg,cause);
	}
}
