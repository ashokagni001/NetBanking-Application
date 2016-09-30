<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>ADD NEW ACCOUNT</title>
<style type="text/css">
.row {
	margin: 3px;
}
</style>
</head>
<body>
	<h4>ADD NEW ACCOUNT</h4>
	<div class="col-md-12 row">
		<form method="post" action="insertAccount">
			<div class="row">
				<div class="form-group">
					<label class="control-label col-sm-3" for="accountNumber">ACCOUNT
						NUMBER </label>
					<div class="col-sm-9">
						<input type="number" name="accountNumber"
							placeholder="Enter accountNumber" min = "1" reqiured />
					</div>
				</div>
			</div>
			<div class="row">
				<div class="input-field col s12">
					<label class="control-label col-md-3" for="ifscode">IFSC
						CODE </label>
					<div class="col-md-9">
						<select name="ifscode" required>
							<option value="" disabled selected>Choose your IFSC</option>
							<c:forEach items="${branches}" var="branch">
								<option value="${branch.getIFSCode()}">${branch.getIFSCode()}</option>
								</c:forEach>
						</select>
					</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group">
						<label class="control-label col-md-3" for="balance">AMOUNT
						</label>
						<div class="col-md-9">
							<input type="number" name="balance" placeholder="Enter Amount"
								min = "1" required />
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group">
						<label class="control-label col-md-3" for="accounttype">ACCOUNT
							TYPE </label>
						<div class="col-md-9">
							<select name="accountType">
								<option value="Current">Current</option>
								<option value="Saving">Saving</option>
							</select>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group">
						<div class="col-md-offset-3 col-md-9">
							<div class="col-md-4">
								<input class="btn btn-success" type="submit" name="addaccount"
									value="ADD" />
							</div>
						</div>
					</div>
				</div>
		</form>
	</div>
	<c:if test="${branches != null}">
		<h3>Branch Details</h3>
		<div class="row">
			<div class="col-md-12 sizetable">
				<table class="table table-striped table-bordered table-hover"
					id="dataTables-example">
					<tr>
						<th>S.NO</th>
						<th>IFSC CODE</th>
						<th>EMAIL</th>
						<th>ADDRESS</th>
						<th>CITY</th>
						<th>STATE</th>
						<th>PINCODE</th>
					</tr>
					<%
					    int sno = 1;
					%>
					<c:forEach items="${branches}" var="branches">
						<tr>
							<td><c:out value="<%=sno%>" /></td>
							<td><c:out value="${branches.getIFSCode()}" /></td>
							<td><c:out value="${branches.getEmailId()}" /></td>
							<c:set value="${branches.getAddress()}" var="address" />
							<td><c:out value="${address.getAddress()}" /></td>
							<td><c:out value="${address.getCity()}" /></td>
							<td><c:out value="${address.getProvince()}" /></td>
							<td><c:out value="${address.getPostalCode()}" /></td>
						</tr>
						<%
						    sno++;
						%>
					</c:forEach>
				</table>
			</div>
		</div>
		<c:if test="${message != null}">
			<script type="text/javascript">
				alert("<c:out value='${message}'/>");
				windows.location.reload();
			</script>
		</c:if>
	</c:if>
	<c:if test="${null == branches}">
		<h3>${message}</h3>
	</c:if>
</body>
</html>