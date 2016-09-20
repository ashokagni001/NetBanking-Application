<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>ADD ADDRESS</title>
<link href="css/bootstrap.css" rel="styleSheet">
<!-- MetisMenu CSS -->
<link href="css/metisMenu.min.css" rel="stylesheet">
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/metisMenu.min.js"></script>
<link href="css/mystyle.css" rel="stylesheet">
</head>
<body>
	<c:if test="${sessionScope['id'] != null}">
		<div class="col-md-12 container">
			<c:import url="TopHead.jsp" />
			<div class="col-md-12 main-container">
				<div class="col-md-3 sidemenu">
					<c:import url="SideMenu.jsp" />
				</div>
				<div class="col-md-9">
					<div class="col-md-12">
						<div class="tex text-center">
							<h4>ADD ADDRESS</h4>
						</div>
						<br />
						<c:if test="${BranchAddress != null }">
							<form:form method="post" action="address"
								modelAttribute="BranchAddress">
								<tr>
									<td><form:input path="street" placeholder="street" /></td>
								</tr>
								<form:select path="country" class="countries" id="countryId">
									<form:option value="">Select Country</form:option>
								</form:select>
								<br />
								<br />
								<form:select path="state" class="states" id="stateId">
									<form:option value="">Select State</form:option>
								</form:select>
								<br />
								<br />
								<form:select path="city" class="cities" id="cityId">
									<form:option value="">Select City</form:option>
								</form:select>
								<br />
								<br />
								<script
									src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
								<script src="http://iamrohit.in/lab/js/location.js"></script>
								<tr>
									<td><form:input path="pincode" placeholder="pincode" /></td>
								</tr>
								<br />
								<br />
								<input type="submit" name="addBranch" value="ADD" />
							</form:form>
						<<br />
							<br />
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</c:if>
	<c:if test="${sessionScope['id'] == null}">
		<div class="col-md-12 container">
			<c:import url="RegHead.jsp" />
			<div class="tex text-center">
				<h4>ADD ADDRESS</h4>
			</div>
			<c:if test="${Address != null }">
				<form:form method="post" action="customerAddress"
					modelAttribute="Address">
					<tr>
						<td><form:input path="street" placeholder="street" /></td>
					</tr>
					<br />
					<br />
					<form:select path="country" class="countries" id="countryId">
						<form:option value="">Select Country</form:option>
					</form:select>
					<br />
					<br />
					<form:select path="state" class="states" id="stateId">
						<form:option value="">Select State</form:option>
					</form:select>
					<br />
					<br />
					<form:select path="city" class="cities" id="cityId">
						<form:option value="">Select City</form:option>
					</form:select>
					<br />
					<br />
					<script
						src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
					<script src="http://iamrohit.in/lab/js/location.js"></script>
					<tr>
						<td><form:input path="pincode" placeholder="pincode" /></td>
					</tr>
					<br />
					<br />
					<input type="submit" name="addBranch" value="ADD" />
					<br />
					<br />
				</form:form>
			</c:if>
		</div>
	</c:if>
</body>
</html>