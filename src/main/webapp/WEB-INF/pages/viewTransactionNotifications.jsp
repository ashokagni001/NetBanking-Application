<%@ include file="/common/taglibs.jsp"%>
<html>
<body>
	<div class="col-md-9">
		<div class="col-md-12">
			<div class="tex text-center">
				<h4>ALL TRANSACTION NOTIFICATIONS</h4>
			</div>
			<c:if test="${message != null}">
				<script type="text/javascript">
					alert("<c:out value='${message}'/>");
					windows.location.reload();
				</script>
			</c:if>
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
								<a
								href="viewAccountByAccountNumber?accountNumber=<c:out value="${accountDebit.accountNumber}"/>"
								style="color: blue"><c:out
										value="${accountDebit.accountNumber}" /></a></td>
							<td align="center"><c:set
									value="${notifications.getCreditAccount()}" var="accountCredit" />
								<a
								href="viewAccountByAccountNumber?accountNumber=<c:out value="${accountCredit.accountNumber}"/>"
								style="color: blue"><c:out
										value="${accountCredit.accountNumber}" /></a></td>
							<td align="center"><c:out
									value="${notifications.getAmount()}" /></td>
							<td align="center"><c:out value="${notifications.getDate()}" /></td>
							<td align="center" height="30" width="100"><a
								href="updateTransactionStatus?id=<c:out value="${notifications.getId()}"/>&accountNumber=<c:out value="${accountCredit.accountNumber}"/>&amount=<c:out value="${notifications.getAmount()}"/>&action=Success"
								style="color: blue">PERMISSION</a></td>
							<td align="center" height="30" width="100"><a
								href="updateTransactionStatus?id=<c:out value="${notifications.getId()}"/>&accountNumber=<c:out value="${accountDebit.accountNumber}"/>&amount=<c:out value="${notifications.getAmount()}"/>&action=Failure"
								style="color: blue">IGNORE</a></td>
						</tr>
						<%
						    sno++;
						%>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
</html>