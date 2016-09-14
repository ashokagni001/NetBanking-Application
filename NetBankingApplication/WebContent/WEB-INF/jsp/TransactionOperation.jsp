<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${sessionScope['id']== null}">
    <c:redirect url="login"/>
</c:if>
<c:if test="${sessionScope['role']!= 'approver'}">
    <c:redirect url="userHomePage"/>
</c:if>
<html>
    <head>
        <link rel="stylesheet" href="css/bootstrap.css">
        <script src="js/bootstrap.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <style type="text/css">
            .well {
                background-color:  #8ddfe1 ;
            }
        </style>
    </head>
    <body>
        <div class = "well">
            <font size="20"><marquee behavior="alternate">NET BANKING</marquee></font>
        </div>
        <h2>INSERT TRANSACTION DETAIL</h2>
    <center>
          <form method="post" action="insertTransaction">
                <b>Debit Account Number</b>
                <input name="debitAccountNumber" placeholder="debitAccountNumber" />
                </br>
                <b>Credit Account Number </b>
                <input name="creditAccountNumber" placeholder="creditAccountNumber" />
                </br>
                <b>Credit Account IFSC number </b>
                <input name="ifscode" placeholder="ifscode" />
                </br>
                <b>Enter your Amount </b>
                <input name="amount" placeholder="Amount" />
                </br>
                <input type="submit" name="proceed" value="insertTransaction" />
            </form>
     <a href="BranchIndex">Go to main page</a></br></br>
     <br/><a href="logoutController"> LOGOUT</a>
     </center>
</body>
</html>