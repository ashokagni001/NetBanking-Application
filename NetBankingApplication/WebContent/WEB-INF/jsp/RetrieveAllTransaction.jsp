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
                <th align="center" height="30" width="100">S.NO</th>
	            <th align="center" height="30" width="100">TRANSACTION ID</th>
	            <th align="center" height="30" width="100">DEBIT ACCOUNT ID</th>
	            <th align="center" height="30" width="100">CRIDIT ACCOUNT ID</th>
	            <th align="center" height="30" width="100">USER ID</th>
	            <th align="center" height="30" width="100">AMOUNT</th>
	            <th align="center" height="30" width="100">DATE & TIME</th>
	            <th align="center" height="30" width="100">TRANSACTION STATUS</th>
	            <th colspan="2" align="center" height="30" width="100" >VIEW CUSTOMER DETAIL</th>
                </tr> 
                <% int sno =1; %>
                <c:forEach items="${transactions}" var="transactions">
                    <tr>
                        <td>
                            <c:out value="<%= sno %>"/>
                        </td>
                        <td>
                            <c:out value="${transactions.getId()}"/>
                        </td>
                        <td>
                            <c:set value="${transactions.getDebitAccount()}" var="accountDebit"/>
                            <c:out value="${accountDebit.accountNumber}"/>
                        </td>
                        <td>
                            <c:set value="${transactions.getCriditAccount()}" var="accountCridit"/>
                            <c:out value="${accountCridit.accountNumber}" />
                        </td>
                        <td>
                            <c:set value="${transactions.getCustomer()}" var="customer"/>
                            <c:out value="${customer.customerId}"/>
                        </td>
                        <td>
                            <c:out value="${transactions.getAmount()}"/>
                        </td>
                        <td>
                            <c:out value="${transactions.getDate()}"/>
                        </td>
                        <td>
                            <c:out value="${transactions.getStatus()}"/>
                        </td>
                        <td align="center" height="30" width="100">
                            <a href="viewCustomerAccount?accountNumber=<c:out value="${accountDebit.accountNumber}"/>" style="color:blue">VIEW DEBIT ACCOUNT</a>
                        </td>
                        <td align="center" height="30" width="100">
                            <a href="viewCustomerAccount?accountNumber=<c:out value="${accountCridit.accountNumber}"/>" style="color:blue">VIEW CRIDIT ACCOUNT</a>
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
