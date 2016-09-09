<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<html>
    <head>
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body bgcolor="#99a38f">
        <center>
            <c:if test="${role != null}">
            <h1>Fetching Data From A Project Database</h1> 
            <table cellpadding="0" cellspacing="2" bordercolor=#125610 border="3">
                <tr>
	            <th align="center" height="30" width="100">ROLE Id</th>
	            <th align="center" height="30" width="100">ROLE NAME</th>
                </tr>
                <c:set value="${branch}" var="branch"/>
                <tr>
                    <td><c:out value="${branch.getRoleId()}" /></td>
                    <td><c:out value="${branch.getRoleName()}" /></td>
                </tr>                   
            </table>
            </c:if>
	    <br/><br/>   
         <c:if test="${message != null}">
                <script type="text/javascript">
                    alert('CLICK OK THE PAGE WILL BE REFRESHED...' + "<c:out value='${message}'/>" );
                    windows.location.reload();
                </script>
            </c:if>
        </center>
        <br/><a href="logoutController" style="width:300px;"> LOGOUT</a><br/><br/>
    </body>
</html>
