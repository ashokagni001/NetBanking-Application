
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
		<table class=" table table-bordered">
			<tr>
				<th>S.NO</th>
				<th>TRANSACTION ID</th>
				<th>DEBIT ACCOUNT NUMBER</th>
				<th>CREDIT ACCOUNT NUMBER</th>
				<th>AMOUNT</th>
				<th>DATE & TIME</th>
				<th colspan="2" align="center" height="30" width="100">VIEW
					CUSTOMER DETAIL</th>
				<th colspan="2" align="center" height="30" width="100">TRANSACTION
					ACTION</th>
			</tr>
			<%
				int sno = 1;
			%>
			<c:forEach items="${notifications}" var="notifications">
				<tr>
					<td align="center"><c:out value="<%=sno%>" /></td>
					<td align="center"><c:out value="${notifications.getId()}" /></td>
					<td align="center"><c:set
							value="${notifications.getDebitAccount()}" var="accountDebit" />
						<c:out value="${accountDebit.accountNumber}" /></td>
					<td align="center"><c:set
							value="${notifications.getCriditAccount()}" var="accountCridit" />
						<c:out value="${accountCridit.accountNumber}" /></td>
					<td align="center"><c:out value="${notifications.getAmount()}" /></td>
					<td align="center"><c:out value="${notifications.getDate()}" /></td>
					<td align="center" height="30" width="100"><a
						href="viewCustomerAccount?accountNumber=<c:out value="${accountDebit.accountNumber}"/>"
						style="color: blue">VIEW DEBIT ACCOUNT</a></td>
					<td align="center" height="30" width="100"><a
						href="viewCustomerAccount?accountNumber=<c:out value="${accountCridit.accountNumber}"/>"
						style="color: blue">VIEW CRIDIT ACCOUNT</a></td>
					<td align="center" height="30" width="100"><a
						href="transactionSuccess?id=<c:out value="${notifications.getId()}"/>&criditAccountNumber=<c:out value="${accountCridit.accountNumber}"/>&amount=<c:out value="${notifications.getAmount()}"/>"
						style="color: blue">PERMISSION</a></td>
					<td align="center" height="30" width="100"><a
						href="transactionCancel?id=<c:out value="${notifications.getId()}"/>&debitAccountNumber=<c:out value="${accountDebit.accountNumber}"/>&amount=<c:out value="${notifications.getAmount()}"/>"
						style="color: blue">IGNORE</a></td>
				</tr>
				<%
					sno++;
				%>
			</c:forEach>
		</table>
		<br /> <br /> <b>Go to main page </b><a href="approverHomePage"
			style="font-sise: 18px"> click here</a> <br />
		<a href="logoutController" style="width: 300px"> LOGOUT</a>
	</center>
</body>
</html>
