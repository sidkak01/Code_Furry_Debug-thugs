package com.orderprocessing.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orderprocessing.dao.CustomerDao;
import com.orderprocessing.dao.CustomerDaoImpl;
import com.orderprocessing.dao.OrderDao;
import com.orderprocessing.dao.OrderDaoImpl;
import com.orderprocessing.entity.Order;
import com.orderprocessing.entity.Product;
import com.orderprocessing.exception.OrderNotFoundException;

public class OrderServiceImpl implements OrderService{
	// Order Dao object
	private OrderDao orderDao;
	
	// Default constructor
	public OrderServiceImpl() {
		orderDao = new OrderDaoImpl();
	}
	@Override
	public List<Order> fetchAllOrders() throws SQLException {
		return orderDao.getAllOrdersWithoutProductList();
	}

	@Override
	public List<Order> fetchOrdersByCustomerId(int customerId) throws SQLException {
		return orderDao.getOrdersWithoutProductListByCustomerId(customerId);
	}

	@Override
	public List<Order> fetchQuotesByCustomerId(int customerId) throws SQLException {
		return orderDao.getQuotesWithoutProductListByCustomerId(customerId);
	}
	
	@Override
	public Order getOrderById(int orderId) throws SQLException, OrderNotFoundException {
		return orderDao.getOrderByOrderId(orderId);
	}
	
	@Override
	public Map<Product, Integer> getProducts(int orderId) throws SQLException {
		return orderDao.getOrderHasProducts(orderId);
	}
	
	@Override
	public void approveOrder(int orderId) throws SQLException {
		orderDao.approveOrder(orderId);
	}
	
	@Override
	public int addQuote(Date order_date, int customer_id, String customer_shipping_address, float total_order_value,
			float shipping_cost) throws SQLException {
				return orderDao.addQuote(order_date, customer_id, customer_shipping_address, total_order_value, shipping_cost);
		
	}
	
	@Override
	public void addOrderHasProducts(String products,int orderId) throws SQLException {
		Gson gson = new Gson();
		Type mapType = new TypeToken<Map<Integer,Integer>>(){}.getType();
		products = products.replaceAll("\\{","");
		products = products.replaceAll("\\}","");
		products = products.replaceAll("\\[","\\{");
		products = products.replaceAll("\\]","\\}");
		System.out.println(products);
		Map<Integer,Integer> prodmap = gson.fromJson(products, mapType);
		orderDao.addOrderHasProducts(prodmap,orderId);
	}
	
	@Override
	public void expireOrder(int orderId) throws SQLException {
		orderDao.expireOrder(orderId);
	}
}
