<%@ include file="/common/taglibs.jsp"%>
<html>
<body>
	<c:if test="${null != transactions }">
		<div class="tex text-center">
			<h4>VIEW TRANSACTION</h4>
		</div>
		<div class="col-md-12 sizetable">
			<table class="table table-striped table-bordered table-hover"
				id="dataTables-example">
				<tr>
					<th>S.NO</th>
					<th>TRANSACTION ID</th>
					<th>DEBIT ACCOUNT</th>
					<th>CREDIT ACCOUNT</th>
					<th>AMOUNT</th>
					<th>DATE & TIME</th>
					<th>TRANSACTION STATUS</th>
				</tr>
				<%
				    int sno = 1;
				%>
				<c:forEach items="${transactions}" var="transactions">
					<tr>
						<td><c:out value="<%=sno%>" /></td>
						<td><c:out value="${transactions.getId()}" /></td>
						<td><c:set
								value="${transactions.getDebitAccount()}" var="accountDebit" />
							<a
							href="viewAccountByAccountNumber?accountNumber=<c:out value="${accountDebit.accountNumber}"/>"
							style="color: blue"><c:out
									value="${accountDebit.accountNumber}" /></a></td>
						<td><c:set
								value="${transactions.getCreditAccount()}" var="accountCredit" />
							<a
							href="viewAccountByAccountNumber?accountNumber=<c:out value="${accountCredit.accountNumber}"/>"
							style="color: blue"><c:out
									value="${accountCredit.accountNumber}" /></a></td>
						<td><c:out value="${transactions.getAmount()}" /></td>
						<td><c:out value="${transactions.getDate()}" /></td>
						<td><c:out value="${transactions.getStatus()}" /></td>
					</tr>
					<%
					    sno++;
					%>
				</c:forEach>
			</table>
		</div>
		<c:if test="${null != message}">
			<script type="text/javascript">
				alert("<c:out value='${message}'/>");
				windows.location.reload();
			</script>
		</c:if>
	</c:if>
	<c:if test="${null == transactions }">
		<h3>NO TRANSACTION AVAILABLE</h3>
	</c:if>
</body>
</html>