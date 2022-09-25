package com.orderprocessing.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.orderprocessing.entity.Customer;
import com.orderprocessing.entity.Employee;
import com.orderprocessing.entity.Invoice;
import com.orderprocessing.exception.CustomerNotFoundException;
import com.orderprocessing.service.CustomerService;
import com.orderprocessing.service.CustomerServiceImpl;

@WebServlet("/CustomerController")
public class CustomerController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String operation = req.getParameter("operation");
		PrintWriter out = resp.getWriter();
		CustomerService customerService = new CustomerServiceImpl();
		RequestDispatcher rd = null;
		Customer customer = null;
		Employee employee = null;
		// Check session
		HttpSession session = req.getSession();
		String user_type = (String) session.getAttribute("user_type");
		if(user_type == null) {
			rd = req.getRequestDispatcher("main.jsp");
			rd.forward(req, resp);
		}
		else if (user_type.equals("customer")) {
			customer = (Customer) session.getAttribute("user");
			if(customer == null) {
				rd = req.getRequestDispatcher("main.jsp");
				rd.forward(req, resp);
			}
		}
		else if (user_type.equals("employee")) {
			employee = (Employee) session.getAttribute("user");
			if(employee == null) {
				rd = req.getRequestDispatcher("main.jsp");
				rd.forward(req, resp);
			}
		}
		
		if(operation.equals("getCust")) {
			String idOrName = req.getParameter("cust");
			try {
				customer = customerService.getCustomerByIdOrName(idOrName);
				Gson gson = new Gson();
				String jsonText = gson.toJson(customer);
				out.println(jsonText);
			} catch (SQLException | CustomerNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operation = request.getParameter("operation");
		System.out.println(operation);
		RequestDispatcher rd = null;
		HttpSession session = request.getSession();
		Customer customer = null;
		Employee employee = null;
		
		if(operation.equals("custlogin")) {
			CustomerService customerService = new CustomerServiceImpl();
			String cname = request.getParameter("cname");
			String pass = request.getParameter("cpswd");
			
			try {
				customer = customerService.loginCustomer(cname, pass);
				System.out.println("Login Successful");
				
				if(!session.isNew()) {
					session.invalidate();
					session = request.getSession(true);
				}
				session.setAttribute("user", customer);
				session.setAttribute("user_type", "customer");
				
				//rd = request.getRequestDispatcher("customerordermanagement.html");
				request.setAttribute("operation", "custorder");
				rd = request.getRequestDispatcher("OrderController");
				rd.forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch(CustomerNotFoundException e) {
				rd = request.getRequestDispatcher("customerlogin.jsp");
				rd.forward(request, response);
			}
		}
		else {
			// Check session
			String user_type = (String) session.getAttribute("user_type");
			if(user_type == null) {
				rd = request.getRequestDispatcher("main.jsp");
				rd.forward(request, response);
			}
			else if (user_type.equals("customer")) {
				customer = (Customer) session.getAttribute("user");
				if(customer == null) {
					rd = request.getRequestDispatcher("main.jsp");
					rd.forward(request, response);
				}
			}
			else if (user_type.equals("employee")) {
				employee = (Employee) session.getAttribute("user");
				if(employee == null) {
					rd = request.getRequestDispatcher("main.jsp");
					rd.forward(request, response);
				}
			}
		}
		if(operation.equals("custInvoice")) {
			CustomerService customerService = new CustomerServiceImpl();
			Invoice invoice = (Invoice)request.getAttribute("invoice");
			try {
				customer = customerService.getCustomerById(invoice.getCustomerId());
				request.setAttribute("operation", "custInvoice");
				request.setAttribute("customer", customer);
				rd = request.getRequestDispatcher("OrderController");
				rd.forward(request, response);
			} catch (SQLException | CustomerNotFoundException e) {
				e.printStackTrace();
			}
		}
		else if(operation.equals("approveOrder")) {
			request.setAttribute("operation", "approveOrder");
			rd = request.getRequestDispatcher("OrderController");
			rd.forward(request, response);
		}
	}

}
