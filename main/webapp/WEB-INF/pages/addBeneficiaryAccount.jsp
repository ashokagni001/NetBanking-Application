<%@ include file="/common/taglibs.jsp"%>
<html>
<body>
	<div class="col-md-12">
		<div class="tex text-center">
			<h4>ADD NEW BENEFICIARY ACCOUNT</h4>
		</div>
		<c:if test="${message != null}">
			<script type="text/javascript">
				alert("<c:out value='${message}'/>");
				windows.location.reload();
			</script>
		</c:if>
		<div class="col-md-12 row">
			<form method="get" action="addBeneficiary">
				<div class="row">
					<div class="form-group">
						<label class="control-label col-md-4" for="accountNumber">CREDIT
							ACCOUNT NUMBER </label>
						<div class="col-md-8">
							<input type="number" name="accountNumber"
								placeholder="Enter creditAccountNumber" required />
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group">
						<label class="control-label col-md-4" for="IFSCode">BRANCH
							IFSC CODE </label>
						<div class="col-md-8">
							<input type="text" name="IFSCode"
								placeholder="Enter credit IFSCode" required /> Ex:I2I0BK****
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group">
						<div class="col-md-offset-4 col-md-8">
							<div class="col-md-12">
								<div class="col-md-7">
									<input class="btn btn-success" type="submit" name="submit"
										value="ADD" />
								</div>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	</div>
	</div>
	</div>
</body>
</html>