<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="well">
		<font size="20"> <marquee behavior="alternate">NETBANKING</marquee>
		</font>
</div>
<html>
    <head>
    <style type="text/css">
    .well {
	background-color: #8ddfe1;
}
</style>
    <head>
<link rel="stylesheet" href="css/bootstrap.css">
<script src="js/bootstrap.js"></script>
<style type="text/css">
.well {
	background-color: #8ddfe1;
}
</style>
</head>
    <title>NET BANKING</title>
</head>
    <body>
        <c:if test="${message != null}">
			<script type="text/javascript">
				alert("<c:out value='${message}'/>");
				windows.location.reload();
			</script>
		</c:if>
		<br/><br/>
		<a href="login">GO TO MAIN PAGE</a>
    </body>
</html>