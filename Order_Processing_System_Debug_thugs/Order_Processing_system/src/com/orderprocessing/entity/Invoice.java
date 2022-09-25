package com.orderprocessing.entity;

import java.util.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import com.orderprocessing.util.GSTType;
import com.orderprocessing.util.InvoiceStatus;

public class Invoice {
	private int invoiceId;
	private Date invoiceDate;
	private int orderId;
	private int customerId;
	private Map<Product, Integer> products;
	private float totalGST;
	private GSTType typeOfGST;
	private float totalInvoiceValue;
	private InvoiceStatus status;
	
	// Default constructor
	public Invoice() {
		this.products = null;
	}
	
	// Parameterized Constructor - without products map, totalGST
	public Invoice(int invoiceId, Date invoiceDate, int orderId, int customerId, GSTType typeOfGST, 
			float totalInvoiceValue, InvoiceStatus status) {
		this.invoiceId = invoiceId;
		this.invoiceDate = invoiceDate;
		this.orderId = orderId;
		this.customerId = customerId;
		this.typeOfGST = typeOfGST;
		this.totalInvoiceValue = totalInvoiceValue;
		this.status = status;
	}

	/// Setter and Getter methods
	public int getInvoiceId() {
		return invoiceId;
	}
	
	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}
	
	public Date getInvoiceDate() {
		return invoiceDate;
	}
	
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	
	public int getOrderId() {
		return orderId;
	}
	
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	public int getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	public Map<Product, Integer> getProducts() {
		return products;
	}

	public void setProducts(Map<Product, Integer> products) {
		this.products = products;
	}

	public float getTotalGST() {
		return totalGST;
	}
	
	public void setTotalGST(float totalGST) {
		this.totalGST = totalGST;
	}
	
	public GSTType getTypeOfGST() {
		return typeOfGST;
	}
	
	public void setTypeOfGST(GSTType typeOfGST) {
		this.typeOfGST = typeOfGST;
	}
	
	public float getTotalInvoiceValue() {
		return totalInvoiceValue;
	}
	
	public void setTotalInvoiceValue(float totalInvoiceValue) {
		this.totalInvoiceValue = totalInvoiceValue;
	}
	
	public InvoiceStatus getStatus() {
		return status;
	}
	
	public void setStatus(InvoiceStatus status) {
		this.status = status;
	}
	
	
}
