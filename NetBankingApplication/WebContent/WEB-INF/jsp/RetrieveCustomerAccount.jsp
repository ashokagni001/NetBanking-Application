<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${sessionScope['id']== null}">
	<c:redirect url="login" />
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
<title>VIEW CUSTOMER ACCOUNT</title>
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
				<c:if test="${sessionScope['role'] == 'approver'}">
					<c:import url="SideMenu.jsp" />
				</c:if>
				<c:if test="${sessionScope['role'] == 'user'}">
					<c:import url="CustomerSideMenu.jsp" />
				</c:if>
			</div>
			<div class="col-md-9">
				<div class="col-md-12 sizetable">
					<table class="table table-striped table-bordered table-hover"
						id="dataTables-example">
						<tr>
							<th>ACCOUNT NUMBER</th>
							<th>USER ID</th>
							<th>BRANCH ID</th>
							<th>BALANCE</th>
							<th>ACCOUNT TYPE</th>
						</tr>
						<c:set value="${accountDetail}" var="accountDetail" />
						<tr>
							<td align="center"><c:out
									value="${accountDetail.getAccountNumber()}" /></td>
							<c:set value="${accountDetail.getCustomer()}" var="customer" />
							<td align="center"><a
								href="getCustomer?customerId=<c:out value="${customer.customerId}"/>"
								style="color: blue"><c:out value="${customer.customerId}" /></a></td>
							<c:set value="${accountDetail.getBranch()}" var="branch" />
							<td align="center"><c:out value="${branch.IFSCode}" /></td>
							<td align="center"><c:out
									value="${accountDetail.getBalance()}" /></td>
							<td align="center"><c:out
									value="${accountDetail.getAccountType()}" /></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>