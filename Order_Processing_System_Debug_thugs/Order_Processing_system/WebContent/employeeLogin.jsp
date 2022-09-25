<%@ page language="java" session="false" import="java.util.List,com.orderprocessing.controller.EmployeeController" contentType="text/html; charset=ISO-8859-1"
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
            <h1 style="color:lightblue">Hi! Employee</h1>
          </div>
        </div>

        <div class="right">
          <h5>Login</h5>

      <div>
        <!-- Form for id and password -->

        <form action="EmployeeController" method="POST" id="empform">
            <div>
                <input class="input" type="text" placeholder="Employee Id" name="ename" required>
            </div>
            <br><br>
            <div>
                <input class="input" type="password" placeholder="Password" name="epswd" required>
            </div>
        </form>
    </div>

          <br /><br />

          <br />
          <div>
          <!-- Employee Login Button -->

          <button class="button-login button-txt" form="empform" value="submit">
                  Login</button>
          </div>
        </div>
      </div>
    </div>
    <!-- partial -->
  </body>
</html>

