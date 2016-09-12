<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${sessionScope['id']== null}">
	<c:redirect url="login" />
</c:if>
<c:if test="${sessionScope['role']!= 'approver'}">
	<c:redirect url="userHomePage" />
</c:if>
<html>
<head>
<link rel="stylesheet" href="resource/css/bootstrap.css">
<script src="resource/js/bootstrap.js"></script>
<script src="resource/js/bootstrap.min.js"></script>
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
		<table class=" table table-bordered">
			<tr>
				<th>S.NO</th>
				<th>TRANSACTION ID</th>
				<th>DEBIT ACCOUNT ID</th>
				<th>CREDIT ACCOUNT ID</th>
				<th>AMOUNT</th>
				<th>DATE & TIME</th>
				<th>TRANSACTION STATUS</th>
				<th colspan="2">VIEW CUSTOMER DETAIL</th>
			</tr>
			<%
				int sno = 1;
			%>
			<c:forEach items="${transactions}" var="transactions">
				<tr>
					<td align="center"><c:out value="<%=sno%>" /></td>
					<td align="center"><c:out value="${transactions.getId()}" /></td>
					<td align="center"><c:set
							value="${transactions.getDebitAccount()}" var="accountDebit" />
						<c:out value="${accountDebit.accountNumber}" /></td>
					<td align="center"><c:set
							value="${transactions.getCriditAccount()}" var="accountCredit" />
						<c:out value="${accountCredit.accountNumber}" /></td>
					<td align="center"><c:out value="${transactions.getAmount()}" /></td>
					<td align="center"><c:out value="${transactions.getDate()}" /></td>
					<td align="center"><c:out value="${transactions.getStatus()}" /></td>
					<td align="center" height="30" width="100"><a
						href="viewCustomerAccount?accountNumber=<c:out value="${accountDebit.accountNumber}"/>"
						style="color: blue">VIEW DEBIT ACCOUNT</a></td>
					<td align="center" height="30" width="100"><a
						href="viewCustomerAccount?accountNumber=<c:out value="${accountCredit.accountNumber}"/>"
						style="color: blue">VIEW CREDIT ACCOUNT</a></td>
				</tr>
				<%
					sno++;
				%>
			</c:forEach>
		</table>
		<br /> <br /> <b>Go to main page </b><a href="approverHomePage"
			style="font-sise: 18px"> click here</a> <br /> <a
			href="logoutController" style="width: 300px"> LOGOUT</a>
		<c:if test="${message != null}">
			<script type="text/javascript">
				alert('CLICK OK THE PAGE WILL BE REFRESHED...'
						+ "<c:out value='${message}'/>");
				windows.location.reload();
			</script>
		</c:if>
	</center>
</body>
</html>
