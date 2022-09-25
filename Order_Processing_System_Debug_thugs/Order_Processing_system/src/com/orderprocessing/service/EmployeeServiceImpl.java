package com.orderprocessing.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.orderprocessing.dao.EmployeeDao;
import com.orderprocessing.dao.EmployeeDaoImpl;
import com.orderprocessing.entity.Employee;
import com.orderprocessing.entity.Order;
import com.orderprocessing.exception.EmployeeNotFoundException;

public class EmployeeServiceImpl implements EmployeeService{
	private EmployeeDao employeeDao;
	
	// Default constructor
	public EmployeeServiceImpl() {
		employeeDao = new EmployeeDaoImpl();
	}
	
	@Override
	public Employee loginEmployee(int eid, String password) throws SQLException, EmployeeNotFoundException {
		return employeeDao.loginUsingId(eid, password);
	}
}
