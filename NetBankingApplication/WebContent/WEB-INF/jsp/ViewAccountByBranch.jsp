<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	top: 150px;
	right: 0px;
	width: 200px;
	height: 120px;
}
</style>
</head>
<body>
	<div class="well">
		<font size="20"><marquee behavior="alternate">NET
				BANKING</marquee></font>
	</div>
	<center>
		<h1>VIEW ACCOUNT BY BRANCH</h1>
		<h3>Branch IFSC Number</h3>
		<form action="getAccount" method="get">
			<table>
				<tr>
					<td><input type="text" name="ifsc" placeholder="IFSC" required></td>
					<td><input type="submit" name="view" value="view"></td>
				</tr>
			</table>
		</form>
		<c:if test="${message != null}">
			<script type="text/javascript">
				alert('CLICK OK THE PAGE WILL BE REFRESHED...'
						+ "<c:out value='${message}'/>");
				windows.location.reload();
			</script>
		</c:if>
		<c:if test="${accounts != null}">
			<h2>Fetching Data From A Branch Management System</h2>
			<table class="table table-striped">
				<tr>
					<th>S.NO</th>
					<th>AccountNumber</th>
					<th>Customer Id</th>
					<th>Balance</th>
					<th>AccountType</th>
				</tr>
				<%
					int sno = 1;
				%>
				<c:forEach items="${accounts}" var="accounts">
					<tr>
						<td><c:out value="<%=sno%>" /></td>
						<td><c:out value="${accounts.getAccountNumber()}" /></td>
						<td><c:set value="${accounts.getCustomer()}" var="customer" />
							<c:out value="${customer.customerId}" /></td>
						<td><c:out value="${accounts.getBalance()}" /></td>
						<td><c:out value="${accounts.getAccountType()}" /></td>
					</tr>
					<%
						sno++;
					%>
				</c:forEach>
			</table>
		</c:if>
	</center>
	<div class="pos">
		<a href="BranchIndex" style="font-sise: 18px">Go to main page</a> <br />
		<a class="btn btn-danger" href="logoutController" style="width: 100px">
			LOGOUT</a>
	</div>

</body>
</html>