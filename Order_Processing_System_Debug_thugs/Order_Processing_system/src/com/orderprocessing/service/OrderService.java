package com.orderprocessing.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.orderprocessing.entity.Order;
import com.orderprocessing.entity.Product;
import com.orderprocessing.exception.OrderNotFoundException;

public interface OrderService {

	List<Order> fetchAllOrders() throws SQLException;

	List<Order> fetchOrdersByCustomerId(int customerId) throws SQLException;

	List<Order> fetchQuotesByCustomerId(int customerId) throws SQLException;

	Order getOrderById(int orderId) throws SQLException, OrderNotFoundException;

	Map<Product, Integer> getProducts(int orderId) throws SQLException;

	void approveOrder(int orderId) throws SQLException;

	int addQuote(Date order_date, int customer_id, String customer_shipping_address, float total_order_value,
			float shipping_cost) throws SQLException;

	void addOrderHasProducts(String products,int orderId) throws SQLException;

	void expireOrder(int orderId) throws SQLException;

}
