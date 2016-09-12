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
        <center>
            <table class = " table table-bordered">
                <tr>
	            <th align="center" height="30" width="100">ACCOUNT NUMBER</th>
	            <th align="center" height="30" width="100">USER ID</th>
	            <th align="center" height="30" width="100">BRANCH ID</th>
	            <th align="center" height="30" width="100">BALANCE</th>
	            <th align="center" height="30" width="100">ACCOUNT TYPE</th>
	            <th colspan="1" align="center" height="30" width="100">VIEW CUSTOMER DETAIL</th>
                </tr> 
                <c:set value="${accountDetail}" var="accountDetail"/>
                    <tr>
                        <td><c:out value="${accountDetail.getAccountNumber()}" /></td>
                        <c:set value="${accountDetail.getCustomer()}" var="customer"/>
                        <td><c:out value="${customer.customerId}" /></td>
                        <c:set value="${accountDetail.getBranch()}" var="branch"/>
                        <td><c:out value="${branch.IFSCode}" /></td>
                        <td><c:out value="${accountDetail.getBalance()}" /></td>
                        <td><c:out value="${accountDetail.getAccountType()}" /></td>
                        <td align="center" height="30" width="100"><a href="getCustomer?customerId=<c:out value="${customer.customerId}"/>" style="color:blue">VIEW USER DETAIL</a></td>  
                  </tr>
            </table>
            <br/><br/>
            <b>Go to main page </b><a href="BranchIndex" style="font-sise:18px">click here</a>
	    <br/><br/>
	        <b>Do you want to insert a new Branch</b><a href="addBranch" style="color:blue">Insert here</a>  
	    <br/><br/>
         <c:if test="${message != null}">
                <script type="text/javascript">
                    alert('CLICK OK THE PAGE WILL BE REFRESHED...' + "<c:out value='${message}'/>" );
                    windows.location.reload();
                </script>
            </c:if>
    </body>
</html>
