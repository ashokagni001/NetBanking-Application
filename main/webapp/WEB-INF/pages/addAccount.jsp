<%@ include file="/common/taglibs.jsp"%>
<html>
    <body>
						<h4>ADD NEW ACCOUNT</h4>
					<div class="col-md-12 row">
						<form method="post" action="addAccount">
							<div class="row">
								<div class="form-group">
									<label class="control-label col-sm-3" for="accountNumber">ACCOUNT
										NUMBER </label>
									<div class="col-sm-9">
										<input type="number" name="accountNumber"
											placeholder="Enter accountNumber" required />
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-group">
									<label class="control-label col-md-3" for="ifscode">
										IFSC CODE </label>
									<div class="col-md-9">
										<input type="text" name="IFSCode"
											placeholder="Enter Bank IFSCode" required />
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-group">
									<label class="control-label col-md-3" for="balance">AMOUNT
									</label>
									<div class="col-md-9">
										<input type="balance" name="balance"
											placeholder="Enter Amount" required />
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
											<center>
												<input class="btn btn-success" type="submit"
													name="addaccount" value="ADD" />
											</center>
										</div>
									</div>
								</div>
							</div>
						</form>
					</div>
					<br/><br/>
					<h3>Branch Details</h3>
					<div class="row">
					<c:if test="${branches != null}">
						<div class="col-md-12 sizetable">
							<table class="table table-striped table-bordered table-hover"
								id="dataTables-example">
								<tr>
									<th>S.NO</th>
									<th>IFSC CODE</th>
									<th>EMAIL</th>
								</tr>
								<%
								    int sno = 1;
								%>
								<c:forEach items="${branches}" var="branches">
									<tr>
										<td><c:out value="<%=sno%>" /></td>
										<td><c:out value="${branches.getIFSCode()}" /></td>
										<td><c:out value="${branches.getEmailId()}" /></td>
									</tr>
									<%
									    sno++;
									%>
								</c:forEach>
							</table>
						</div>
					</c:if>
				</div>
				<c:if test="${message != null}">
					<script type="text/javascript">
						alert("<c:out value='${message}'/>");
						windows.location.reload();
					</script>
				</c:if>
</body>
</html>
