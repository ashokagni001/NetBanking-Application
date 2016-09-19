<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${sessionScope['id']== null}">
	<c:redirect url="login" />
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
	<c:if test="${sessionScope['role'] == 'approver'}">
		<div class="well">
			<font size="20"><marquee behavior="alternate">I2I
					NETBANKING</marquee></font>
		</div>
		<h3> Welcome  ${sessionScope['name']} </h3><br />
		<c:if test="${message != null}">
			<script type="text/javascript">
				alert("<c:out value='${message}'/>");
				windows.location.reload();
			</script>
		</c:if>
		<h1>VIEW CUSTOMER</h1>
		<center>
			<c:if test="${customer != null}">
				<h2>Fetching Data From A Customer Management System</h2>
				<table class=" table table-bordered">
					<tr>
						<th align="center" height="30" width="100">CUSTOMER ID</th>
						<th align="center" height="30" width="100">NAME</th>
						<th align="center" height="30" width="100">AGE</th>
						<th align="center" height="30" width="100">DOB</th>
						<th align="center" height="30" width="100">GENDER</th>
						<th align="center" height="30" width="100">MOB.NUMBER</th>
						<th align="center" height="30" width="100">EMAIL</th>
						<th align="center" height="30" width="100">ACCOUNT NUMBER</th>
						<th colspan="2" align="center" height="30" width="100">ADDRESS</th>
					</tr>
					<c:set value="${customer}" var="customer" />
					<tr>
						<td><c:out value="${customer.getCustomerId()}" /></td>
						<td><c:out value="${customer.getName()}" /></td>
						<td><c:out value="${customer.getAge()}" /></td>
						<td><c:out value="${customer.getDob()}" /></td>
						<td><c:out value="${customer.getGender()}" /></td>
						<td><c:out value="${customer.getMobileNumber()}" /></td>
						<td><c:out value="${customer.getEmail()}" /></td>
						<c:set value="${customer.getAccountNumber()}" var="accountNum" />
						<td align="center"><a
					href="viewCustomerAccount?accountNumber=<c:out value="${accountNum}"/>"
					style="color: blue"><c:out value="${accountNum}" /></a></td>
						<c:set value="${customer.getAddress()}" var="address" />
						<td><a
							href="viewCustomerAddress?addressId=<c:out value="${address.addressId}"/>"
							style="color: blue">VIEW</a></td>
					</tr>
				</table>
			</c:if>
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
							<c:set value="${customers.getAccountNumber()}" var="accountNum" />
							<td align="center"><a
					        href="viewCustomerAccount?accountNumber=<c:out value="${accountNum}"/>"
					        style="color: blue"><c:out value="${accountNum}" /></a></td>
							<td><a
								href="viewCustomerAddress?addressId=<c:out value="${address.addressId}"/>"
								style="color: blue">VIEW</a></td>
						</tr>
						<%
							sno++;
						%>
					</c:forEach>
				</table>
				<h3>If u want any one Customer Details</h3>
				<h5>Insert Customer Id</h5>
				<form action="getCustomer" method="get">
					<table>
						<tr>
							<td><input type="text" name="customerId"
								placeholder="CUSTOMER ID" required></td>
							<td><input type="submit" name="view" value="VIEW"></td>
						</tr>
					</table>
				</form>
			</c:if>
		</center>
		<a href="approverIndexPage" style="font-sise: 18px">Go to main
			page </a>
		<div class="pos">
			<a class="btn btn-danger" href="logoutController"> LOGOUT</a>
		</div>
	</c:if>
	<c:if test="${sessionScope['role'] == 'user'}">
		<c:if test="${message != null}">
			<script type="text/javascript">
				alert("<c:out value='${message}'/>");
				windows.location.reload();
			</script>
		</c:if>
		<div class="well">
			<font size="20"><marquee behavior="alternate">NET
					BANKING</marquee></font>
		</div>
		<br />
		<h3> Welcome  ${sessionScope['name']} </h3><br />
		<h2>Fetching Data From A Customer Management System</h2>
		<br />
		<table class="table table-condensed">
			<tr>
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
			<c:set value="${customer}" var="customer" />
			<tr>
				<td align="center"><c:out value="${customer.getCustomerId()}" /></td>
				<td align="center"><c:out value="${customer.getName()}" /></td>
				<td align="center"><c:out value="${customer.getAge()}" /></td>
				<td align="center"><c:out value="${customer.getDob()}" /></td>
				<td align="center"><c:out value="${customer.getGender()}" /></td>
				<td align="center"><c:out value="${customer.getMobileNumber()}" /></td>
				<td align="center"><c:out value="${customer.getEmail()}" /></td>
				<c:set value="${customer.getAccountNumber()}" var="accountNum" />
				<td align="center"><a
					href="viewCustomerAccount?accountNumber=<c:out value="${accountNum}"/>"
					style="color: blue"><c:out value="${accountNum}" /></a></td>
				<c:set value="${customer.getAddress()}" var="address" />
				<td><a
					href="viewCustomerAddress?addressId=<c:out value="${address.addressId}"/>"
					style="color: blue">VIEW</a></td>
			</tr>
		</table>
		<a href="userHomePage">Go to main page</a>
		<div class="pos">
			<a class="btn btn-danger" href="logoutController"> LOGOUT</a>
		</div>
	</c:if>
</body>
</html>
