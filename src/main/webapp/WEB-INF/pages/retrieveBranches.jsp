<%@ include file="/common/taglibs.jsp"%>
<html>
<body>
					<div class="col-md-12 row">
						<form action="getBranchByIFSCode" method="get">
							<div class="row">
								<div class="form-group">
									<label class="control-label col-md-3" for="ifsc">BRANCH
										IFSCODE </label>
									<div class="col-md-9">
										<input type="text" name="IFSCode" placeholder="Enter IFSCode"
											required />
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-group">
									<div class="col-md-offset-3 col-md-9">
										<div class="col-md-4">
											<center>
												<input class="btn btn-success" type="submit"
													 value="VIEW" />
											</center>
										</div>
									</div>
								</div>
							</div>
						</form>
					</div>
					<c:if test="${branch != null}">
						<div class="col-md-12 sizetable">
							<table class="table table-striped table-bordered table-hover"
								id="dataTables-example">
								<tr>
									<th>IFSC CODE</th>
									<th>EMAIL ID</th>
									<th>ADDRESS</th>
								</tr>
								<c:set value="${branch}" var="branch" />
								<tr>
									<td align="center"><c:out value="${branch.getIFSCode()}" /></td>
									<td align="center"><c:out value="${branch.getEmailId()}" /></td>
									<c:set value="${branch.getAddress()}" var="address" />
									<td align="center"><a
										href="viewBranchAddress?addressId=<c:out value="${address.addressId}"/>"
										style="color: blue">VIEW</a></td>
								</tr>
							</table>
						</div>
					</c:if>
					<c:if test="${branches != null}">
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
								</tr>
								<%
								    int sno = 1;
								%>
								<c:forEach items="${branches}" var="branches">
									<tr>
										<td><c:out value="<%=sno%>" /></td>
										<td><c:set value="${branches.getIFSCode()}" var="IFSC" />
										<a href="getAccounts?IFSCode=<c:out value="${IFSC}"/>" style="color: blue"><c:out value="${IFSC}" /></a></td>
										<td><c:out value="${branches.getEmailId()}" /></td>
										<td><c:out value="${branches.address.address}" /></td>
										<td><c:out value="${branches.address.city}" /></td>
										<td><c:out value="${branches.address.province}" /></td>
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
