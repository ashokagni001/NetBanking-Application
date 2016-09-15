<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${sessionScope['id']== null}">
	<c:redirect url="login" />
</c:if>
<c:if test="${sessionScope['role']!= 'user'}">
	<c:redirect url="approverIndexPage" />
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
				<th>DEBIT ACCOUNT NUMBER</th>
				<th>CREDIT ACCOUNT NUMBER</th>
				<th>AMOUNT</th>
				<th>DATE & TIME</th>
				<th>TRANSACTION STATUS</th>
			</tr>
			<%
				int sno = 1;
			%>
			<c:forEach items="${miniStatement}" var="miniStatement">
				<tr>
					<td><c:out value="<%=sno%>" /></td>
					<td align="center"><c:set
							value="${miniStatement.getDebitAccount()}" var="accountDebit" />
						<c:out value="${accountDebit.accountNumber}" /></td>
					<td align="center"><c:set
							value="${miniStatement.getCreditAccount()}" var="accountCredit" />
						<c:out value="${accountCredit.accountNumber}" /></td>
					<td align="center"><c:out value="${miniStatement.getAmount()}" />
					</td>
					<td align="center"><c:out value="${miniStatement.getDate()}" />
					</td>
					<td align="center"><c:out value="${miniStatement.getStatus()}" />
					</td>
				</tr>
				<%
					sno++;
				%>
			</c:forEach>
		</table>
	</center>
	<div class="pos">
		<a href="userHomePage">Go to main page </a> <br /> <br /> <a
			class="btn btn-danger" href="logoutController"> LOGOUT</a>
	</div>
</body>
</html>
