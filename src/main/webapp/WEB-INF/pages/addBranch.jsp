<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>ADD NEW BRANCH</title>
<style type="text/css">
.row {
margin: 3px;
}
</style>
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
						<form:input path="emailId" type ="email" placeholder="Enter EMAIL Id" required = "required"/>
					</div>
				</div>
			</div>
			<div>
				<legend class="accordion-heading">
					<a data-toggle="collapse" href="#collapse-address">ADDRESS</a>
				</legend>
				<div id="collapse-address" class="accordion-body collapse">
					<div class="row">
						<div class="form-group">
							<label class="control-label col-md-3" for="address.country">STREET
							</label>
							<div class="col-md-9">
								<form:input cssClass="form-control" path="address.address"
									id="address" required = "required"/>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-group">
							<label class="control-label col-md-3" for="address.country">COUNTRY
							</label>
							<div class="col-md-9">
								<form:select path="address.country" class="countries"
									id="countryId" required = "required">
									<form:option value="">Select Country</form:option>
								</form:select>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group">
							<label class="control-label col-md-3" for="address.province">STATE
							</label>
							<div class="col-md-9">
								<form:select path="address.province" class="states" id="stateId" required = "required">
									<form:option value="">Select State</form:option>
								</form:select>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group">
							<label class="control-label col-md-3" for="address.city">CITY
							</label>
							<div class="col-md-9">
								<form:select path="address.city" class="cities" id="cityId" required = "required">
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
							<label class="control-label col-md-3" for="address.city">PIN
								CODE </label>
							<div class="col-sm-3 form-group">
								<form:input cssClass="form-control" path="address.postalCode"
									id="postalCode" required = "required"/>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group">
					<div class="col-md-offset-3 col-md-9">
						<div class="col-md-4">
							<input class="btn btn-success" type="submit" name="addBranch"
								value="ADD" />
						</div>
					</div>
				</div>
			</div>
		</form:form>
	</div>
	<c:if test="${message != null}">
		<script type="text/javascript">
			alert("<c:out value='${message}'/>");
			windows.location.reload();
		</script>
	</c:if>
</body>
</html>