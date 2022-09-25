package com.orderprocessing.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.orderprocessing.entity.Employee;
import com.orderprocessing.exception.EmployeeNotFoundException;
import com.orderprocessing.service.EmployeeService;
import com.orderprocessing.service.EmployeeServiceImpl;

@WebServlet("/EmployeeController")
public class EmployeeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmployeeService employeeService = new EmployeeServiceImpl();
		Employee employee;
		
		int eid = Integer.parseInt(request.getParameter("ename"));
		String pass = request.getParameter("epswd");
		
		RequestDispatcher rd = null;
		try {
			employee = employeeService.loginEmployee(eid, pass);
			System.out.println("Login Successful");
			
			HttpSession session = request.getSession();
			if(!session.isNew()) {
				session.invalidate();
				session = request.getSession(true);
			}
			session.setAttribute("user", employee);
			session.setAttribute("user_type", "employee");
			
			//rd = request.getRequestDispatcher("employeeordermanagement.html");
			request.setAttribute("operation", "emporder");
			rd = request.getRequestDispatcher("OrderController");
			rd.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(EmployeeNotFoundException e) {
			rd = request.getRequestDispatcher("employeeLogin.jsp");
			rd.forward(request, response);
		}
		// TODO: forward employee obj to jsp page
	}

}
