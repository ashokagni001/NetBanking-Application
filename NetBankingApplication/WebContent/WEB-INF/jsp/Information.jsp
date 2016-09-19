<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${message != null}">
	<script type="text/javascript">
		alert("<c:out value='${message}'/>");
		window.location.href = "login.jsp";
	</script>
</c:if>
