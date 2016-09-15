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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<html>
<head>
<link rel="stylesheet" href="css/bootstrap.css">
<script src="js/bootstrap.js"></script>
<script src="js/bootstrap.min.js"></script>
<style type="text/css">
.well {
	background-color: #8ddfe1;
}

.pos {
    position: absolute;
    top : 150px;
    right: 0px;
    width: 200px;
    height: 120px;
}
</style>
</head>
<body>
	<div class="well">
		<font size="20"><marquee behavior="alternate">NET
				BANKING</marquee></font>
	</div>
	<center>
		<h1>WELCOME NETBANKING APPLICATION</h1>
		<br />
		<br /> If U Want Customer Operation Open Click On <br />
		<br />
		<a class="btn btn-info" href="userHomePage">OPEN</a> <br />
		<br /> If U Want Admin Operation Open Click On <br />
		<br />
		<a class="btn btn-info" href=approverIndexPage>OPEN</a>
	</center>
	<br />
	<br />
	<div class = "pos">
	<a class = "btn btn-danger" href="logoutController" style="width: 100px;"> LOGOUT</a>
    </div>
</body>
</html>
</body>
</html>