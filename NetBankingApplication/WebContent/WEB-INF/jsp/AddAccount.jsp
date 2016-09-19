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
    <link rel="stylesheet" href="css/bootstrap.css">
    <script src="js/bootstrap.js"></script>
    <style type="text/css">
        .well {
            background-color: #8ddfe1;
        }
        .pos {
            position: absolute;
            right: 100px;
            top: 112px;
            height: 70px;
            width: 50px;
        }
    </style>
</head>

<body>
    <div class="well">
        <font size="20"> <marquee behavior="alternate">I2I
                NETBANKING</marquee>
        </font>
    </div>
    <h2>INSERT ACCOUNT</h2>
    <form method="post" action="addAccount">
        <div class="container">
            <form class="form-horizontal">
                <div class="form-group">
                    <label class="control-label col-sm-2" for="accountNumber">accountNumber :
                    </label>
                    <div class="col-sm-10">
                        <input type="number" name="accountNumber" placeholder="Enter accountNumber" required="required" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="ifscode">credit ifscode :</label>
                    <div class="col-sm-10">
                        <input type="text" name="ifscode" placeholder="Enter credit ifscode" required="required" />
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="balance">Amount:</label>
                        <div class="col-sm-10">
                            <input type="balance" name="balance" placeholder="Enter Amount" required="required" />
                            </br>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="amount" required="required">Account Type:
                        </label>
                        <div class="col-sm-10">
                            <select name="accounttype">
                                <option value="Current">Current</option>
                                <option value="Saving">Saving</option>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <input type="hidden" />
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <br />
                            <input type="submit" name="addaccount" value="ADD" />
                        </div>
                    </div>
            </form>
            </div>
    </form>
    <a href="BranchIndex">Go to main page</a>
    <div class="pos">
        <a class="btn btn-danger" href="logoutController"> LOGOUT</a>
    </div>
    </div>
    <c:if test="${message != null}">
        <script type="text/javascript">
            alert("<c:out value='${message}'/>");
            windows.location.reload();
        </script>
    </c:if>
</body>
</html>