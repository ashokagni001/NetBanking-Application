<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${sessionScope['id']== null}">
	<c:redirect url="login" />
</c:if>
<c:if test="${sessionScope['role']!= 'approver'}">
	<c:redirect url="userHomePage" />
</c:if>
<html>
<head>
<title>VIEW NOTIFICATION</title>
<link href="css/bootstrap.css" rel="styleSheet">
<!-- MetisMenu CSS -->
<link href="css/metisMenu.min.css" rel="stylesheet">
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/metisMenu.min.js"></script>
<link href="css/mystyle.css" rel="stylesheet">
</head>
<body>
	<c:if test="${message != null}">
		<script type="text/javascript">
			alert("<c:out value='${message}'/>");
			windows.location.reload();
		</script>
	</c:if>

	<div class="col-md-12 container">
		<c:import url="TopHead.jsp" />

		<div class="col-md-12 main-container">
			<div class="col-md-3 sidemenu">
				<c:import url="SideMenu.jsp" />
			</div>
			<div class="col-md-9">
				<div class="col-md-12">
					<c:if test="${beneficiaryNotifications != null }">
						<div class="tex text-center">
							<h4>ALL BENEFICIARY NOTIFIACTIONS</h4>
						</div>

						<div class="col-md-12 sizetable">
							<table class="table table-striped table-bordered table-hover"
								id="dataTables-example">
								<tr>
									<th>S.NO</th>
									<th>BENEFICIARY ID</th>
									<th>BENEFICIARY CUSTOMER NUMBER</th>
									<th>CUSTOMER ACCOUNT NUMBER</th>
									<th colspan="2" align="center" height="30" width="100">TRANSACTION
										ACTION</th>
								</tr>
								<%
								    int sno = 1;
								%>
								<c:forEach items="${beneficiaryNotifications}"
									var="beneficiaryNotifications">
									<tr>
										<td align="center"><c:out value="<%=sno%>" /></td>
										<td align="center"><c:out
												value="${beneficiaryNotifications.getId()}" /></td>
										<td align="center"><c:set
												value="${beneficiaryNotifications.getBeneficiaryAccountNumber()}"
												var="beneficiaryCustomer" /> <a
											href="viewCustomerAccount?accountNumber=<c:out value="${beneficiaryCustomer.accountNumber}"/>"
											style="color: blue"><c:out
													value="${beneficiaryCustomer.accountNumber}" /></a></td>
										<td align="center"><c:set
												value="${beneficiaryNotifications.getCustomerAccountNumber()}"
												var="customerAccount" /> <a
											href="viewCustomerAccount?accountNumber=<c:out value="${customerAccount.accountNumber}"/>"
											style="color: blue"><c:out
													value="${customerAccount.accountNumber}" /></a></td>
										<td align="center" height="30" width="100"><a
											href="beneficiaryRequestSuccess?id=<c:out value="${beneficiaryNotifications.getId()}"/>">
												PERMISSION</a></td>
										<td align="center" height="30" width="100"><a
											href="beneficiaryRequestCancel?id=<c:out value="${beneficiaryNotifications.getId()}"/>">
												IGNORE</a></td>
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
