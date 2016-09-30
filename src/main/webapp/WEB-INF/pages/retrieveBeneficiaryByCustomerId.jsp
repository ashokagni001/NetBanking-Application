<%@ include file="/common/taglibs.jsp"%>
<html>
<body>
	<c:if test="${message != null}">
		<script type="text/javascript">
			alert("<c:out value='${message}'/>");
			windows.location.reload();
		</script>
	</c:if>
			<div class="col-md-9">
				<div class="col-md-12">

					<c:if test="${beneficiaries != null}">
						<center>
							<h5>ALL BENEFICIARIES ACCOUNTS</h5>
						</center>
						<table class="table table-striped table-bordered table-hover"
							id="dataTables-example">
							<tr>
								<th>S.NO</th>
								<th>NAME</th>
								<th>ACCOUNT NUMBER</th>
							</tr>
							<%
							    int sno = 1;
							%>
							<c:forEach items="${beneficiaries}" var="beneficiaries">
								<tr>
									<td><c:out value="<%=sno%>" /></td>
									<td><c:out
                                            value="${beneficiaries.getCustomerAccountNumber().getUser().getFirstName()}" /></td>
									<td><a
										href="addBeneficiaryTransaction?customerAccountNumber=<c:out value="${beneficiaries.getCustomerAccountNumber().getAccountNumber()}"/>">
											<c:out
												value="${beneficiaries.getCustomerAccountNumber().getAccountNumber()}" />
									</a></td>
								</tr>
								<%
								    sno++;
								%>
							</c:forEach>
						</table>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</body>
</html>