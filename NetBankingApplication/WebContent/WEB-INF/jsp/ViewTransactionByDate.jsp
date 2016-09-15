<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${sessionScope['id']== null}">
	<c:redirect url="login" />
</c:if>
<c:if test="${sessionScope['role']!= 'approver'}">
	<c:redirect url="approverIndexPage" />
</c:if>
<html>
<head>
<link rel="stylesheet" href="resource/css/bootstrap.css">
<script src="resource/js/bootstrap.js"></script>
<script src="resource/js/bootstrap1.js"></script>
<style type="text/css">
.well {
	background-color: #8ddfe1;
}

.pos {
	position: absolute;
	right: 100px;
	top: 78px;
	height: 70px;
	width: 50px;
}
</style>
</head>
<body>
	<div class="well">
		<font size="20"><marquee behavior="alternate">NET
				BANKING</marquee></font>
	</div>
	<br />
	<h2>Choose from date to date of Transaction Detail</h2>
	<form action="getDates" method="get">
		<table>
			<tr>
				<td><input type="date" name="fromDate" placeholder="2016-09-08"
					required></td>
				<td><input type="date" name="toDate" placeholder="2016-09-11"
					required></td>
				<td><input type="submit" name="view" value="view"></td>
			</tr>
		</table>
	</form>
	<c:if test="${transactions != null}">
		<table class=" table table-bordered">
			<tr>
				<th>S.NO</th>
				<th>TRANSACTION ID</th>
				<th>DEBIT ACCOUNT ID</th>
				<th>CREDIT ACCOUNT ID</th>
				<th>AMOUNT</th>
				<th>DATE TIME</th>
				<th>TRANSACTION STATUS</th>
				<th colspan="2" align="center">VIEW CUSTOMER DETAIL</th>
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
							value="${transactions.getCreditAccount()}" var="accountCredit" />
						<c:out value="${accountCredit.accountNumber}" /></td>
					<td align="center"><c:out value="${transactions.getAmount()}" /></td>
					<td align="center"><c:out value="${transactions.getDate()}" /></td>
					<td align="center"><c:out value="${transactions.getStatus()}" /></td>
					<td align="center"><a
						href="viewCustomerAccount?accountNumber=<c:out value="${accountDebit.accountNumber}"/>"
						style="color: blue">VIEW DEBIT ACCOUNT</a></td>
					<td align="center"><a
						href="viewCustomerAccount?accountNumber=<c:out value="${accountCredit.accountNumber}"/>"
						style="color: blue">VIEW CREDIT ACCOUNT</a></td>
				</tr>
				<%
					sno++;
				%>
			</c:forEach>
		</table>
	</c:if>

	<br />
	<c:if test="${message != null}">
		<script type="text/javascript">
			alert('CLICK OK THE PAGE WILL BE REFRESHED...'
					+ "<c:out value='${message}'/>");
			windows.location.reload();
		</script>
	</c:if>
	<a href="TransactionIndex">Go to main page</a>
	<div class="pos">
		<a class="btn btn-danger" href="logoutController"> LOGOUT</a>
	</div>
</body>
</html>
