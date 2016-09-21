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
						<div class="col-md-12 row">
							<c:if test="${BranchAddress != null }">
								<form:form method="post" action="address"
									modelAttribute="BranchAddress">
									<div class="row">
										<div class="form-group">
											<label class="control-label col-md-3" for="street">STREET
											</label>
											<div class="col-md-9">
												<form:input path="street" placeholder="street" />
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="control-label col-md-3" for="country">COUNTRY
											</label>
											<div class="col-md-9">
												<form:select path="country" class="countries" id="countryId">
													<form:option value="">Select Country</form:option>
												</form:select>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="control-label col-md-3" for="state">STATE
											</label>
											<div class="col-md-9">
												<form:select path="state" class="states" id="stateId">
													<form:option value="">Select State</form:option>
												</form:select>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="control-label col-md-3" for="city">CITY
											</label>
											<div class="col-md-9">
												<form:select path="city" class="cities" id="cityId">
													<form:option value="">Select City</form:option>
												</form:select>
											</div>
										</div>
									</div>
									<script
										src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
									<script src="http://iamrohit.in/lab/js/location.js"></script>
									<div class="row">
										<div class="form-group">
											<label class="control-label col-md-3" for="pincode">PINCODE
											</label>
											<div class="col-md-9">
												<form:input path="pincode" placeholder="pincode" />
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<div class="col-md-offset-3 col-md-9">
												<div class="col-md-4">
													<center>
														<input class="btn btn-success" type="submit"
															name="addAddress" value="ADD" />
													</center>
												</div>
											</div>
										</div>
									</div>
								</form:form>
							</c:if>
						</div>
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
			<div class="col-md-12 row">
				<c:if test="${Address != null }">
					<form:form method="post" action="customerAddress"
						modelAttribute="Address">
						<div class="row">
							<div class="form-group">
								<label class="control-label col-md-3" for="street">STREET
								</label>
								<div class="col-md-9">
									<form:input path="street" placeholder="street" />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group">
								<label class="control-label col-md-3" for="country">COUNTRY
								</label>
								<div class="col-md-9">
									<form:select path="country" class="countries" id="countryId">
										<form:option value="">Select Country</form:option>
									</form:select>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group">
								<label class="control-label col-md-3" for="state">STATE
								</label>
								<div class="col-md-9">
									<form:select path="state" class="states" id="stateId">
										<form:option value="">Select State</form:option>
									</form:select>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group">
								<label class="control-label col-md-3" for="city">CITY </label>
								<div class="col-md-9">
									<form:select path="city" class="cities" id="cityId">
										<form:option value="">Select City</form:option>
									</form:select>
								</div>
							</div>
						</div>
						<script
							src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
						<script src="http://iamrohit.in/lab/js/location.js"></script>
						<div class="row">
							<div class="form-group">
								<label class="control-label col-md-3" for="pincode">PINCODE
								</label>
								<div class="col-md-9">
									<form:input path="pincode" placeholder="pincode" />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group">
								<div class="col-md-offset-3 col-md-9">
									<div class="col-md-4">
										<center>
											<input class="btn btn-success" type="submit"
												name="addAddress" value="ADD" />
										</center>
									</div>
								</div>
							</div>
						</div>
					</form:form>
				</c:if>
			</div>
		</div>
	</c:if>
</body>
</html>