<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${sessionScope['id']== null}">
    <c:redirect url="login"/>
</c:if>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    <center>
	<tr>
		<td align="center" height="40" width="350"><a href="GetCustomer">FETCH
				CUSTOMER</a></td>
	</tr>
	<tr>
		<td align="center" height="40" width="350"><a
			href="getMiniStatementByCustomerId">CUTOMER MINI STATEMENT</a></td>
	</tr>
<br/><a href="logoutController" style="width:300px;"> LOGOUT</a>
</center>
</body>
</html>