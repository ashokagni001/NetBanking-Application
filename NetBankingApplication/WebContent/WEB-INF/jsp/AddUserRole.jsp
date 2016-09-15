<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${sessionScope['id']== null}">
	<c:redirect url="login" />
</c:if>
<c:if test="${sessionScope['role']!= 'approver'}">
	<c:redirect url="userHomePage" />
</c:if>
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
	<h2>ADD USER ROLE</h1>
	<center>
		<h3>INSERT DETAIL</h3><br />
		<table>
			<form method="get" action="insertRole" />
			<tr>
				<td>CUSTOMER ID :<input type="text" name="customerId"
					placeholder="Enter the customerId" required></td>
			</tr>
			<tr>
				<td>SELECT ROLE : <select name="role">
						<c:forEach items="${roles}" var="role">
							<option value="${role.roleId}">${role.roleName}</option>
						</c:forEach>
				</select>
				</td>
			</tr>
			<tr>
				<td><input type="submit" name="add" value="Allocate"></td>
			</tr>
		</table>
		</form>
		</br>
		</br>
		</center>
		<c:if test="${message != null}">
			<script type="text/javascript">
				alert("<c:out value='${message}'/>");
				windows.location.reload();
			</script>
		</c:if>
		<div class = "pos">
        <a class = "btn btn-link" href="BranchIndex">Go to main page </a></br>
		</br> 
		<a class = "btn btn-danger" href="logoutController" style="width: 100"> LOGOUT</a>
		</div>
	
</html>