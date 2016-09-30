<%@ include file="/common/taglibs.jsp"%>
<html>
<body>
	<c:if test="${beneficiaryNotifications != null }">
		<c:if test="${message != null}">
			<script type="text/javascript">
				alert("<c:out value='${message}'/>");
				windows.location.reload();
			</script>
		</c:if>

		<div class="tex text-center">
			<h4>ALL BENEFICIARY NOTIFIACTIONS</h4>
		</div>

		<div class="col-md-12 sizetable">
			<table class="table table-striped table-bordered table-hover"
				id="dataTables-example">
				<tr>
					<th>S.NO</th>
					<th>BENEFICIARY ID</th>
					<th>BENEFICIARY CUSTOMER NUMBER</th>
					<th>CUSTOMER ACCOUNT NUMBER</th>
					<th colspan="2" align="center" height="30" width="100">TRANSACTION
						ACTION</th>
				</tr>
				<%
				    int sno = 1;
				%>
				<c:forEach items="${beneficiaryNotifications}"
					var="beneficiaryNotifications">
					<tr>
						<td align="center"><c:out value="<%=sno%>" /></td>
						<td align="center"><c:out
								value="${beneficiaryNotifications.getId()}" /></td>
						<td align="center"><c:set
								value="${beneficiaryNotifications.getBeneficiaryAccountNumber()}"
								var="beneficiaryCustomer" /> <a
							href="viewCustomerAccount?accountNumber=<c:out value="${beneficiaryCustomer.accountNumber}"/>"
							style="color: blue"><c:out
									value="${beneficiaryCustomer.accountNumber}" /></a></td>
						<td align="center"><c:set
								value="${beneficiaryNotifications.getCustomerAccountNumber()}"
								var="customerAccount" /> <a
							href="viewCustomerAccount?accountNumber=<c:out value="${customerAccount.accountNumber}"/>"
							style="color: blue"><c:out
									value="${customerAccount.accountNumber}" /></a></td>
						<td align="center" height="30" width="100"><a
							href="updateBeneficiaryAccount?id=<c:out value="${beneficiaryNotifications.getId()}"/>&action=Success">
								ACCEPT</a></td>
						<td align="center" height="30" width="100"><a
							href="updateBeneficiaryAccount?id=<c:out value="${beneficiaryNotifications.getId()}"/>&action=Failure">
								IGNORE</a></td>
					</tr>
					<%
					    sno++;
					%>
				</c:forEach>
			</table>
		</div>
	</c:if>
	<c:if test="${beneficiaryNotifications == null }">
	<h3>${message}</h3>
	</c:if>
</body>
</html>