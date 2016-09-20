<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${sessionScope['id']== null}">
	<c:redirect url="login" />
</c:if>
<c:if test="${sessionScope['role']!= 'approver'}">
	<c:redirect url="userHomePage" />
</c:if>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>VIEW ACCOUNT BY BRANCH</title>
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
						<h4>VIEW ACCOUNT BY BRANCH</h4>
					</div>
					<form action="getAccount" method="get">
						<div class="row">
							<div class="form-group">
								<label class="control-label col-md-3" for="ifsc">BRANCH IFSCODE </label>
								<div class="col-md-9">
									<input type="text" name="ifsc" placeholder="Enter IFSCode"
										required />
										Ex.I2I0BK****
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group">
								<div class="col-md-offset-3 col-md-9">
									<br /> <input type="submit" name="viewAccount" value="VIEW" />
								</div>
							</div>
						</div>
					</form>
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
									<th>CUSTOMER ID</th>
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
										<td><c:set value="${accounts.getCustomer()}"
												var="customer" /> <a
											href="getCustomer?customerId=<c:out value="${customer.customerId}"/>"
											style="color: blue"><c:out value="${customer.customerId}" /></a></td>
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
				</div>
			</div>
		</div>
	</div>
</body>
</html>
