<%@ include file="/common/taglibs.jsp"%>
<html>
<body>
			<div class="col-md-9">
				<div class="col-md-12">
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
									<td align="center"><c:out value="<%=sno%>" /></td>
									<td align="center"><c:out value="${transactions.getId()}" /></td>
									<td align="center"><c:set
											value="${transactions.getDebitAccount()}" var="accountDebit" />
										<a
										href="viewAccountByCustomerId?accountNumber=<c:out value="${accountDebit.accountNumber}"/>"
										style="color: blue"><c:out
												value="${accountDebit.accountNumber}" /></a></td>
									<td align="center"><c:set
											value="${transactions.getCreditAccount()}"
											var="accountCredit" /> <a
										href="viewAccountByCustomerId?accountNumber=<c:out value="${accountCredit.accountNumber}"/>"
										style="color: blue"><c:out
												value="${accountCredit.accountNumber}" /></a></td>
									<td align="center"><c:out
											value="${transactions.getAmount()}" /></td>
									<td align="center"><c:out
											value="${transactions.getDate()}" /></td>
									<td align="center"><c:out
											value="${transactions.getStatus()}" /></td>
								</tr>
								<%
								    sno++;
								%>
							</c:forEach>
						</table>
					</div>
					<c:if test="${message != null}">
						<script type="text/javascript">
							alert("<c:out value='${message}'/>");
							windows.location.reload();
						</script>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</body>
</html>