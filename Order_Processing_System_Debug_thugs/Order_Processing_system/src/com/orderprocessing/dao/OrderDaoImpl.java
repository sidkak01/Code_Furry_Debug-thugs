package com.orderprocessing.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.orderprocessing.entity.Order;
import com.orderprocessing.entity.Product;
import com.orderprocessing.exception.OrderNotFoundException;
import com.orderprocessing.util.OrderStatus;

public class OrderDaoImpl implements OrderDao{
	private static Connection conn;
	private static PreparedStatement selectOrdersWithoutProdList, selectOrdersWithoutProdListByCustId, 
		selectQuotesWithoutProdListByCustId, insertQuote, insertOrderHasProducts, selectOrderById,
		selectOrderHasProducts, updateOrderStatus;
	
	static {
		conn = DBUtil.getConnection();
		try {
			selectOrdersWithoutProdList = conn.prepareStatement("SELECT * FROM orderinfo");
			selectOrdersWithoutProdListByCustId = conn.prepareStatement("SELECT * FROM orderinfo where customer_id=? AND status IN (?,?)");
			selectQuotesWithoutProdListByCustId = conn.prepareStatement("SELECT * FROM orderinfo where customer_id=? AND status=?");
			insertQuote = conn.prepareStatement("INSERT INTO orderinfo(order_date,customer_id,customer_shipping_address,total_order_value,shipping_cost,status) VALUES (?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			insertOrderHasProducts = conn.prepareStatement("INSERT INTO tbl_orderhasproducts VALUES(?,?,?)");
			selectOrderById = conn.prepareStatement("SELECT * FROM orderinfo WHERE order_id = ?");
			selectOrderHasProducts = conn.prepareStatement("SELECT * FROM tbl_orderhasproducts, productinfo where order_id = ? AND tbl_orderhasproducts.product_id = productinfo.product_id");
			updateOrderStatus = conn.prepareStatement("UPDATE orderinfo SET status=? WHERE order_id=?");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Get all orders without list of products
	@Override
	public List<Order> getAllOrdersWithoutProductList() throws SQLException {
		List<Order> orderList = new ArrayList<>();
		ResultSet rs = selectOrdersWithoutProdList.executeQuery();
		while(rs.next())
			orderList.add(new Order(rs.getInt(1), rs.getDate(2), rs.getInt(3), rs.getString(4), rs.getFloat(5), rs.getFloat(6), rs.getString(7), OrderStatus.valueOf(rs.getString(8))));
		return orderList;
	}
	
	// Get Orders list for customer, product list is null
	@Override
	public List<Order> getOrdersWithoutProductListByCustomerId(int id) throws SQLException{
		List<Order> orderList = new ArrayList<>();
		selectOrdersWithoutProdListByCustId.setInt(1, id);
		selectOrdersWithoutProdListByCustId.setString(2, OrderStatus.Approved.toString());
		selectOrdersWithoutProdListByCustId.setString(3, OrderStatus.Completed.toString());
		ResultSet rs = selectOrdersWithoutProdListByCustId.executeQuery();
		while(rs.next())
			orderList.add(new Order(rs.getInt(1), rs.getDate(2), rs.getInt(3), rs.getString(4), rs.getFloat(5), rs.getFloat(6), rs.getString(7), OrderStatus.valueOf(rs.getString(8))));
		return orderList;
	}
	
	// Get Quotes list for customer, product list is null
	@Override
	public List<Order> getQuotesWithoutProductListByCustomerId(int id) throws SQLException{
		List<Order> orderList = new ArrayList<>();
		selectQuotesWithoutProdListByCustId.setInt(1, id);
		selectQuotesWithoutProdListByCustId.setString(2, OrderStatus.Pending.toString());
		ResultSet rs = selectQuotesWithoutProdListByCustId.executeQuery();
		while(rs.next())
			orderList.add(new Order(rs.getInt(1), rs.getDate(2), rs.getInt(3), rs.getString(4), rs.getFloat(5), rs.getFloat(6), rs.getString(7), OrderStatus.valueOf(rs.getString(8))));
		return orderList;
	}
	
	// Insert new quote in database and return order_id
	@Override
	public int addQuote(Date order_date, int customer_id, String customer_shipping_address, float total_order_value,
			float shipping_cost) throws SQLException {
		// Convert java.util.Date to java.sql.Date
		java.sql.Date sqlDate = new java.sql.Date(order_date.getTime());
		
		insertQuote.setDate(1, sqlDate);
		insertQuote.setInt(2, customer_id);
		insertQuote.setString(3, customer_shipping_address);
		insertQuote.setFloat(4, total_order_value);
		insertQuote.setFloat(5, shipping_cost);
		insertQuote.setString(6, OrderStatus.Pending.toString());
		
		insertQuote.executeUpdate();
		
		ResultSet rs = insertQuote.getGeneratedKeys();
		if(rs.next())
			return rs.getInt(1);
		return -1;
	}
	
	// Insert into OrderHasProducts
	@Override
	public void addOrderHasProducts(Map<Integer, Integer> productMap,int orderId) throws SQLException {
		int i=0;
		for(Map.Entry<Integer, Integer> entry: productMap.entrySet()) {
			insertOrderHasProducts.setInt(1, orderId);
			insertOrderHasProducts.setInt(2, entry.getKey());
			insertOrderHasProducts.setInt(3, entry.getValue());
			insertOrderHasProducts.addBatch();
			i++;
			if(i%10 == 0 || i==productMap.size()) {
				insertOrderHasProducts.executeBatch();
			}
		}
	}

	@Override
	public Order getOrderByOrderId(int orderId) throws SQLException, OrderNotFoundException {
		selectOrderById.setInt(1, orderId);
		ResultSet rs = selectOrderById.executeQuery();
		if(rs.next()) 
			return new Order(orderId,rs.getDate(2),rs.getInt(3),rs.getString(4),rs.getFloat(5),rs.getFloat(6),rs.getString(7),OrderStatus.valueOf(rs.getString(8)));
		throw new OrderNotFoundException("Order not found");
	}

	@Override
	public Map<Product, Integer> getOrderHasProducts(int orderId) throws SQLException {
		selectOrderHasProducts.setInt(1, orderId);
		ResultSet rs = selectOrderHasProducts.executeQuery();
		Map<Product, Integer> productMap = new HashMap<>();
		while(rs.next())
			productMap.put(new Product(rs.getInt(4),rs.getString(5),rs.getFloat(6),rs.getInt(7),rs.getInt(8)), rs.getInt(3));
		return productMap;
	}

	// Approve order, set status to Approved
	@Override
	public void approveOrder(int orderId) throws SQLException {
		updateOrderStatus.setString(1, OrderStatus.Approved.toString());
		updateOrderStatus.setInt(2, orderId);
		updateOrderStatus.executeUpdate();
	}
	
	// order approval failed, set status to Expired
	@Override
	public void expireOrder(int orderId) throws SQLException {
		updateOrderStatus.setString(1, OrderStatus.Expired.toString());
		updateOrderStatus.setInt(2, orderId);
		updateOrderStatus.executeUpdate();
	}
}
