
// Company Table structure creation


CREATE TABLE companyinfo (
  company_id int NOT NULL AUTO_INCREMENT,
  company_name varchar(255) DEFAULT NULL,
  company_address varchar(255) DEFAULT NULL,
  city varchar(255) DEFAULT NULL,
  gst_number varchar(255) DEFAULT NULL,
  PRIMARY KEY (company_id)
) ;

INSERT INTO `companyinfo` VALUES (1,'HSBC','Business Bay, Pune','Pune','101');


// CustomerInformation Table structure creation

CREATE TABLE customeinfo (
  customer_id int NOT NULL AUTO_INCREMENT,
  name varchar(255) NOT NULL,
  password varchar(255) NOT NULL,
  address varchar(255) DEFAULT NULL,
  email varchar(255) NOT NULL,
  city varchar(255) DEFAULT NULL,
  gst_number varchar(255) DEFAULT NULL,
  pincode bigint DEFAULT NULL,
  mobile_number varchar(50) DEFAULT NULL,
  PRIMARY KEY (customer_id)
);

INSERT INTO customerinfo VALUES (1,'Siddharth kakade','siddharth','HSBC Pune','siddharth@02.com','maharashtr','20',412102,'25343');

// EmployeeInformation Table structure creation

CREATE TABLE employeeinfo (
  employee_id int NOT NULL AUTO_INCREMENT,
  employee_name varchar(255) NOT NULL,
  password varchar(255) NOT NULL,
  PRIMARY KEY (employee_id)
) ;

INSERT INTO `tbl_employee` VALUES (1,'Akshada Shelar','akshada'),(2,'Siddhant Touti','siddhant'),(3,'Mayur Naik','mayur');


// InvoiceInformation Table structure creation


CREATE TABLE invoiceinfo (
  invoice_id int NOT NULL AUTO_INCREMENT,
  invoice_date date DEFAULT NULL,
  order_id int DEFAULT NULL,
  customer_id int DEFAULT NULL,
  gst_type enum('INTER_STATE','SAME_STATE') DEFAULT NULL,
  total_value decimal(10,0) DEFAULT NULL,
  status enum('Paid','Unpaid') DEFAULT NULL,
  PRIMARY KEY (invoice_id),
  KEY order_id (order_id),
  KEY customer_id (customer_id),
  FOREIGN KEY (order_id) REFERENCES orderinfo (order_id),
  FOREIGN KEY (customer_id) REFERENCES customerinfo (customer_id)
);


// InvoiceInformation Table structure creation

CREATE TABLE orderinfo (
  order_id int NOT NULL AUTO_INCREMENT,
  order_date date DEFAULT NULL,
  customer_id int DEFAULT NULL,
  customer_shipping_address varchar(255) DEFAULT NULL,
  total_order_value decimal(10,0) DEFAULT NULL,
  shipping_cost decimal(10,0) DEFAULT NULL,
  shipping_agency varchar(255) DEFAULT NULL,
  status enum('Pending','Approved','Completed','Expired') DEFAULT NULL,
  PRIMARY KEY (order_id),
  KEY customer_id (customer_id),
  FOREIGN KEY (`customer_id`) REFERENCES customerinfo (customer_id)
);



INSERT INTO orderinfo VALUES (1,'2021-09-21',1,'csd 1',100,10,'bd','Completed'),(2,'2021-09-02',2,'csd 2',250,25,'bd','Completed'),(3,'2021-09-21',1,'csa 3',300,30,'bd','Pending'),(4,'2021-09-21',2,'csa 4',400,450,'bd','Pending'),(5,'2021-09-21',1,'csa 5',500,50,'bd','Approved'),(6,'2021-09-21',2,'csa 6',600,60,'bd','Completed');



// ProductInformation Table structure creation

CREATE TABLE productinfo (
  product_id int NOT NULL AUTO_INCREMENT,
  name varchar(255) DEFAULT NULL,
  price decimal(10,0) DEFAULT NULL,
  category int DEFAULT NULL,
  company_id int DEFAULT NULL,
  PRIMARY KEY (product_id),
  KEY company_id (company_id),
  FOREIGN KEY (company_id) REFERENCES companyinfo (company_id)
);
INSERT INTO productinfo VALUES (1,'Headphones',100,1,3),(2,'iPhone',1000,2,2),(3,'iPad',600,2,2),(4,'Debit Card',5,3,1),(5,'Credit Card',5,3,1),(6,'Earbuds',150,1,3);
