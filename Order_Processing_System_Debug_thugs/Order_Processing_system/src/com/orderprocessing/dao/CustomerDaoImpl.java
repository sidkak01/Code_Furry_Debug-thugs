package com.orderprocessing.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.orderprocessing.entity.Customer;
import com.orderprocessing.entity.Order;
import com.orderprocessing.exception.CustomerNotFoundException;
import com.orderprocessing.util.OrderStatus;

public class CustomerDaoImpl implements CustomerDao{
	private static Connection conn;
	private static PreparedStatement selectCustById, selectCustByName, loginId, loginName;
	
	static {
		conn = DBUtil.getConnection();
		try {
			selectCustById = conn.prepareStatement("SELECT * FROM customerinfo WHERE customer_id=?");
			selectCustByName = conn.prepareStatement("SELECT * FROM customerinfo WHERE name=?");
			loginId = conn.prepareStatement("SELECT * FROM customerinfo WHERE customer_id=? AND password=?");
			loginName = conn.prepareStatement("SELECT * FROM customerinfo WHERE name=? AND password=?");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Get Customer by Id
	@Override
	public Customer getCustomerById(int id) throws SQLException, CustomerNotFoundException {
		selectCustById.setInt(1, id);
		ResultSet rs = selectCustById.executeQuery();
		if(rs.next())
			return new Customer(rs.getInt(1),rs.getString(2),rs.getString(4),rs.getString(5),rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));
		throw new CustomerNotFoundException("Customer not found or Invalid credentials");
	}
	
	// Get Customer by Name
	@Override
	public Customer getCustomerByName(String name) throws SQLException, CustomerNotFoundException {
		selectCustByName.setString(1, name);
		ResultSet rs = selectCustByName.executeQuery();
		if(rs.next())
			return new Customer(rs.getInt(1),rs.getString(2),rs.getString(4),rs.getString(5),rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));
		throw new CustomerNotFoundException("Customer not found or Invalid credentials");
	}
	
	// Check customer id and password, return customer object
	@Override
	public Customer loginUsingId(int id, String password) throws SQLException, CustomerNotFoundException {
		loginId.setInt(1, id);
		loginId.setString(2, password);
		ResultSet rs = loginId.executeQuery();
		if(rs.next())
			return new Customer(rs.getInt(1),rs.getString(2),rs.getString(4),rs.getString(5),rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));
		throw new CustomerNotFoundException("Customer not found or Invalid credentials");
	}
	
	// Check customer name and password, return customer object
	@Override
	public Customer loginUsingName(String name, String password) throws SQLException, CustomerNotFoundException {
		loginName.setString(1, name);
		loginName.setString(2, password);
		ResultSet rs = loginName.executeQuery();
		if(rs.next())
			return new Customer(rs.getInt(1),rs.getString(2),rs.getString(4),rs.getString(5),rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));
		throw new CustomerNotFoundException("Customer not found  or Invalid credentials");
	}
	
}
