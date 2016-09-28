<%@ include file="/common/taglibs.jsp"%>
<html>
<body>
			<div class="col-md-9">
				<div class="col-md-12">
					<div class="tex text-center">
						<h4>MINISTATMENTS</h4>
					</div>
					<div class="col-md-12 sizetable">
						<table class="table table-striped table-bordered table-hover"
							id="dataTables-example">
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
											value="${miniStatement.getCreditAccount()}"
											var="accountCredit" /> <c:out
											value="${accountCredit.accountNumber}" /></td>
									<td align="center"><c:out
											value="${miniStatement.getAmount()}" /></td>
									<td align="center"><c:out
											value="${miniStatement.getDate()}" /></td>
									<td align="center"><c:out
											value="${miniStatement.getStatus()}" /></td>
								</tr>
								<%
								    sno++;
								%>
							</c:forEach>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
