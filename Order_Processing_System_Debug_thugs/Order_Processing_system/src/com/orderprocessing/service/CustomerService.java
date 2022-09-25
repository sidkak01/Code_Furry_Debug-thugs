package com.orderprocessing.service;

import java.sql.SQLException;
import java.util.List;

import com.orderprocessing.entity.Customer;
import com.orderprocessing.entity.Order;
import com.orderprocessing.exception.CustomerNotFoundException;

public interface CustomerService {
	Customer loginCustomer(String name_or_id, String password) throws SQLException, CustomerNotFoundException;

	Customer getCustomerById(int id) throws SQLException, CustomerNotFoundException;

	Customer getCustomerByIdOrName(String idOrName) throws SQLException, CustomerNotFoundException;
}
