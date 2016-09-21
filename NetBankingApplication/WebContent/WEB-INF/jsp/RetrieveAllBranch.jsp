<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${sessionScope['id']== null}">
	<c:redirect url="login" />
</c:if>
<c:if test="${sessionScope['role']!= 'approver'}">
	<c:redirect url="userHomePage" />
</c:if>
<c:if test="${message != null}">
	<script type="text/javascript">
		alert("<c:out value='${message}'/>");
		windows.location.reload();
	</script>
</c:if>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>VIEW BRANCH</title>
<link href="css/bootstrap.css" rel="styleSheet">
<!-- MetisMenu CSS -->
<link href="css/metisMenu.min.css" rel="stylesheet">
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/metisMenu.min.js"></script>
<link href="css/mystyle.css" rel="stylesheet">
</head>

<body>
	<div class="col-md-12 container">
		<c:import url="TopHead.jsp" />
		<div class="col-md-12 main-container">
			<div class="col-md-3 sidemenu">
				<c:import url="SideMenu.jsp" />
			</div>
			<div class="col-md-9">
				<div class="col-md-12">
					<div class="tex text-center">
						<h4>VIEW BRANCH BY IFSC</h4>
					</div>
					<div class="col-md-12 row">
						<form action="getBranch" method="get">
							<div class="row">
								<div class="form-group">
									<label class="control-label col-md-3" for="ifsc">BRANCH
										IFSCODE </label>
									<div class="col-md-9">
										<input type="text" name="ifsc" placeholder="Enter IFSCode"
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
													name="viewBranch" value="VIEW" />
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
									<th>DALETE</th>
								</tr>
								<c:set value="${branch}" var="branch" />
								<tr>
									<td align="center"><c:out value="${branch.getIFSCode()}" /></td>
									<td align="center"><c:out value="${branch.getEmailId()}" /></td>
									<c:set value="${branch.getAddress()}" var="address" />
									<td align="center"><a
										href="viewBranchAddress?addressId=<c:out value="${address.addressId}"/>"
										style="color: blue">VIEW</a></td>
									<td><a
										href="deleteBranchById?ifsc=<c:out value="${branches.getIFSCode()}"/>"
										style="color: blue">Delete</a></td>
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
									<th>ACTION</th>
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
										<td><a
											href="viewBranchAddress?addressId=<c:out value="${address.addressId}"/>"
											style="color: blue">VIEW</a></td>
										<td><a
											href="deleteBranchById?ifsc=<c:out value="${branches.getIFSCode()}"/>"
											style="color: blue">Delete</a></td>
									</tr>
									<%
									    sno++;
									%>
								</c:forEach>
							</table>
						</div>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
