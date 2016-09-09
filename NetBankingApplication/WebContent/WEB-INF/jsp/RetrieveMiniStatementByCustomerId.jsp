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
            <table cellpadding="0" cellspacing="2" bordercolor=#125610 border="3">
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
