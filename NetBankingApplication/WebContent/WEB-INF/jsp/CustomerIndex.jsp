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
	top: 120px;
	height: 70px;
	width: 50px;
}
</style>
</head>
<body>
	<c:if test="${message != null}">
		<script type="text/javascript">
			alert("<c:out value='${message}'/>");
			windows.location.reload();
		</script>
	</c:if>
	<div class="well">
		<font size="20"><marquee behavior="alternate">I2I NETBANKING</marquee></font>
	</div>
	<br />
	<br />
	<center>
		<h2>CUSTOMER INFORMATION</h2>
		<table>

			<tr>
				<td align="center" height="40" width="350"><a
					href="GetCustomer">FETCH CUSTOMER</a></td>
			</tr>
		</table>
	</center>
	<a href="approverIndexPage" style="font-sise: 18px">Go to main page</a>
	<div class="pos">
		<a class="btn btn-danger" href="logoutController"> LOGOUT</a>
	</div>
</body>
</html>