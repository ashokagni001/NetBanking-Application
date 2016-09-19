<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${sessionScope['id']== null}">
	<c:redirect url="login" />
</c:if>
<c:if test="${sessionScope['role']!= 'user'}">
	<c:redirect url="approverIndexPage" />
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

.position {
	position: absolute;
	left: 200px;
	font-size: 18px;
}

.pos {
	position: absolute;
	right: 100px;
	top: -20px;
	height: 70px;
	width: 50px;
}
</style>
</head>
<body>
	<div class="well">
		<font size="20"><marquee behavior="alternate">I2I NETBANKING</marquee></font>
	</div>

	<div class="position">
		<h2>INSERT TRANSACTION</h2>
		<br />
		<form method="post" action="insertTransaction">
			<div class="container">
				<form class="form-horizontal">
					<div class="form-group">
						<label class="control-label col-sm-2" for="creditAccountNumber">creditAccountNumber</label>
						<div class="col-sm-10">
							<input type="number" name="creditAccountNumber"
								placeholder="Enter creditAccountNumber" required="required">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="ifscode">credit
							ifscode </label>
						<div class="col-sm-10">
							<input type="text" name="ifscode"
								placeholder="Enter credit ifscode" required="required">
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="amount" required="required">Enter
								your Amount</label>
							<div class="col-sm-10">
								<input type="number" name="amount"
									placeholder="Enter your Amount" required="required"/> </br>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<input type="submit" name="insertTransaction"
									value="insertTransaction" />
							</div>
						</div>
				</form>
				<input type="hidden" name="customerId"
					value=<c:out value="${sessionScope['id']}"/> />

			</div>
		</form>
	</div>
	<c:if test="${message != null}">
			<script type="text/javascript">
				alert("<c:out value='${message}'/>");
				windows.location.reload();
			</script>
		</c:if>
	<a class="btn btn-link" href="userHomePage">Go to main page </a>
	<div class="pos">
		<a class="btn btn-danger" href="logoutController"> LOGOUT</a>
	</div>
</body>
</html>