 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<html>
    <head>
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body bgcolor="#E9967A">
        <center>
            <h1>NET BANKING</h1> 
            <table cellpadding="0" cellspacing="2" bordercolor=#125610 border="3">
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
                        <td align="center" height="30" width="100"><a href="GetCustomer?customerId=<c:out value="${customer.customerId}"/>" style="color:blue">VIEW USER DETAIL</a></td>  
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
