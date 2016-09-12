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
					<th align="center" height="30" width="100">STREET</th>
					<th align="center" height="30" width="100">CITY</th>
					<th align="center" height="30" width="100">STATE</th>
					<th align="center" height="30" width="100">COUNTRY</th>
					<th align="center" height="30" width="100">PIN CODE</th>
				</tr>
				<c:set value="${address}" var="address" />
				<tr>
					<td><c:out value="${address.getStreet()}" /></td>
					<td><c:out value="${address.getCity()}" /></td>
					<td><c:out value="${address.getState()}" /></td>
					<td><c:out value="${address.getCountry()}" /></td>
					<td><c:out value="${address.getPincode()}" /></td>
				</tr>
			</table>
		</c:if>
		<br />
		<br />
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
		<a href="ViewAccountByBranch" style="font-sise: 18px"> click here</a>
		</br>
		</br>
	</c:if>
	<c:if test="${sessionScope['role']== 'user'}">
		<b>Go to main page </b>
		<a href="GetCustomer" style="font-sise: 18px"> click here</a>
		</br>
		</br>
	</c:if>
	<br />
	<a href="logoutController" style="width: 300px;"> LOGOUT</a>
</body>
</html>
