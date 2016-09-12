<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<c:if test="${sessionScope['id']== null}">
    <c:redirect url="login"/>
</c:if>
<c:if test="${sessionScope['role']!= 'user'}">
    <c:redirect url="approverHomePage"/>
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
        <center>
            <table class = " table table-bordered">
                <tr>
                <th align="center" height="30" width="100">S.NO</th>
	            <th align="center" height="30" width="100">DEBIT ACCOUNT NUMBER</th>
	            <th align="center" height="30" width="100">CRIDIT ACCOUNT NUMBER</th>
	            <th align="center" height="30" width="100">AMOUNT</th>
	            <th align="center" height="30" width="100">DATE & TIME</th>
	            <th align="center" height="30" width="100">TRANSACTION STATUS</th>
                </tr> 
                <% int sno =1; %>
                <c:forEach items="${miniStatement}" var="miniStatement">
                    <tr>
                        <td>
                            <c:out value="<%= sno %>"/>
                        </td>
                        <td>
                            <c:set value="${miniStatement.getDebitAccount()}" var="accountDebit"/>
                            <c:out value="${accountDebit.accountNumber}"/>
                        </td>
                        <td>
                            <c:set value="${miniStatement.getCriditAccount()}" var="accountCridit"/>
                            <c:out value="${accountCridit.accountNumber}" />
                        </td>
                        <td>
                            <c:out value="${miniStatement.getAmount()}"/>
                        </td>
                        <td>
                            <c:out value="${miniStatement.getDate()}"/>
                        </td>
                        <td>
                            <c:out value="${miniStatement.getStatus()}"/>
                        </td>
                    </tr>                   
                <%    
                    sno++;   
                %> 
                </c:forEach>
            </table>
            <br/><br/>
            <c:if test="${sessionScope['role']== 'approver'}">
            <b>Go to main page </b><a href="approverHomePage"> click here</a>
             </c:if>
             <c:if test="${sessionScope['role']== 'user'}">
            <b>Go to main page </b><a href="userHomePage?customerId= <c:out value="${sessionScope['id']}"/>">click here</a> <br />
             </c:if>
	        <br/>
	        <br/><a href="logoutController" style="width:300px;"> LOGOUT</a>
            <c:if test="${message != null}">
                <script type="text/javascript">
                    alert('CLICK OK THE PAGE WILL BE REFRESHED...' + "<c:out value='${message}'/>" );
                    windows.location.reload();
                </script>
            </c:if>
        </center>
    </body>
</html>
