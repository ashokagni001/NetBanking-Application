<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${sessionScope['id']== null}">
	<c:redirect url="login" />
</c:if>
<c:if test="${sessionScope['role']!= 'user'}">
	<c:redirect url="approverIndexPage" />
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
					<label for="usr">Credit Account Number:</label> <input
						name="creditAccountNumber" placeholder="creditAccountNumber" /> </br>
				</div>
				<div class="form-group">
					<label for="usr">Credit Account IFSC number :</label> <input
						name="ifscode" placeholder="ifscode" /> </br>
				</div>
				<div class="form-group">
					<label for="usr">Enter your Amount:</label> <input name="amount"
						placeholder="Amount" /> </br>
				</div>
				<div class="form-group">
					<input type="submit" name="insertTransaction" value="insertTransaction" />
				</div>
				<input type="hidden" name="customerId" value=<c:out value="${sessionScope['id']}"/> />
			</form>
		</div>
		<a href="userHomePage">Go to main page </a> <br /> </br>
		<br /> <a href="logoutController" style="width: 300px;"> LOGOUT</a>
	</center>
</body>
</html>