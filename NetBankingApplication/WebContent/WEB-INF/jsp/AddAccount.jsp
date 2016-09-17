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
<style type="text/css">
.well {
	background-color: #8ddfe1;
}

.pos {
	position: absolute;
	right: 100px;
	top: 112px;
	height: 70px;
	width: 50px;
}
</style>
</head>
<body>
	<div class="well">
		<font size="20"> <marquee behavior="alternate">NETBANKING</marquee>
		</font>
	</div>
	<h2>INSERT ACCOUNT</h2>
	<center>
		<c:if test="${message != null}">
			<script type="text/javascript">
				alert("<c:out value='${message}'/>");
				windows.location.reload();
			</script>
		</c:if>
		<form method="post" action="addAccount">
			<input name="accountNumber" type="text"
				placeholder="Enter the accountNumber" /><br /> <input
				name="balance" type="text" placeholder="Enter the account balance" /><br />
			<b>Account Type</b></br> <select name="accounttype">
				<option value="Current">Current</option>
				<option value="Saving">Saving</option>
			</select> <br /> <input name="ifscode" type="text"
				placeholder="Enter the ifscode" /><br /> <input type="submit"
				name="addaccount" value="ADD" />
		</form>
	</center>
	<a href="BranchIndex">Go to main page</a>
	<div class="pos">
		<a class="btn btn-danger" href="logoutController"> LOGOUT</a>
	</div>
	</div>
</body>
</html>