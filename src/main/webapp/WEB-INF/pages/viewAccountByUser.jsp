<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>VIEW ACCOUNT</title>
</head>
<body>
    <h3>ACCOUNT DETAIL :</h3>
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
				<th>BRANCH CITY</th>
				<th>BRANCH STATE</th>
				<th>BRANCH COUNTRY</th>
			</tr>
			<c:set value="${account}" var="account" />
			<tr>
				<td><c:out value="${account.getAccountNumber()}" /></td>
				<td><c:out value="${account.getBalance()}" /></td>
				<td><c:out value="${account.getAccountType()}" /></td>
				<td><c:out value="${account.getBranch().getIFSCode() }" /></td>
				<td><c:out value="${account.getBranch().getAddress().getCity() }" /></td>
			    <td><c:out value="${account.getBranch().getAddress().getProvince() }" /></td>
			    <td><c:out value="${account.getBranch().getAddress().getCountry() }" /></td>
			</tr>
		</table>
	</c:if>
</body>
</html>