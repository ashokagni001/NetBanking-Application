
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${sessionScope['id']== null}">
	<c:redirect url="login" />
</c:if>
<c:if test="${sessionScope['role']!= 'approver'}">
	<c:redirect url="approverIndexPage" />
</c:if>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>VIEW TRNASCTION BY DATE</title>
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
						<h4>ENTER FROM DATE & TO DATE OF TRANSACTION DETAIL</h4>
					</div>
					<form action="getDates" method="get">
						<div class="row">
							<div class="form-group">
								<label class="control-label col-md-2" for="fromDate">FROM
									DATE </label>
								<div class="col-md-10">
									<input type="date" name="fromDate" placeholder="2016-09-08"
										required />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group">
								<label class="control-label col-md-2" for="toDate">TO
									DATE </label>
								<div class="col-md-10">
									<input type="date" name="toDate" placeholder=" 2016-09-11"
										required />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group">
								<div class="col-md-offset-2 col-md-10">
									<br /> <input type="submit" name="viewTransaction"
										value="VIEW" />
								</div>
							</div>
						</div>
					</form>
					<c:if test="${transactions != null}">
						<div class="tex text-center">
							<h5>TRANSACTION DETAIL</h5>
						</div>
						<div class="col-md-12 sizetable">
							<table class="table table-striped table-bordered table-hover"
								id="dataTables-example">
								<tr>
									<th>S.NO</th>
									<th>TRANSACTION ID</th>
									<th>DEBIT ACCOUNT</th>
									<th>CREDIT ACCOUNT</th>
									<th>AMOUNT</th>
									<th>DATE TIME</th>
									<th>TRANSACTION STATUS</th>
								</tr>
								<%
								    int sno = 1;
								%>
								<c:forEach items="${transactions}" var="transactions">
									<tr>
										<td align="center"><c:out value="<%=sno%>" /></td>
										<td align="center"><c:out value="${transactions.getId()}" /></td>
										<td align="center"><c:set
												value="${transactions.getDebitAccount()}" var="accountDebit" />
											<a
											href="viewCustomerAccount?accountNumber=<c:out value="${accountDebit.accountNumber}"/>"
											style="color: blue"><c:out
													value="${accountDebit.accountNumber}" /></a></td>
										<td align="center"><c:set
												value="${transactions.getCreditAccount()}"
												var="accountCredit" /> <a
											href="viewCustomerAccount?accountNumber=<c:out value="${accountCredit.accountNumber}"/>"
											style="color: blue"></a> <c:out
												value="${accountCredit.accountNumber}" /></td>
										<td align="center"><c:out
												value="${transactions.getAmount()}" /></td>
										<td align="center"><c:out
												value="${transactions.getDate()}" /></td>
										<td align="center"><c:out
												value="${transactions.getStatus()}" /></td>
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
	<c:if test="${message != null}">
		<script type="text/javascript">
			alert("<c:out value='${message}'/>");
			windows.location.reload();
		</script>
	</c:if>
</body>
</html>