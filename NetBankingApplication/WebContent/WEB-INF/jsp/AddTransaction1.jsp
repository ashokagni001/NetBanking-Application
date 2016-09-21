
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
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>NEW TRANSACTION</title>
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
		<c:import url="TopHead.jsp" />
		<div class="col-md-12 main-container">
			<div class="col-md-3 sidemenu">
				<c:import url="CustomerSideMenu.jsp" />
			</div>
			<div class="col-md-9">
				<div class="col-md-12">
					<div class="tex text-center">
						<h4>NEW TRANSACTION</h4>
					</div>
					<br />
				</div>
				<br />
				<form method="get" action="insertTransaction">
				<c:set value="${customerAccount}" var="customerAccount" />
					<div class="row">
						<div class="form-group">
							<label class="control-label col-md-4" for="creditAccountNumber">CREDIT
								ACCOUNT NUMBER </label>
							<div class="col-md-8">
							<td><input name="creditAccountNumber" value="<c:out value="${customerAccount.getAccountNumber()}"/>" readonly/></td>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group">
							<label class="control-label col-md-4" for="creditAccountNumber">CREDIT
								CUSTOMER NAME </label>
							<div class="col-md-8">
							<td><input type="text" value="<c:out value="${customerAccount.getCustomer().getName()}"/>" readonly/></td>
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
								<input type="submit" name="insertTransaction" value="PROCEED"
									required />
							</div>
						</div>
						<input type="hidden" name="customerId"
							value=<c:out value="${sessionScope['id']}"/> />
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
