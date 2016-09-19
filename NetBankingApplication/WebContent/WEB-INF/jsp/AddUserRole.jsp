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
		<font size="20"><marquee behavior="alternate">I2I
				NETBANKING</marquee></font>
	</div>
	<h3> Welcome  ${sessionScope['name']} </h3><br />
	<h2>ADD USER ROLE</h2>
	<c:if test="${customers != null}">
		<h2>Fetching Data From A Customer Management System</h2>
		<table class=" table table-bordered">
			<tr>
				<th>S.NO</th>
				<th>CUSTOMER ID</th>
				<th>NAME</th>
				<th>AGE</th>
				<th>DOB</th>
				<th>GENDER</th>
				<th>MOB.NUMBER</th>
				<th>EMAIL</th>
				<th>ACCOUNT NUMBER</th>
				<th colspan="2" align="center" height="30" width="100">ADDRESS</th>
			</tr>
			<%
				int sno = 1;
			%>
			<c:forEach items="${customers}" var="customers">
				<tr>
					<td><c:out value="<%=sno%>" /></td>
					<td><c:out value="${customers.getCustomerId()}" /></td>
					<td><c:out value="${customers.getName()}" /></td>
					<td><c:out value="${customers.getAge()}" /></td>
					<td><c:out value="${customers.getDob()}" /></td>
					<td><c:out value="${customers.getGender()}" /></td>
					<td><c:out value="${customers.getMobileNumber()}" /></td>
					<td><c:out value="${customers.getEmail()}" /></td>
					<td><c:out value="${customers.getAccountNumber()}" /></td>
					<c:set value="${customers.getAddress()}" var="address" />
					<td><a
						href="viewCustomerAddress?addressId=<c:out value="${address.addressId}"/>"
						style="color: blue">VIEW</a></td>
				</tr>
				<%
					sno++;
				%>
			</c:forEach>
		</table>
		</c:if>
		<center>
			<h3>INSERT DETAIL</h3>
			<br />
			<table>
				<form method="get" action="insertRole" />
				<tr>
					<td>CUSTOMER ID :<input type="text" name="customerId"
						placeholder="Enter the customerId" required></td>
				</tr>
				<tr>
					<td>SELECT ROLE : <select name="role">
							<c:forEach items="${roles}" var="role">
								<option value="${role.roleId}">${role.roleName}</option>
							</c:forEach>
					</select>
					</td>
				</tr>
				<tr>
					<td><input type="submit" name="add" value="Allocate"></td>
				</tr>
			</table>
			</form>
			</br> </br>
		</center>
		<c:if test="${message != null}">
			<script type="text/javascript">
				alert("<c:out value='${message}'/>");
				windows.location.reload();
			</script>
		</c:if>
		<a class="btn btn-link" href="BranchIndex">Go to main page </a>
		</br>
		</br>
		<div class="pos">
			<a class="btn btn-danger" href="logoutController"> LOGOUT</a>
		</div>
</body>
</html>
