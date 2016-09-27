<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
</head>
<title>ADD NEW BRANCH</title>
</head>

<body>
	<div class="tex text-center">
		<h4>ADD NEW BRANCH</h4>
	</div>
	<div class="col-md-12 row">
		<form:form commandName="branch" method="post" action="insertBranch"
			modelAttribute="Branch">
			<div class="row">
				<div class="form-group">
					<label class="control-label col-md-3" for="emailId">BRANCH
						EMAIL ID </label>
					<div class="col-md-9">
						<form:input path="emailId" placeholder="Enter EMAIL Id" />
					</div>
				</div>
			</div>
			<div>
				<legend class="accordion-heading">
					<a data-toggle="collapse" href="#collapse-address"><fmt:message
							key="address" /></a>
				</legend>
				<div id="collapse-address" class="accordion-body collapse">
				    <div class="row">
					    <div class="form-group">
					        <label class="control-label col-md-3" for="address.country">STREET
							</label>
					        <div class="col-md-9">
						    <form:input cssClass="form-control" path="address.address"
							id="address" />
							</div>
					    </div>
				</div>

					<div class="row">
						<div class="form-group">
							<label class="control-label col-md-3" for="address.country">COUNTRY
							</label>
							<div class="col-md-9">
								<form:select path="address.country" class="countries" id="countryId">
									<form:option value="">Select Country</form:option>
								</form:select>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group">
							<label class="control-label col-md-3" for="address.province">STATE </label>
							<div class="col-md-9">
								<form:select path="address.province" class="states" id="stateId">
									<form:option value="">Select State</form:option>
								</form:select>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group">
							<label class="control-label col-md-3" for="address.city">CITY </label>
							<div class="col-md-9">
								<form:select path="address.city" class="cities" id="cityId">
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
							<label class="control-label col-md-3" for="address.city">PIN CODE
							</label>
                            <div class="col-sm-3 form-group">
							    <form:input cssClass="form-control" path="address.postalCode"
								id="postalCode" />
						    </div>
						</div>
					</div>
				</div>
				</div>
			<div class="row">
				<div class="form-group">
					<div class="col-md-offset-3 col-md-9">
						<div class="col-md-4">
							<center>
								<input class="btn btn-success" type="submit" name="addBranch"
									value="ADD" />
							</center>
						</div>
					</div>
				</div>
			</div>
		</form:form>
	</div>
	</div>
	</div>
	</div>
	</div>
</body>
</html>