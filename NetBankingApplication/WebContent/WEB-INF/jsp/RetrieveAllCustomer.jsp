<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${sessionScope['id']== null}">
	<c:redirect url="login" />
</c:if>
<c:if test="${message != null}">
	<script type="text/javascript">
		alert("<c:out value='${message}'/>");
		windows.location.reload();
	</script>
</c:if>
<html>
<head>
<title>VIEW CUSTOMER</title>
<link href="css/bootstrap.css" rel="styleSheet">
<!-- MetisMenu CSS -->
<link href="css/metisMenu.min.css" rel="stylesheet">
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/metisMenu.min.js"></script>
<link href="css/mystyle.css" rel="stylesheet">
</head>
<body>
	<c:if test="${sessionScope['role'] == 'approver'}">
		<div class="col-md-12 container">
			<c:import url="TopHead.jsp" />
			<div class="col-md-12 main-container">
				<div class="col-md-3 sidemenu">
					<c:import url="SideMenu.jsp" />
				</div>
				<div class="col-md-9">
					<div class="col-md-12">
						<div class="tex text-center">
							<h4>CUSTOMER INFORMATION</h4>
						</div>
						<c:if test="${customer != null}">
							<div class="col-md-12 sizetable">
								<table class="table table-striped table-bordered table-hover"
									id="dataTables-example">
									<tr>
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
									<c:set value="${customer}" var="customer" />
									<tr>
										<td><c:out value="${customer.getCustomerId()}" /></td>
										<td><c:out value="${customer.getName()}" /></td>
										<td><c:out value="${customer.getAge()}" /></td>
										<td><c:out value="${customer.getDob()}" /></td>
										<td><c:out value="${customer.getGender()}" /></td>
										<td><c:out value="${customer.getMobileNumber()}" /></td>
										<td><c:out value="${customer.getEmail()}" /></td>
										<c:set value="${customer.getAccountNumber()}" var="accountNum" />
										<td align="center"><a
											href="viewCustomerAccount?accountNumber=<c:out value="${accountNum}"/>"
											style="color: blue"><c:out value="${accountNum}" /></a></td>
										<c:set value="${customer.getAddress()}" var="address" />
										<td><a
											href="viewCustomerAddress?addressId=<c:out value="${address.addressId}"/>"
											style="color: blue">VIEW</a></td>
									</tr>
								</table>
							</div>
						</c:if>
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
											<c:set value="${customers.getAccountNumber()}"
												var="accountNum" />
											<td align="center"><a
												href="viewCustomerAccount?accountNumber=<c:out value="${accountNum}"/>"
												style="color: blue"><c:out value="${accountNum}" /></a></td>
											<td><c:set value="${customers.getAddress()}"
													var="address" />
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
							<div class="tex text-center">
								<div class="col-md-12" style="padding-top: 10px;">
									<h5>IF YOU WANT ANY ONE CUSTOMER DETAIL</h5>
								</div>
							</div>
							<div class="col-md-12 row">
								<form action="getCustomer" method="get">
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
											<div class="col-md-offset-2 col-md-10">
												<div class="col-md-3">
													<center>
														<input class="btn btn-success" type="submit"
															name="viewCustomer" value="VIEW" />
													</center>
												</div>
											</div>
										</div>
									</div>
								</form>
							</div>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</c:if>
	<c:if test="${sessionScope['role'] == 'user'}">
		<div class="col-md-12 container">
			<c:import url="TopHead.jsp" />
			<div class="col-md-12 main-container">
				<div class="col-md-3 sidemenu">
					<c:import url="CustomerSideMenu.jsp" />
				</div>
				<div class="col-md-9">
					<div class="col-md-12">
						<div class="tex text-center">
							<h4>CUSTOMER INFORMATION</h4>
						</div>
						<div class="col-md-12 sizetable">
							<table class="table table-striped table-bordered table-hover"
								id="dataTables-example">
								<tr>
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
								<c:set value="${customer}" var="customer" />
								<tr>
									<td><c:out value="${customer.getCustomerId()}" /></td>
									<td><c:out value="${customer.getName()}" /></td>
									<td><c:out value="${customer.getAge()}" /></td>
									<td><c:out value="${customer.getDob()}" /></td>
									<td><c:out value="${customer.getGender()}" /></td>
									<td><c:out value="${customer.getMobileNumber()}" /></td>
									<td><c:out value="${customer.getEmail()}" /></td>
									<c:set value="${customer.getAccountNumber()}" var="accountNum" />
									<td align="center"><a
										href="viewCustomerAccount?accountNumber=<c:out value="${accountNum}"/>"
										style="color: blue"><c:out value="${accountNum}" /></a></td>
									<c:set value="${customer.getAddress()}" var="address" />
									<td><a
										href="viewCustomerAddress?addressId=<c:out value="${address.addressId}"/>">VIEW</a></td>
								</tr>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</c:if>
</body>
</html>