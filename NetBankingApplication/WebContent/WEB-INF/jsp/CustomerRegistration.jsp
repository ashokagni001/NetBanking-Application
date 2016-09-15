<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
	<c:if test="${message != null}">
		<script type="text/javascript">
			alert('CLICK OK THE PAGE WILL BE REFRESHED...'
					+ "<c:out value='${message}'/>");
			windows.location.reload();
		</script>
	</c:if>
	<div class="well">
		<font size="20"><marquee behavior="alternate">NET
				BANKING</marquee></font>
	</div>
	<center>
		<h2>REGISTER YOUR DETAIL</h2>
		<form:form method="post" action="register" modelAttribute="Customer">
			<br />
			<br />
			<form:input path="name" type="text" placeholder="Enter the name" />
			<br />
			<br />
			<form:input path="dob" type="text" placeholder="Enter the dob" />
			<br />
			<br />
			<b>Gender</b>
			<form:select path="gender">
				<form:option value="Male">Male</form:option>
				<form:option value="Female">Female</form:option>
			</form:select>
			<br />
			<br />
			<form:input path="mobileNumber" type="text"
				placeholder="Enter the mobile_number" />
			<br />
			<br />
			<form:input path="accountNumber" type="text"
				placeholder="Enter the account_number" />
			<br />
			<br />
			<form:input path="email" type="text" placeholder="Enter the email" />
			<br />
			<br />
			<input type="submit" name="submit" value="NEXT" />
		</form:form>
	</center>
	<div class="pos">
		<a class="btn btn-danger" href="logoutController"> LOGOUT</a>
	</div>
</body>
</html>
