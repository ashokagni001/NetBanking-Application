<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<c:if test="${message != null}">
		<script type="text/javascript">
			alert("<c:out value='${message}'/>");
			windows.location.reload();
		</script>
	</c:if>
	<c:if test="${account != null}">
		<table class="table table-striped">
			<tr>
				<th>ACCOUNT NUMBER</th>
				<th>BALANCE</th>
				<th>ACCOUNT TYPE</th>
				<th>BRANCH IFSC</th>
				<th>BRANCH ADDRESS</th>
				<th>BRANCH CITY</th>
			</tr>
			<c:set value="${account}" var="account" />
			<tr>
				<td><c:out value="${account.getAccountNumber()}" /></td>
				<td><c:out value="${account.getBalance()}" /></td>
				<td><c:out value="${account.getAccountType()}" /></td>
				<td><c:out value="${account.getBranch().IFSCode}" /></td>
				<td><c:out value="${account.getBranch().getAddress().address}" /></td>
				<td><c:out value="${account.getBranch().getAddress().city}" /></td>
			</tr>
		</table>
	</c:if>
</body>
</html>