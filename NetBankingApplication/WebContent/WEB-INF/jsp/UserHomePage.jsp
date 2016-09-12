<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${sessionScope['id']== null}">
	<c:redirect url="login" />
</c:if>
<c:if test="${sessionScope['role']!= 'user'}">
	<c:redirect url="ApproverHomePage" />
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
<body>
	<center>
		<h2>CUSTOMER INFORMATION</h2>
		<table>
			<br />
			<br />
			<tr>
				<td><a href="getCustomer?customerId=<c:out value="${sessionScope['id']}"/>">CUSTOMER DETAIL</a></td>
			</tr>
			<tr>
				<td><a href="viewMiniStatementByCustomerId?customerId=<c:out value="${sessionScope['id']}"/>">CUTOMER MINI
						STATEMENT</a></td>
			</tr>
			<tr>
				<td><a href="addTransaction">ADD TRANSACTION</a></td>
			</tr>
			<tr>
				<td><br /> <a href="logoutController" style="width: 300px;">
						LOGOUT</a></td>
			</tr>
		</table>
	</center>
</body>
</html>