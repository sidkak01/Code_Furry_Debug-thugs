<%@ page language="java" import="java.util.List,com.orderprocessing.controller.OrderController, com.orderprocessing.entity.Order, com.orderprocessing.util.OrderStatus" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <title>Employee Login</title>
    <link
      href="https://fonts.googleapis.com/css?family=Open+Sans"
      rel="stylesheet"
    />

    <link
      href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
      rel="stylesheet"
      integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
      crossorigin="anonymous"
    />
    <link rel="stylesheet" href="./customerlogin.css" />
    <script src="script.js"></script>
  </head>
  <body>
    <div id="mySidenav" class="sidenav">
      <a href="javascript:void(0)" class="closebtn" onclick="closeNav()"
        >&times;</a
      >
      <a href="#">About</a>
      <a href="#">Services</a>
      <a href="#">Clients</a>
      <a href="#">Contact</a>
    </div>

    <div>
      <button class="button1" role="button" onclick="openNav()">Menu</button>
    </div>
    <!-- partial:index.partial.html -->
    <div id="main">
      <div class="box-form">
        <div class="left">
          <div class="overlay">
            <h1>Orders</h1>
          </div>
        </div>

       

          <form>

            <body id="body-pd">
            	
                <div class="container">
                <div class="orders">Orders</div><br>
            
                       <div class="listnames">List Of Orders</div>
                       
                
                            <table rules="rows"class="table2">             
                                <thead>
                                    <tr>
                                        <th>Customer Id</th>
                                        <th>Date</th>
                                        <th>Customer Name</th>
                                        <th>Customer City</th>						
                                        <th>Order Value</th>						
                                        <th>Status</th>
                                        <!-- <th>Invoice Status</th> -->
                                    </tr>
                                </thead>
                                
                               <!--  -----------------fetching orders--------------------------- -->
                               
                              <%  List<Order> displayOrders = (List<Order>)request.getAttribute("allOrders");
                               	for(Order o:displayOrders){
                               		
                               %>
                               	
                
                                <tbody>
                                    <tr>
                                        <td ><%=o.getOrderId() %></td>
                                        <td><%=o.getOrderDate() %></td>
                                        
                                        <td><%=o.getCustomerShippingAddress() %></td>  
                                        <td><%=o.getOrderValue() %></td> 
                                        <td><%=o.getStatus() %></td>   
                                         <% if(o.getStatus()== OrderStatus.Approved || o.getStatus()==OrderStatus.Completed){%>
                                        	<td>
                                        		<form action="InvoiceController" method="post">
                                        			<input type="text" name="operation" value="custInvoice" hidden>
                                        			<input type="text" name="orderId" value="<%=o.getOrderId() %>" hidden>
                                        			<button class="approved" type="submit">Invoice</button>
                                        		</form>
                                        	</td>
                                       <% }%>
                                                     
                                        
                                    </tr>
                                    
                                    <%} %>
                    <!--   ----------------------end of fetching data------------------------- -->
                
                                </tbody> 
                                
                            </table>
                            <div class="button-align">
                            <a href="http://localhost:8080/Order_Processing_System/newquote.jsp">
                            <button class="button-order button-txt-order button-align"> 
                                                 
                                New Quote</button></a>
                            <a href="http://localhost:8080/Order_Processing_System/importproducts.jsp">
                            <button class="button-order button-txt-order button-align"> 
                                        
                                Import Products</button></a>
                            </div>
                        </div>             
  </body>
</html>
