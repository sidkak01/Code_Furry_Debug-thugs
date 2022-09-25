package com.orderprocessing.exception;

public class EmployeeNotFoundException extends Exception{
	public EmployeeNotFoundException(String msg) {
		super(msg);
	}
	
	public EmployeeNotFoundException(String msg, Throwable cause) {
		super(msg,cause);
	}
}
