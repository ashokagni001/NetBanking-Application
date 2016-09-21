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
<title>ALLOCATE USER ROLE</title>
<link href="css/bootstrap.css" rel="styleSheet">
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
						<h4>ALLOCATE USER TO ROLE</h4>
					</div>
					<br />
					<c:if test="${customers != null}">
						<div class="col-md-12 sizetable">
							<table class="table table-striped table-bordered table-hover"
								id="dataTables-example">
								<tr>
									<th>S.NO</th>
									<th>CUSTOMER ID</th>
									<th>NAME</th>
									<th>AGE</th>
									<th>DOB</th>
									<th>GENDER</th>
									<th>MOB.NUMBER</th>
									<th>EMAIL</th>
									<th>ACCOUNT NUMBER</th>
									<th colspan="2" align="center" height="30" width="100">ADDRESS</th>
								</tr>
								<%
								    int sno = 1;
								%>
								<c:forEach items="${customers}" var="customers">
									<tr>
										<td><c:out value="<%=sno%>" /></td>
										<td><c:out value="${customers.getCustomerId()}" /></td>
										<td><c:out value="${customers.getName()}" /></td>
										<td><c:out value="${customers.getAge()}" /></td>
										<td><c:out value="${customers.getDob()}" /></td>
										<td><c:out value="${customers.getGender()}" /></td>
										<td><c:out value="${customers.getMobileNumber()}" /></td>
										<td><c:out value="${customers.getEmail()}" /></td>
										<td><c:out value="${customers.getAccountNumber()}" /></td>
										<c:set value="${customers.getAddress()}" var="address" />
										<td><a
											href="viewCustomerAddress?addressId=<c:out value="${address.addressId}"/>"
											style="color: blue">VIEW</a></td>
									</tr>
									<%
									    sno++;
									%>
								</c:forEach>
							</table>
						</div>
					</c:if>
					<div class="col-md-12 row">
						<form method="get" action="insertRole">
							<div class="row">
								<div class="form-group">
									<label class="control-label col-md-2" for="customerId">CUSTOMER
										ID </label>
									<div class="col-md-10">
										<input type="text" name="customerId"
											placeholder="Enter customerId" required />
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-group">
									<label class="control-label col-md-2" for="role">SELECT
										ROLE </label>
									<div class="col-md-10">
										<select name="role">
											<c:forEach items="${roles}" var="role">
												<option value="${role.roleId}">${role.roleName}</option>
											</c:forEach>
										</select>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-group">
									<div class="col-md-offset-2 col-md-10">
										<div class="col-md-3">
											<center>
												<input class="btn btn-success" type="submit" name="add"
													value="ALLOCATE" />
											</center>
										</div>
									</div>
								</div>
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
			</div>
		</div>
	</div>
</body>
</html>
