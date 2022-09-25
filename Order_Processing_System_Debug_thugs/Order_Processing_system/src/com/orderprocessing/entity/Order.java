package com.orderprocessing.entity;

import java.util.Date;
import java.util.List;

import com.orderprocessing.util.OrderStatus;

public class Order {
	private int orderId;
	private Date orderDate;
	private int customerId;
	private String customerShippingAddress;
	private float orderValue;
	private float shippingCost;
	private String shippingAgency;
	private OrderStatus status;
	private List<Product> products;
	
	public Order() {
		this.orderId = -1;
		this.orderDate = null;
		this.customerId = -1;
		this.customerShippingAddress = null;
		this.orderValue = -1;
		this.shippingCost = -1;
		this.shippingAgency = null;
		this.status = null;
		this.products = null;
	}
	
	// Parameterized constructor -- without product list
	public Order(int orderId, Date orderDate, int customerId, String customerShippingAddress, float orderValue,
			float shippingCost, String shippingAgency, OrderStatus status) {
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.customerId = customerId;
		this.customerShippingAddress = customerShippingAddress;
		this.orderValue = orderValue;
		this.shippingCost = shippingCost;
		this.shippingAgency = shippingAgency;
		this.status = status;
		this.products = null;
	}

	// Parameterized Constructor - all fields
	public Order(int orderId, Date orderDate, int customerId, String customerShippingAddress,
			List<Product> products, float orderValue, float shippingCost, String shippingAgency, OrderStatus status) {
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.customerId = customerId;
		this.customerShippingAddress = customerShippingAddress;
		this.orderValue = orderValue;
		this.shippingCost = shippingCost;
		this.shippingAgency = shippingAgency;
		this.status = status;
		this.products = products;
	}

	/// Setter and Getter methods
	public int getOrderId() {
		return orderId;
	}
	
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	public Date getOrderDate() {
		return orderDate;
	}
	
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	public int getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	public String getCustomerShippingAddress() {
		return customerShippingAddress;
	}
	
	public void setCustomerShippingAddress(String customerShippingAddress) {
		this.customerShippingAddress = customerShippingAddress;
	}
	
	public float getOrderValue() {
		return orderValue;
	}
	
	public void setOrderValue(float orderValue) {
		this.orderValue = orderValue;
	}
	
	public float getShippingCost() {
		return shippingCost;
	}
	
	public void setShippingCost(float shippingCost) {
		this.shippingCost = shippingCost;
	}
	
	public String getShippingAgency() {
		return shippingAgency;
	}
	
	public void setShippingAgency(String shippingAgency) {
		this.shippingAgency = shippingAgency;
	}
	
	public OrderStatus getStatus() {
		return status;
	}
	
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	
	public List<Product> getProducts() {
		return products;
	}
	
	public void setProducts(List<Product> products) {
		this.products = products;
	}

	// toString() method
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderDate=" + orderDate + ", customerId=" + customerId
				+ ", customerShippingAddress=" + customerShippingAddress + ", orderValue=" + orderValue
				+ ", shippingCost=" + shippingCost + ", shippingAgency=" + shippingAgency + ", status=" + status
				+ ", products=" + products + "]";
	}
	
	
}
