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
		<center>
			<c:import url="TopHead.jsp" />
			<div class="col-md-12">
				<div style="width: 64%; float: left;">
					<br /> <br /> <a href="userHomePage"><img
						src="image/myAccount1.jpg" width="300" height="165" class="imge"><br />
						<br />CUSTOMER OPERATIONS</a>
				</div>
				<div style="width: 28%; hight: 30%; float: left; margin-left: 15px;">
					<br /> <br /> <a href=approverIndexPage><img
						src="image/bank.jpg" width="300" height="165" class="imge">
						<br /> <br />ADMIN OPERATIONS</a>
				</div>
			</div>
		</center>
	</div>
</body>
</html>