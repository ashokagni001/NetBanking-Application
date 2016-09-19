
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
    <c:if test="${sessionScope['role']!= 'approver'}">
        <div class="well">
            <font size="20"><marquee behavior="alternate">I2I NETBANKING</marquee></font>
        </div>
        <br />
        <h3> Welcome  ${sessionScope['name']} </h3><br />
        <table class=" table table-bordered">
            <tr>
                <th>ACCOUNT NUMBER</th>
                <th>CUSTOMER ID</th>
                <th>BRANCH ID</th>
                <th>BALANCE</th>
                <th>ACCOUNT TYPE</th>
            </tr>
            <c:set value="${accountDetail}" var="accountDetail" />
            <tr>
                <td align="center"><c:out
                        value="${accountDetail.getAccountNumber()}" /></td>
                <c:set value="${accountDetail.getCustomer()}" var="customer" />
                <td align="center"><c:out value="${customer.customerId}" /></td>
                <c:set value="${accountDetail.getBranch()}" var="branch" />
                <td align="center"><c:out value="${branch.IFSCode}" /></td>
                <td align="center"><c:out value="${accountDetail.getBalance()}" /></td>
                <td align="center"><c:out
                        value="${accountDetail.getAccountType()}" /></td>
            </tr>
        </table>
        <a href="approverIndexPage">Go to main page</a>
        <div class="pos">
            <a class="btn btn-danger" href="logoutController"> LOGOUT</a>
        </div>
        <c:if test="${message != null}">
            <script type="text/javascript">
                alert("<c:out value='${message}'/>");
                windows.location.reload();
            </script>
        </c:if>
    </c:if>
    <c:if test="${sessionScope['role'] != 'user'}">
        <div class="well">
            <font size="20"><marquee behavior="alternate">NET
                    BANKING</marquee></font>
        </div>
        <br />
        <table class=" table table-bordered">
            <tr>
                <th>ACCOUNT NUMBER</th>
                <th>USER ID</th>
                <th>BRANCH ID</th>
                <th>BALANCE</th>
                <th>ACCOUNT TYPE</th>
            </tr>
            <c:set value="${accountDetail}" var="accountDetail" />
            <tr>
                <td align="center"><c:out
                        value="${accountDetail.getAccountNumber()}" /></td>
                <c:set value="${accountDetail.getCustomer()}" var="customer" />
                <td align="center"><a href="getCustomer?customerId=<c:out value="${customer.customerId}"/>"
                    style="color: blue"><c:out value="${customer.customerId}" /></td>
                <c:set value="${accountDetail.getBranch()}" var="branch" />
                <td align="center"><c:out value="${branch.IFSCode}" /></td>
                <td align="center"><c:out value="${accountDetail.getBalance()}" /></td>
                <td align="center"><c:out
                        value="${accountDetail.getAccountType()}" /></td>
            </tr>
        </table>
    <a href="approverIndexPage">Go to main page</a>
    <div class="pos">
        <a class="btn btn-danger" href="logoutController"> LOGOUT</a>
    </div>
    </c:if>
    <c:if test="${message != null}">
        <script type="text/javascript">
            alert("<c:out value='${message}'/>");
            windows.location.reload();
        </script>
    </c:if>
</body>
</html>