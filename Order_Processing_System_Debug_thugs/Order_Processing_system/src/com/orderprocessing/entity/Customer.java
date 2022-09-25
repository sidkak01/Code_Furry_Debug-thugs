package com.orderprocessing.entity;

public class Customer {
	private int customerId;
	private String customerName;
	private String customerAddress;
	private String customerEmail;
	private String customerCity;
	private String customerGSTNumber;
	private String customerPincode;
	private String customerPhone;
	
	// Default constructor
	public Customer() {
		this.customerId = -1;
		this.customerName = null;
		this.customerAddress = null;
		this.customerEmail = null;
		this.customerCity = null;
		this.customerGSTNumber = null;
		this.customerPincode = null;
		this.customerPhone = null;
	}
	
	// Parameterized constructor
	public Customer(int id, String name, String address, String email, String city, String gst_number, String pincode, String phone) {
		this.customerId = id;
		this.customerName = name;
		this.customerAddress = address;
		this.customerEmail = email;
		this.customerCity = city;
		this.customerGSTNumber = gst_number;
		this.customerPincode = pincode;
		this.customerPhone = phone;
	}
	
	
	/// Setter and getter methods
	public int getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	public String getCustomerName() {
		return customerName;
	}
	
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public String getCustomerGSTNumber() {
		return customerGSTNumber;
	}
	
	public void setCustomerGSTNumber(String customerGSTNumber) {
		this.customerGSTNumber = customerGSTNumber;
	}
	
	public String getCustomerAddress() {
		return customerAddress;
	}
	
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	
	public String getCustomerCity() {
		return customerCity;
	}
	
	public void setCustomerCity(String customerCity) {
		this.customerCity = customerCity;
	}
	
	public String getCustomerEmail() {
		return customerEmail;
	}
	
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	
	public String getCustomerPhone() {
		return customerPhone;
	}
	
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	
	public String getCustomerPincode() {
		return customerPincode;
	}
	
	public void setCustomerPincode(String customerPincode) {
		this.customerPincode = customerPincode;
	}
	
}
