<%@page import="model.Ebill" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ebill Management</title>
</head>
<body>

	<div class="container"><div class="row"><div class="col-6">
	<h1>Ebills Management V10.1</h1>
	<form id="formEbill" name="formEbill">
 		Customer name: 
 		<input id="cname" name="cname" type="text" class="form-control form-control-sm">
 		<br> Unit: 
 		<input id="unit" name="unit" type="text" class="form-control form-control-sm">
 		<br>  price: 
 		<input id="price" name="price" type="text" class="form-control form-control-sm">
 		<br> Month: 
 		<input id="month" name="month" type="text" class="form-control form-control-sm">
 		<br> User Name:
 		<input id="username" name="username" type="text" class="form-control form-control-sm">
 		<br>Role:
 		<input id="role" name="role" type="text" class="form-control form-control-sm">
 		<br>
 		<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
 		<input type="hidden" id="hidEbillIDSave" name="hidEbillIDSave" value="">
	</form>
	
	<div id="alertSuccess" class="alert alert-success"></div>
	<div id="alertError" class="alert alert-danger"></div>
	
	<br>
	<div id="divEbillGrid">
	 <%
		 Ebill ebillObj = new Ebill(); 
 		out.print(ebillObj.getAllBills()); 
	  %>
	</div>
	</div> </div> </div> 
</body>
</html>