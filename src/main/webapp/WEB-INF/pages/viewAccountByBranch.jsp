<%@ include file="/common/taglibs.jsp"%>
<html>
<body>
	<h4>VIEW ACCOUNT BY BRANCH</h4>
	<div class="col-md-12 row">
		<form action="getAccounts" method="get">
			<div class="row">
				<div class="form-group">
					<label class="control-label col-md-3" for="ifsc">BRANCH
						IFSCODE </label>
					<div class="col-md-9">
						<input type="text" name="IFSCode" placeholder="Enter IFSCode"
							required /> Ex.I2I0BK****
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group">
					<div class="col-md-offset-3 col-md-9">
						<div class="col-md-4">
							<input class="btn btn-success" type="submit" value="VIEW" />
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
	<c:if test="${message != null}">
		<script type="text/javascript">
			alert("<c:out value='${message}'/>");
			windows.location.reload();
		</script>
	</c:if>
	<c:if test="${accounts != null}">
		<div class="col-md-12 sizetable">
			<table class="table table-striped table-bordered table-hover"
				id="dataTables-example">
				<tr>
					<th>S.NO</th>
					<th>ACCOUNT NUMBER</th>
					<th>BALANCE</th>
					<th>ACCOUNT TYPE</th>
				</tr>
				<%
				    int sno = 1;
				%>
				<c:forEach items="${accounts}" var="accounts">
					<tr>
						<td><c:out value="<%=sno%>" /></td>
						<td><c:out value="${accounts.getAccountNumber()}" /></td>
						<td><c:out value="${accounts.getBalance()}" /></td>
						<td><c:out value="${accounts.getAccountType()}" /></td>
					</tr>
					<%
					    sno++;
					%>
				</c:forEach>
			</table>
		</div>
	</c:if>
</body>
</html>
