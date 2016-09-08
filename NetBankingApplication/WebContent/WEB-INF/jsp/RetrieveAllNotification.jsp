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
	            <th align="center" height="30" width="100">DEBIT ACCOUNT NUMBER</th>
	            <th align="center" height="30" width="100">CRIDIT ACCOUNT NUMBER</th>
	            <th align="center" height="30" width="100">AMOUNT</th>
	            <th align="center" height="30" width="100">DATE & TIME</th>
	            <th colspan="2" align="center" height="30" width="100" >VIEW CUSTOMER DETAIL</th>
	            <th colspan="2" align="center" height="30" width="100" >TRANSACTION ACTION</th>
                </tr> 
                <% int sno =1; %>
                <c:forEach items="${notifications}" var="notifications">
                    <tr>
                        <td>
                            <c:out value="<%= sno %>"/>
                        </td>
                        <td>
                            <c:out value="${notifications.getId()}"/>
                        </td>
                        <td>
                            <c:set value="${notifications.getDebitAccount()}" var="accountDebit"/>
                            <c:out value="${accountDebit.accountNumber}"/>
                        </td>
                        <td>
                            <c:set value="${notifications.getCriditAccount()}" var="accountCridit"/>
                            <c:out value="${accountCridit.accountNumber}" />
                        </td>
                        <td>
                            <c:out value="${notifications.getAmount()}"/>
                        </td>
                        <td>
                            <c:out value="${notifications.getDate()}"/>
                        </td>
                        <td align="center" height="30" width="100">
                            <a href="viewCustomerAccount?accountNumber=<c:out value="${accountDebit.accountNumber}"/>" style="color:blue">VIEW DEBIT ACCOUNT</a>
                        </td>
                        <td align="center" height="30" width="100">
                            <a href="viewCustomerAccount?accountNumber=<c:out value="${accountCridit.accountNumber}"/>" style="color:blue">VIEW CRIDIT ACCOUNT</a>
                        </td> 
                        <td align="center" height="30" width="100">
                            <a href="transactionsuccess?id=<c:out value="${notifications.getId()}"/>&criditAccountNumber=<c:out value="${accountCridit.accountNumber}"/>&amount=<c:out value="${notifications.getAmount()}"/>" style="color:blue">PERMISSION</a>
                        </td>
                        <td align="center" height="30" width="100">
                            <a href="transactionCancel?id=<c:out value="${notifications.getId()}"/>&debitAccountNumber=<c:out value="${accountDebit.accountNumber}"/>&amount=<c:out value="${notifications.getAmount()}"/>" style="color:blue">IGNORE</a>
                        </td>   
                    </tr>                   
                <%    
                    sno++;   
                %> 
                </c:forEach>
            </table>
            <br/><br/>
            <b>Go to main page </b><a href="TransactionIndex" style="font-sise:18px"> click here</a>
	        <br/><br/>
	        <b>Do you want to insert a new Branch</b><a href="addTransaction" style="color:blue"> Insert here</a>  
	        <br/><br/>
            <c:if test="${message != null}">
                <script type="text/javascript">
                    alert('CLICK OK THE PAGE WILL BE REFRESHED...' + "<c:out value='${message}'/>" );
                    windows.location.reload();
                </script>
            </c:if>
        </center>
    </body>
</html>
