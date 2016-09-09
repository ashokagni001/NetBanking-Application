<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <body>
        <div class = "well">
            <font size="20"><marquee behavior="alternate">NET BANKING</marquee></font>
        </div>
        <h1>VIEW ACCOUNT BY BRANCH </h1>
        <center> 
            <c:if test="${message != null}">
            <script type="text/javascript">
                alert('CLICK OK THE PAGE WILL BE REFRESHED...' + "<c:out value='${message}'/>");
                windows.location.reload();
            </script>
        </c:if>
         <c:if test="${accounts != null}">
            <h2>Fetching Data From A Project Management System</h2> 
            <table cellpadding="0" cellspacing="2" bordercolor=#125610 border="3">
                <tr>
                    <th align="center" height="30" width="100">S.NO</th>
	            <th align="center" height="30" width="100">AccountNumber</th>
	            <th align="center" height="30" width="100">Customer Id</th>
	            <th align="center" height="30" width="100">Balance</th>
	            <th align="center" height="30" width="100">AccountType</th>
                </tr> 
                <% int sno =1; %>
               <c:forEach items="${accounts}" var="accounts" >
                    <tr>
                        <td><c:out value="<%= sno %>" /></td>
                        <td><c:out value="${accounts.getAccountNumber()}" /></td>
                        <td><c:set value="${accounts.getCustomer()}" var="customer"/>
                        <c:out value="${customer.customerId}" /></td>
                        <td><c:out value="${accounts.getBalance()}" /></td>
                        <td><c:out value="${accounts.getAccountType()}" /></td>
                    </tr>                   
                <%    
                    sno++;   
                %> 
                </c:forEach>
            </table>
            </c:if>
            <h3>Insert Id</h3>
            <form action="getAccount" method="get">
            <table>
                <tr>
                    <td><input type="text" name="ifsc" placeholder="IFSC" required></td>
                    <td><input type="submit" name="view" value="view"></td>
                </tr>
            </table>
            </br></br>
            </form>
            <b>Go to main page </b><a href="BranchIndex" style="font-sise:18px"> click here</a>
    </center>
</html>