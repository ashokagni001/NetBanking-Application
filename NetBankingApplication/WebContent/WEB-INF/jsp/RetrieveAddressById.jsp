
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${sessionScope['id']== null}">
	<c:redirect url="login" />
</c:if>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>VIEW ADDRESS</title>
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
				<c:if test="${sessionScope['role']== 'approver'}">
					<c:import url="SideMenu.jsp" />
				</c:if>
				<c:if test="${sessionScope['role']== 'user'}">
					<c:import url="CustomerSideMenu.jsp" />
				</c:if>
			</div>
			<div class="col-md-9">
				<div class="col-md-12">
					<div class="tex text-center">
						<h4>ADDRESS DETAIL</h4>
					</div>
					<c:if test="${address != null}">
						<div class="col-md-12 sizetable">
							<table class="table table-striped table-bordered table-hover"
								id="dataTables-example">
								<tr>
									<th>STREET</th>
									<th>CITY</th>
									<th>STATE</th>
									<th>COUNTRY</th>
									<th>PIN CODE</th>
								</tr>
								<c:set value="${address}" var="address" />
								<tr>
									<td><c:out value="${address.getStreet()}" /></td>
									<td><c:out value="${address.getCity()}" /></td>
									<td><c:out value="${address.getState()}" /></td>
									<td><c:out value="${address.getCountry()}" /></td>
									<td><c:out value="${address.getPincode()}" /></td>
								</tr>
							</table>
						</div>
					</c:if>
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