<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${sessionScope['id']== null}">
	<c:redirect url="login" />
</c:if>
<c:if test="${sessionScope['role']!= 'user'}">
	<c:redirect url="approverIndexPage" />
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
<title>VIEW MINI DETAIL</title>
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
					<div class="tex text-center">
						<h4>MINISTATMENTS</h4>
					</div>
					<div class="col-md-12 sizetable">
						<table class="table table-striped table-bordered table-hover"
							id="dataTables-example">
							<tr>
								<th>S.NO</th>
								<th>DEBIT ACCOUNT NUMBER</th>
								<th>CREDIT ACCOUNT NUMBER</th>
								<th>AMOUNT</th>
								<th>DATE & TIME</th>
								<th>TRANSACTION STATUS</th>
							</tr>
							<%
							    int sno = 1;
							%>
							<c:forEach items="${miniStatement}" var="miniStatement">
								<tr>
									<td><c:out value="<%=sno%>" /></td>
									<td align="center"><c:set
											value="${miniStatement.getDebitAccount()}" var="accountDebit" />
										<c:out value="${accountDebit.accountNumber}" /></td>
									<td align="center"><c:set
											value="${miniStatement.getCreditAccount()}"
											var="accountCredit" /> <c:out
											value="${accountCredit.accountNumber}" /></td>
									<td align="center"><c:out
											value="${miniStatement.getAmount()}" /></td>
									<td align="center"><c:out
											value="${miniStatement.getDate()}" /></td>
									<td align="center"><c:out
											value="${miniStatement.getStatus()}" /></td>
								</tr>
								<%
								    sno++;
								%>
							</c:forEach>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
