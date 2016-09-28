<%@ include file="/common/taglibs.jsp"%>
<html>
<body>
			<div class="col-md-9">
				<div class="col-md-12">
					<div class="tex text-center">
						<h4>NEW TRANSACTION</h4>
					</div>
				</div>
				<form method="get" action="insertTransaction">
				<c:set value="${customerAccount}" var="customerAccount" />
					<div class="row">
						<div class="form-group">
							<label class="control-label col-md-4" for="creditAccountNumber">CREDIT
								ACCOUNT NUMBER </label>
							<div class="col-md-8">
							<input name="creditAccountNumber" value="<c:out value="${customerAccount.getAccountNumber()}"/>" readonly/>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group">
							<label class="control-label col-md-4" for="creditAccountNumber">CREDIT
								CUSTOMER NAME </label>
							<div class="col-md-8">
							<input type="text" value="<c:out value="${customerAccount.getUser().getFirstName()}"/>" readonly/>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group">
							<label class="control-label col-md-4" for="amount">ENTER
								YOUR AMOUNT</label>
							<div class="col-md-8">
								<input type="number" name="amount"
									placeholder="Enter your Amount" required />
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group">
							<div class="col-md-offset-4 col-md-8">
								<input class="btn btn-success" type="submit" name="insertTransaction" value="PROCEED"
									/>
							</div>
						</div>
					</div>
				</form>
				<c:if test="${message != null}">
					<script type="text/javascript">
						alert("<c:out value='${message}'/>");
						windows.location.reload();
					</script>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>
