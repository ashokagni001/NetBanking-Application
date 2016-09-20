<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${message != null}">
	<script type="text/javascript">
		alert("<c:out value='${message}'/>");
		windows.location.reload();
	</script>
</c:if>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>REGISTER</title>
<link href="css/bootstrap.css" rel="styleSheet">
<!-- MetisMenu CSS -->
<link href="css/metisMenu.min.css" rel="stylesheet">
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/metisMenu.min.js"></script>
<link href="css/mystyle.css" rel="stylesheet">
</head>
<body>
	<div class="col-md-12 container">
		<c:import url="RegHead.jsp" />
		<br />
		<div class="tex text-center">
			<h3>REGISTER YOUR DETAIL FOR NETBANKING</h3>
		</div>
		<center>
			<form:form method="post" action="register" modelAttribute="Customer">
				<br />
				<br />
				<form:input path="name" type="text" placeholder="Enter the name"
					required="required" />
				<br />
				<br />
				<form:input path="dob" type="text" placeholder="Enter the dob"
					required="required" />
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
					placeholder="Enter the mobile_number" required="required" />
				<br />
				<br />
				<form:input path="accountNumber" type="text"
					placeholder="Enter the account_number" required="required" />
				<br />
				<br />
				<form:input path="email" type="text" placeholder="Enter the email"
					required="required" />
				<br />
				<br />
				<input type="submit" name="submit" value="NEXT" />
			</form:form>
		</center>
	</div>
</body>
</html>