<%@ include file="/common/taglibs.jsp"%>
<html>
<body>
			<div class="col-md-9">
				<div class="col-md-12">
					<div class="tex text-center">
						<h4>NEW TRANSACTION</h4>
					</div>
				</div>
				<div class="col-md-12 row">
					<form method="get" action="insertTransaction">
						<div class="row">
							<div class="form-group">
								<label class="control-label col-md-4" for="creditAccountNumber">CREDIT
									ACCOUNT NUMBER </label>
								<div class="col-md-8">
									<input type="number" name="creditAccountNumber"
										placeholder="Enter creditAccountNumber" required />
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
									<div class="col-md-4">
										<center>
											<input class="btn btn-success" type="submit"
												name="insertTransaction" value="PROCEED" required />
										</center>
									</div>
								</div>
							</div>
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
			</div>
</body>
</html>
