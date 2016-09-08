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
                <th align="center" height="30" width="100">S.NO</th>
	            <th align="center" height="30" width="100">TRANSACTION ID</th>
	            <th align="center" height="30" width="100">DEBIT ACCOUNT ID</th>
	            <th align="center" height="30" width="100">CRIDIT ACCOUNT ID</th>
	            <th align="center" height="30" width="100">USER ID</th>
	            <th align="center" height="30" width="100">AMOUNT</th>
	            <th align="center" height="30" width="100">STATUS</th>
	            <th colspan="2" align="center" height="30" width="100" >VIEW CUSTOMER DETAIL</th>
	            <th colspan="2" align="center" height="30" width="100" >ACTION</th>
                </tr> 
                <% int sno =1; %>
                <c:forEach items="${transactions}" var="transactions">
                    <tr>
                        <td><c:out value="<%= sno %>" /></td>
                        <td><c:out value="${transactions.getId()}" /></td>
                        <td><c:set value="${transactions.getDebitAccount()}" var="account"/>
                        <c:out value="${account.accountNumber}" /></td>
                        <td><c:set value="${transactions.getCriditAccount()}" var="account"/>
                        <c:out value="${account.accountNumber}" /></td>
                        <td><c:set value="${transactions.getCustomer()}" var="customer"/>
                        <c:out value="${customer.customerId}" /></td>
                        <td><c:out value="${transactions.getAmount()}" /></td>
                        <td><c:out value="${transactions.getStatus()}" /></td>
                         <td align="center" height="30" width="100"><a href="viewCustomerAccount?accountNumber=<c:out value="${account.accountNumber}"/>" style="color:blue">VIEW DEBIT ACCOUNT</a></td>
                         <td align="center" height="30" width="100"><a href="viewCustomerAccount?accountNumber=<c:out value="${account.accountNumber}"/>" style="color:blue">VIEW CRIDIT ACCOUNT</a></td> 
                         <td align="center" height="30" width="100"><a href="transactionSuccess?id=<c:out value="${transactions.getId()}"/>" style="color:blue">PERMISSION</a></td>
                         <td align="center" height="30" width="100"><a href="viewCustomerAccount?accountNumber=<c:out value="${account.accountNumber}"/>" style="color:blue">IGNORE</a></td>   
                  </tr>                   
                <%    
                    sno++;   
                %> 
                </c:forEach>
            </table>
            <br/><br/>
            <b>Go to main page </b><a href="BranchIndex" style="font-sise:18px"> click here</a>
	    <br/><br/>
	        <b>Do you want to insert a new Branch</b><a href="addBranch" style="color:blue"> Insert here</a>  
	    <br/><br/>
         <c:if test="${message != null}">
                <script type="text/javascript">
                    alert('CLICK OK THE PAGE WILL BE REFRESHED...' + "<c:out value='${message}'/>" );
                    windows.location.reload();
                </script>
            </c:if>
    </body>
</html>
