<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${sessionScope['id']== null}">
	<c:redirect url="login" />
</c:if>
<c:if test="${sessionScope['role']!= 'user'}">
	<c:redirect url="approverHomePage" />
</c:if>
<html>
<head>
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
				<c:import url="CustomerSideMenu.jsp" />
			</div>
			<div class="col-md-9">
				<div class="col-md-12">

					<center>
						<h2>ALL BENEFICIARIES ACCOUNTS</h2>
					</center>
					<c:if test="${message != null}">
						<script type="text/javascript">
							alert("<c:out value='${message}'/>");
							windows.location.reload();
						</script>
					</c:if>
					<c:if test="${beneficiaries != null}">
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
							    <td>
							    <c:set
										value="${beneficiaries.getCustomerAccountNumber().getCustomer()}" var="customer"/>
								<c:out value="${customer.getName()}"/>
								</td>
								<td><a href="addTransaction1?customerAccountNumber=<c:out value="${beneficiaries.getCustomerAccountNumber().getAccountNumber()}"/>">
								<c:out value="${beneficiaries.getCustomerAccountNumber().getAccountNumber()}" /></a>	
								</td>
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
