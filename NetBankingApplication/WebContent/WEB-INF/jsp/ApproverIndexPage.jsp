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
<title></title>
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
			    <div style="width:50%;">
                <img src="image/bankImage4.jpg" width="204%" height="100%"><br/>
                </div>
			</div>
		</div>
		</div>
</body>

</html>
