<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${sessionScope['id']== null}">
	<c:redirect url="login" />
</c:if>
<c:if test="${sessionScope['role']!= 'user'}">
	<c:redirect url="approverHomePage" />
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
<title>ADD NEW BRANCH</title>
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
					<center>
						<h4>ADD NEW BENEFICIARY ACCOUNT</h4>
					</center>
					<c:if test="${message != null}">
						<script type="text/javascript">
							alert("<c:out value='${message}'/>");
							windows.location.reload();
						</script>
					</c:if>
		    <form method="get" action="addBeneficiaryAccount">
		       <table>
				<tr>
					<td>CUSTOMER ACCOUNT NUMBER <input type="text" name="accountNumber" placeholder="Account Number" required></td>
				</tr>
			    <tr>
					<td>BRANCH IFSC CODE <input type="text" name="IFSCode" placeholder="IFSC CODE" required></td>
			    </tr>
			    <tr>
					<td><input type="submit" name="submit" value="ADD"></td>
				</tr>
		</table>
		</form>
    </body>
</html>