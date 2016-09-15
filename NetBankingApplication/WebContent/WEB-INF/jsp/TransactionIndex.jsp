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
		<font size="20"><marquee behavior="alternate">NET
				BANKING</marquee></font>
	</div>
	<br />
	<center>
	<h2>CUSTOMER INFORMATION</h2>
	<table>
		<tr>
			<td align="center" height="40" width="350"><a
				href="notification">NOTIFICATIONS</a></td>
		</tr>
		<tr>
			<td align="center" height="40" width="350"><a
				href="viewAllTransaction">FETCH ALL TRANSACTION</a></td>
		</tr>
		<tr>
			<td align="center" height="40" width="350"><a
				href="viewTransactionByDate">FETCH TRANSACTION BY DATE</a></td>
		</tr>
	</table>
	</center>
	<a href="approverIndexPage" style="font-sise: 18px">Go to main page</a>
	<div class="pos">
		<a class="btn btn-danger" href="logoutController"> LOGOUT</a>
	</div>
</body>
</html>