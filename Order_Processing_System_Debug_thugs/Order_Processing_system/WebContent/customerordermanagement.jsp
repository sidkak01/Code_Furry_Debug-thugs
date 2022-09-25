<%@ page language="java" import="java.util.List,com.orderprocessing.controller.OrderController, com.orderprocessing.entity.Order, com.orderprocessing.util.OrderStatus" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <title>Customer order managment</title>
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
            <h1>Let's Add something</h1>
          </div>
        </div>

        <div class="right">
          <h5>Quote</h5>

          <form>

            <body id="body-pd">
  	
      <div class="container">
      <div class="orders">Orders</div>
    <br>
    <div class="listnames">List Of Quotes</div>
    <br>
             
      
                  <table rules="rows" class ="table2">
  			   <thead>
  			   <tr>
  			       <th>Order Id</th>
  			       <th>Date</th>
  			       <th>Order Value</th>
  			       <th>Shipping Cost</th>
  			       <th>Invoice</th>
  			   </tr>
  			   </thead>
                      
                     <!--  -----------------fetching quotes--------------------------- -->
                     
                    <%  List<Order> displayQuotes = (List<Order>)request.getAttribute("customerQuotes");
                     	for(Order q:displayQuotes){
                     		
                     %>
                     	
      
                      <tbody>
                          <tr>
                              <td ><%=q.getOrderId() %></td>
                              <td><%=q.getOrderDate() %></td>
                              <td><%=q.getOrderValue() %></td>  
                              <td><%=q.getShippingCost() %></td>    
                              <td>
                              	<form action="CustomerController" method="POST" id="app<%=q.getOrderId() %>">
                              			<input type="hidden" id="operation" name="operation" value="approveOrder">
                              			<input type="hidden" id="orderId" name="orderId" value="<%=q.getOrderId() %>">
                              		</form>
                              	<button class="approve" form="app<%=q.getOrderId() %>" value="submit">Approve</button>
                              </td> 
                                           
                              
                          </tr>
                          
                          <%} %>
          <!--   ----------------------end of fetching orders data------------------------- -->
      
                      </tbody> 
                      
        				</table>
                  
   <br>
                  
  <div class="listnames">List Of Orders</div>
  <br>
            		
            		<table rules="rows"class="table2">
  				   <thead>
  				   <tr >
  				       <th>Order Id</th>
  				       <th>Date</th>
  				       <th>Order Value</th>
  				       <th>Shipping Cost</th>
  				       <th>Status</th>
  				       <th>Invoice</th>
  				   </tr>
  				   </thead>
            
                     <!--  -----------------fetching orders--------------------------- -->
                     
                     <%  List<Order> displayOrders = (List<Order>)request.getAttribute("customerOrders");
                     	for(Order o:displayOrders){
                     		
                     %>
                     
                     <tbody>
                          <tr>
                              <td ><%=o.getOrderId() %></td>
                              <td><%=o.getOrderDate() %></td>
                              <td><%=o.getOrderValue() %></td>
                              <td><%=o.getShippingCost() %></td>  
                              <td><%=o.getStatus() %></td>   
                               <% if(o.getStatus()== OrderStatus.Approved){%>
                              	<td>
                              		<form action="InvoiceController" method="POST" id="inv<%=o.getOrderId() %>">
                              			<input type="hidden" id="operation" name="operation" value="custInvoice">
                              			<input type="hidden" id="orderId" name="orderId" value="<%=o.getOrderId() %>">
                              		</form>
                              		<button class="approved" form="inv<%=o.getOrderId() %>" value="submit">Show Invoice</button>
                              	</td>
                             <% }%>
                                           
                              
                          </tr>
                          
                          <%} %>
          <!--   ----------------------end of fetching data------------------------- -->
      
                      </tbody> 
            
                  
   </div>             
  </body>
</html>