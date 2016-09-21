<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${message != null}">
	<script type="text/javascript">
		alert("<c:out value='${message}'/>");
		windows.location.reload();
	</script>
</c:if>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>REGISTER</title>
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
		<c:import url="RegHead.jsp" />
		<br />
		<div class="tex text-center">
			<h3>REGISTER YOUR DETAIL FOR NETBANKING</h3>
		</div>
		<div class="col-md-12 row">
			<form:form method="post" action="register" modelAttribute="Customer">
				<div class="row">
					<div class="form-group">
						<label class="control-label col-md-3" for="name">NAME </label>
						<div class="col-md-9">
							<form:input path="name" placeholder="Enter the name"
								required="required" />
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group">
						<label class="control-label col-md-3" for="dob">DATE OF
							BIRTH</label>
						<div class="col-md-9">
							<form:input path="dob" placeholder="Enter the dob"
								required="required" />
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group">
						<label class="control-label col-md-3" for="city">GENDER </label>
						<div class="col-md-9">
							<form:select path="gender">
								<form:option value="Male">Male</form:option>
								<form:option value="Female">Female</form:option>
							</form:select>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group">
						<label class="control-label col-md-3" for="mobileNumber">DATE
							OF BIRTH</label>
						<div class="col-md-9">
							<form:input path="mobileNumber"
								placeholder="Enter the mobileNumber" required="required" />
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group">
						<label class="control-label col-md-3" for="accountNumber">ACCOUNT
							NUMBER</label>
						<div class="col-md-9">
							<form:input path="accountNumber"
								placeholder="Enter the accountNumber" required="required" />
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group">
						<label class="control-label col-md-3" for="email">EMAIL ID</label>
						<div class="col-md-9">
							<form:input path="email" placeholder="Enter the emailId"
								required="required" />
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group">
						<div class="col-md-offset-3 col-md-9">
							<div class="col-md-3">
								<center>
									<input class="btn btn-success" type="submit" name="submit"
										value="NEXT" />
								</center>
							</div>
						</div>
					</div>
				</div>
			</form:form>
		</div>
	</div>
</body>
</html>