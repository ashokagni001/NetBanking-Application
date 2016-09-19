<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${sessionScope['id']== null}">
	<c:redirect url="login" />
</c:if>
<c:if test="${sessionScope['role']!= 'approver'}">
	<c:redirect url="userHomePage" />
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

.pos {
	position: absolute;
	right: 100px;
	top: 108px;
	height: 70px;
	width: 50px;
}
</style>
</head>
<body>
	<div class="well">
		<font size="20"><marquee behavior="alternate">I2I NETBANKING</marquee></font>
	</div>
	<br />
	<br />
	<center>
		<h1>BANK MANAGEMENT</h1>
		<table>
			<tr>
				<td align="center" height="40" width="350"><a href="addBranch">
						ADD BRANCH</a></td>
			</tr>
			<tr>
				<td align="center" height="40" width="350"><a href="GetBranch">FETCH
						BRANCH</a></td>
			</tr>
			<tr>
				<td align="center" height="40" width="350"><a href="AddAccount">ADD
						ACCOUNT</a></td>
			</tr>
			<tr>
				<td align="center" height="40" width="350"><a
					href="ViewAccountByBranch">VIEW ACCOUNT BY BRANCH</a></td>
			</tr>
			<tr>
				<td align="center" height="40" width="350"><a
					href="getAllRole"> ADD USER ROLE</a></td>
			</tr>
		</table>
		<c:if test="${message != null}">
			<script type="text/javascript">
				alert("<c:out value='${message}'/>");
				windows.location.reload();
			</script>
		</c:if>
	</center>
	<a href="approverIndexPage">Go to main page</a>
	<div class="pos">
		<a class="btn btn-danger" href="logoutController"> LOGOUT</a>
	</div>
</body>
</html>
