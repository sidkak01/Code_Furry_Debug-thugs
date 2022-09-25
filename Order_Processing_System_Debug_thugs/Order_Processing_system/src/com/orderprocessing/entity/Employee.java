package com.orderprocessing.entity;

public class Employee {
	private int employeeId;
	private String employeeUsername;
	private String employeePassword;
	
	// Default Constructor
	public Employee(){
		this.employeeId = -1;
		this.employeeUsername = null;
		this.employeePassword = null;
	}

	// Parameterized constructor
	public Employee(int employeeId, String employeeUsername, String employeePassword) {
		this.employeeId = employeeId;
		this.employeeUsername = employeeUsername;
		this.employeePassword = employeePassword;
	}
	
	/// Setter and getter method
	public int getEmployeeId() {
		return employeeId;
	}
	
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	
	public String getEmployeeUsername() {
		return employeeUsername;
	}
	
	public void setEmployeeUsername(String employeeUsername) {
		this.employeeUsername = employeeUsername;
	}
	
	public String getEmployeePassword() {
		return employeePassword;
	}
	
	public void setEmployeePassword(String employeePassword) {
		this.employeePassword = employeePassword;
	}
	
}
