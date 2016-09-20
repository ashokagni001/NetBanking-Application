<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${sessionScope['id']== null}">
	<c:redirect url="login" />
</c:if>
<c:if test="${sessionScope['role']!= 'approver'}">
	<c:redirect url="userHomePage" />
</c:if>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>ADD NEW ACCOUNT</title>
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
				<c:import url="SideMenu.jsp" />
			</div>
			<div class="col-md-9">
				<div class="col-md-12">
					<div class="tex text-center">
						<h4>ADD NEW ACCOUNT</h4>
					</div>
					<br />
					<form method="post" action="addAccount">
						<div class="row">
							<div class="form-group">
								<label class="control-label col-sm-2" for="accountNumber">ACCOUNT
									NUMBER </label>
								<div class="col-sm-10">
									<input type="number" name="accountNumber"
										placeholder="Enter accountNumber" required />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group">
								<label class="control-label col-md-2" for="ifscode">
									IFSC CODE </label>
								<div class="col-md-10">
									<input type="text" name="ifscode"
										placeholder="Enter bank ifscode" required />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group">
								<label class="control-label col-md-2" for="balance">AMOUNT
								</label>
								<div class="col-md-10">
									<input type="balance" name="balance" placeholder="Enter Amount"
										required />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group">
								<label class="control-label col-md-2" for="accounttype">ACCOUNT
									TYPE </label>
								<div class="col-md-10">
									<select name="accounttype">
										<option value="Current">Current</option>
										<option value="Saving">Saving</option>
									</select>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group">
								<div class="col-md-offset-2 col-md-10">
									<br /> <input type="submit" name="addaccount" value="ADD" />
								</div>
							</div>
						</div>
					</form>
					<c:if test="${branches != null}">
						<div class="col-md-12 sizetable">
							<table class="table table-striped table-bordered table-hover"
								id="dataTables-example">
								<tr>
									<th>S.NO</th>
									<th>IFSC CODE</th>
									<th>EMAIL</th>
								</tr>
								<%
								    int sno = 1;
								%>
								<c:forEach items="${branches}" var="branches">
									<tr>
										<td><c:out value="<%=sno%>" /></td>
										<td><c:out value="${branches.getIFSCode()}" /></td>
										<td><c:out value="${branches.getEmailId()}" /></td>
									</tr>
									<%
									    sno++;
									%>
								</c:forEach>
							</table>
						</div>
					</c:if>
				</div>
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
