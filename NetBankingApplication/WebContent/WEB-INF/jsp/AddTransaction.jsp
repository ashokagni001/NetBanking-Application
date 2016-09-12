<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${sessionScope['id']== null}">
	<c:redirect url="login" />
</c:if>
<c:if test="${sessionScope['role']!= 'user'}">
	<c:redirect url="approverHomePage" />
</c:if>
<html>
<head>
<link rel="stylesheet" href="css/bootstrap.css">
<script src="js/bootstrap.js"></script>
<script src="js/bootstrap.min.js"></script>
<style type="text/css">
.well {
	background-color: #8ddfe1;
}
</style>
</head>
<body">
	<div class="well">
		<font size="20"><marquee behavior="alternate">NET
				BANKING</marquee></font>
	</div>
	<h2>INSERT TRANSACTION</h2>
	<center>
		<div class="container">
			<form method="post" action="insertTransaction">
				<div class="form-group">
					<label for="usr">Debit Account Number:</label> <input
						name="debitAccountNumber" placeholder="debitAccountNumber" /> </br>
				</div>
				<div class="form-group">
					<label for="usr">Cridit Account Number:</label> <input
						name="criditAccountNumber" placeholder="criditAccountNumber" /> </br>
				</div>
				<div class="form-group">
					<label for="usr">Cridit Account IFSC number :</label> <input
						name="ifscode" placeholder="ifscode" /> </br>
				</div>
				<div class="form-group">
					<label for="usr">Enter your Amoun:</label> <input name="amount"
						placeholder="Amount" /> </br>
				</div>
				<div class="form-group">
					<input type="submit" name="proceed" value="insertTransaction" />
				</div>

			</form>
		</div>
		<b>Go to main page </b><a href="userHomePage">click here</a> <br /> </br>
		<br /> <a href="logoutController" style="width: 300px;"> LOGOUT</a>
	</center>
</body>
</html>