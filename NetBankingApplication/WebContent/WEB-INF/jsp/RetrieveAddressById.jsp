<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${sessionScope['id']== null}">
	<c:redirect url="login" />
</c:if>
<html>
<head>
    <link rel="stylesheet" href="css/bootstrap.css">
    <script src="js/bootstrap.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <style type="text/css">
        .well {
            background-color: #8ddfe1;
        }
        .pos {
            position: absolute;
            right: 100px;
            top: 108px;
            height: 70px;
            width: 50px;
        }
    </style>
</head>
<body>
    <div class="well">
        <font size="20"><marquee behavior="alternate">I2I NETBANKING</marquee></font>
    </div>
    <center>
        <c:if test="${address != null}">
            <h1>Address</h1>
            <table class=" table table-bordered">
                <tr>
                    <th>STREET</th>
                    <th>CITY</th>
                    <th>STATE</th>
                    <th>COUNTRY</th>
                    <th>PIN CODE</th>
                </tr>
                <c:set value="${address}" var="address" />
                <tr>
                    <td align="center">
                        <c:out value="${address.getStreet()}" />
                    </td>
                    <td align="center">
                        <c:out value="${address.getCity()}" />
                    </td>
                    <td align="center">
                        <c:out value="${address.getState()}" />
                    </td>
                    <td align="center">
                        <c:out value="${address.getCountry()}" />
                    </td>
                    <td align="center">
                        <c:out value="${address.getPincode()}" />
                    </td>
                </tr>
            </table>
        </c:if>
        <br />
        <br />
        <c:if test="${message != null}">
            <script type="text/javascript">
                alert("<c:out value='${message}'/>");
                windows.location.reload();
            </script>
        </c:if>
    </center>
    <c:if test="${sessionScope['role']== 'approver'}">
        <br />
        <br />
        <a href="approverIndexPage" style="font-sise: 18px">Go to main
			page</a>
        <div class="pos">
            <a class="btn btn-danger" href="logoutController"> LOGOUT</a>
        </div>
    </c:if>
    <c:if test="${sessionScope['role']== 'user'}">
        <td><a href="getCustomer?customerId=<c:out value="${sessionScope['id']}"/>">Go
				to main page</a>
        </td>
    </c:if>
    <div class="pos">
        <a class="btn btn-danger" href="logoutController"> LOGOUT</a>
    </div>
</body>
</html>
