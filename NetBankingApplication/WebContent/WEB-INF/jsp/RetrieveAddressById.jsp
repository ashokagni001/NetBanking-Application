<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
</style>
</head>
<body>
	<div class="well">
		<font size="20"><marquee behavior="alternate">NET
				BANKING</marquee></font>
	</div>
	<center>
		<c:if test="${address != null}">
			<h1>Fetching Data From A Branch Database</h1>
			<table class=" table table-bordered">
				<tr>
					<th>STREET</th>
					<th>CITY</th>
					<th>STATE</th>
					<th>COUNTRY</th>
					<th>PIN CODE</th>
				</tr>
				<c:set value="${address}" var="address" />
				<tr>
					<td align="center"><c:out value="${address.getStreet()}" /></td>
					<td align="center"><c:out value="${address.getCity()}" /></td>
					<td align="center"><c:out value="${address.getState()}" /></td>
					<td align="center"><c:out value="${address.getCountry()}" /></td>
					<td align="center"><c:out value="${address.getPincode()}" /></td>
				</tr>
			</table>
		</c:if>
		<br /> <br />
		<c:if test="${message != null}">
			<script type="text/javascript">
				alert('CLICK OK THE PAGE WILL BE REFRESHED...'
						+ "<c:out value='${message}'/>");
				windows.location.reload();
			</script>
		</c:if>
	</center>
	<c:if test="${sessionScope['role']== 'approver'}">
		<b>Go to main page </b>
		<a href="approverHomePage" style="font-sise: 18px"> click here</a>
	</c:if>
	<c:if test="${sessionScope['role']== 'user'}">
		<b>Go to main page </b>
		<td><a
			href="getCustomer?customerId=<c:out value="${sessionScope['id']}"/>">CUSTOMER
				DETAIL</a></td>
	</c:if>
	<br />
	<a href="logoutController" style="width: 300px;"> LOGOUT</a>
</body>
</html>
