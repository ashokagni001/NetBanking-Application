<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<html>
    <head>
        <link rel="stylesheet" href="resource/css/bootstrap.css">
        <script src="resource/js/bootstrap.js"></script>
        <script src="resource/js/bootstrap1.js"></script>
        <style type="text/css">
            .well {
                background-color:  #8ddfe1 ;
            }
        </style>
    </head>
    <body">
        <div class = "well">
            <font size="20"><marquee behavior="alternate">NET BANKING</marquee></font>
        </div>
        <h2>INSERT TRANSACTION</h2>
    <center>
          <form method="post" action="insertTransaction">
                <b>Debit Account Number</b>
                <input name="debitAccountNumber" placeholder="debitAccountNumber" />
                </br>
                <b>Cridit Account Number </b>
                <input name="criditAccountNumber" placeholder="criditAccountNumber" />
                </br>
                <b>Cridit Account IFSC number </b>
                <input name="ifscode" placeholder="ifscode" />
                </br>
                <b>Enter your Amount </b>
                <input name="amount" placeholder="Amount" />
                </br>
                <input type="submit" name="proceed" value="insertTransaction" />
            </form>
     <b>Go to main page </b><a href="BranchIndex" style="font-size:18px"> click here</a></br></br>
     </center>
</body>
</html>