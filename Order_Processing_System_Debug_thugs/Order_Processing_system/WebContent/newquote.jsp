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
            <h1>Let's Add something</h1>
          </div>
        </div>

        <div class="right">
          <h5>Quote</h5>

          <form>

<div class="maindiv">


<label for="name">Customer Name/ID</label>
<input type="text" id="name" name="name" onblur="getCustomerDetails()" required>
<label for="gstno">Customer GST NO</label>
<input type="number" id="gstno" name="gstno" required disabled><br><br>



<label for="email">Customer Email</label>
<input type="email" id="email" name="email" required disabled>
<label for="shippingaddress">Shipping Address</label>
<input type="text" id="shippingaddress" name="shippingaddress" required disabled><br><br>


<label for="pincode">Customer Pincode</label>
<input type="number" id="pincode" name="pincode" required disabled>
<label for="phoneno">Customer Phone No</label>
<input type="number" id="phoneno" name="phoneno" required disabled><br>




<label for="orderdate">Order Date</label>
<input type="Date" id="orderdate" name="orderdate" required>

<button class="proceed" onclick="showProducts()" type="button">Proceed</button><br><br>




<div id="div1" style="display:none">
<label for="addproducts">Add Products</label><br>

<div class="tableFixHead">


   <table rules="rows" id="prodTable">
     <thead>
        <th>Product Name</th>
        <th>Product Name</th>
        <th>Price</th>
        <th>Quantity</th>
        <th>Add Product</th>
       </tr>
     </thead>
     <tbody>

      </tbody>
   </table>
 </div>

 <br><br>
<span id="span" style="color:red"></span>
<br>
<label for="removeproducts">Cart</label><br>
 <div class="tableFixHead">


    <table rules="rows" id="mycart">
     <thead>
        <th>Product Name</th>
        <th>Product Name</th>
        <th>Price</th>
        <th>Quantity</th>
        <th>Remove Product</th>
       </tr>
     </thead>
     <tbody>

      </tbody>
   </table>


 </div>
 <br><br>




<label for="shippingaddress">Shipping Cost</label>
<input type="number" id="shippingcost" name="shippingcost" disabled>
<label for="totalordervalue">Total Order Value</label>
<input type="number" id="totalordervalue" name="totalordervalue" disabled><br>
</div>
</div>





<input id="quotebutton1" type="submit" value="Request a Quote" onclick="submitData()" style="display:none">


</form>
<script type="text/javascript">
 var cart=[];
 var custId;
 var custAddress;
 var shippingCost = 0;
 var orderValue = 0;
 function getCustomerDetails() {
   let idOrName = document.getElementById("name").value;
   if(idOrName == "") {
     alert("Enter Name");
   }
   else {
     let xhttp = new XMLHttpRequest();
     xhttp.onload = function() {
       console.log(JSON.parse(xhttp.responseText));
       let customer = JSON.parse(xhttp.responseText);
       document.getElementById("gstno").value = customer["customerGSTNumber"];
       document.getElementById("email").value = customer["customerEmail"];
       document.getElementById("shippingaddress").value = customer["customerAddress"];
       document.getElementById("pincode").value = customer["customerPincode"];
       document.getElementById("phoneno").value = customer["customerPhone"];
       custId = customer["customerId"];
       custAddress = customer["customerAddress"];
       n =  new Date();
       y = n.getFullYear();
       m = n.getMonth() + 1;
       d = n.getDate();
       if(m < 10) {
         if(d < 10)
           document.getElementById("orderdate").value = y + "-0" + m + "-0" + d;
         else
           document.getElementById("orderdate").value = y + "-0" + m + "-" + d;
       } else {
         if(d<10)
           document.getElementById("orderdate").value = y + "-" + m + "-0" + d;
         else
           document.getElementById("orderdate").value = y + "-" + m + "-" + d;
       }
     }
     xhttp.open("GET","http://localhost:8080/Order_Processing_System/CustomerController?operation=getCust&cust="+idOrName);
     xhttp.send(); // This method will send request to a server
   }
 }

 function showProducts() {
   let xhttp = new XMLHttpRequest();
   xhttp.onload = function() {
     //alert("Hello");
     document.getElementById("name").disabled = true;
     console.log(JSON.parse(xhttp.responseText));
     jsonstring = JSON.parse(xhttp.responseText);
     document.getElementById("div1").style.display = "block";
     document.getElementById("quotebutton1").style.display = "block";

     let table = document.getElementById("prodTable");
     for(let i = 0 ; i < jsonstring.length ; i++) {
       let obj = jsonstring[i];
       let row = table.insertRow(-1);

       let cell1 = row.insertCell(0);
       cell1.innerHTML = obj.productId;

       let cell2 = row.insertCell(1);
       cell2.setAttribute("id","name"+obj.productId);
       cell2.innerHTML = obj.productName;

       let cell3 = row.insertCell(2);
       cell3.setAttribute("id","price"+obj.productId);
       cell3.innerHTML = obj.productPrice;

       let cell4 = row.insertCell(3);
       let form = document.createElement("form");
       form.setAttribute("id","form"+obj.productId);

       let quant = document.createElement("input");
       quant.setAttribute("type","number");
       quant.setAttribute("id","quant"+obj.productId);
       form.appendChild(quant);

       let category = document.createElement("input");
       category.setAttribute("type","hidden");
       category.setAttribute("id","cat"+obj.productId);
       category.setAttribute("value",obj.productCategory);
       form.appendChild(category);
       cell4.appendChild(form);

       let cell5 = row.insertCell(4);
       let button = document.createElement("button");
       button.innerHTML = "Add";
       button.setAttribute("id",obj.productId);
       button.setAttribute("onclick","addProduct(this)");
       button.setAttribute("class","addproduct");
       button.setAttribute("type","button");
       cell5.appendChild(button);
     }
   }
   xhttp.open("GET","http://localhost:8080/Order_Processing_System/ProductController?operation=getCustProducts&cust="+custId);
   xhttp.send(); // This method will send request to a server
 }

 function addProduct(btn) {
   let prodId = btn.id;
   console.log(prodId);
   if(findProductById(prodId) == 1) {
     //console.log("Product already exists!!");
     document.getElementById("span").innerHTML = "Product already added. Please delete existing entry to reset quantity";
   }
   else {
     document.getElementById("span").innerHTML = "";
     let quantity = document.getElementById("quant"+prodId).value;
     let prodName = document.getElementById("name"+prodId).innerHTML;
     let price = document.getElementById("price"+prodId).innerHTML;
     let category = document.getElementById("cat"+prodId).value;

     let table = document.getElementById("mycart");
     //alert(table);
     let row = table.insertRow(-1);

     let cell1 = row.insertCell(0);
     cell1.innerHTML = prodId;
     let cell2 = row.insertCell(1);
     cell2.innerHTML = prodName;
     let cell3 = row.insertCell(2);
     cell3.innerHTML = price;
     let cell4 = row.insertCell(3);
     cell4.setAttribute("id","quantity"+prodId);
     cell4.innerHTML = quantity;

     let cell5 = row.insertCell(4);
     let button = document.createElement("button");
     button.innerHTML = "Delete";
     button.setAttribute("id",prodId);
     button.setAttribute("onclick","deleteProduct(this,"+category+","+quantity+","+price+")");
     button.setAttribute("class","removeproduct");
     button.setAttribute("type","button");
     cell5.appendChild(button);

     calculateTotalOrderValue(quantity,price);
     calculateShippingCost(quantity,category,price);
     document.getElementById("shippingcost").value = shippingCost;
     document.getElementById("totalordervalue").value = orderValue;

     cart.push({[prodId]:parseInt(quantity)});
     console.log(JSON.stringify(cart));
   }
 }

 function deleteProduct(btn,category,quantity,price) {
   let prodId = btn.id;

   let i = btn.parentNode.parentNode.rowIndex;
   document.getElementById("mycart").deleteRow(i);
   for(let i = 0 ; i < cart.length ; i++) {
     let obj = cart[i];
     if(Object.keys(obj)[0]==prodId) {
       cart.splice(i,1);
       break;
     }
     console.log(JSON.stringify(cart));
   }
   reduceTotalOrderValue(quantity,price);
   reduceShippingCost(quantity,category,price);
   document.getElementById("shippingcost").value = shippingCost;
   document.getElementById("totalordervalue").value = orderValue;
 }

 function findProductById(id) {
   let flag = 0;
   for(let i = 0 ; i < cart.length ; i++) {
     let obj = cart[i];
     if(Object.keys(obj)[0] == id) {
       flag = 1;
       break;
     }
   }
   return flag;
 }

 function calculateShippingCost(quantity,category,price) {
   if(orderValue > 100000) {
     shippingCost = 0;
   }else {
     let percent = 0;
     if(category == 1)
       percent = 5;
     else if(category == 2)
       percent = 3;
     else if(category == 3)
       percent = 2;
     shippingCost = shippingCost + ((quantity*price*percent)/100);
     orderValue = orderValue + ((quantity*price*percent)/100);
   }
 }

 function calculateTotalOrderValue(quantity,price) {
   orderValue = orderValue + (1.1 * quantity * price);
 }

 function reduceShippingCost(quantity,category,price) {
   if(orderValue > 100000) {
     shippingCost = 0;
   }else {
     let percent = 0;
     if(category == 1)
       percent = 5;
     else if(category == 2)
       percent = 3;
     else if(category == 3)
       percent = 2;
     shippingCost = shippingCost - ((quantity*price*percent)/100);
     orderValue = orderValue - ((quantity*price*percent)/100);
   }
 }

 function reduceTotalOrderValue(quantity,price) {
   orderValue = orderValue - (1.1 * quantity * price);
 }

 function submitData() {
   n =  new Date();
   y = n.getFullYear();
   m = n.getMonth() + 1;
   d = n.getDate();
   if(m < 10) {
     if(d < 10)
       date = y + "-0" + m + "-0" + d;
     else
       date = y + "-0" + m + "-" + d;
   } else {
     if(d<10)
       date = y + "-" + m + "-0" + d;
     else
       date = y + "-" + m + "-" + d;
   }

   var params = 'custId='+custId+'&products='+JSON.stringify(cart)+'&';
   params = params+'custAddress='+custAddress+'&';
   params = params+'shipCost='+shippingCost+'&';
   params = params+'totalValue='+orderValue+'&';
   params = params+'operation=addQuote&date='+date;
   var xhttp = new XMLHttpRequest();
   xhttp.open("post", "http://localhost:8080/Order_Processing_System/OrderController",true);
   xhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
       xhttp.send(params);
       xhttp.onload = function() {
         console.log("Hello");
         location.reload();
       }
 }
</script>
  </body>
</html>
