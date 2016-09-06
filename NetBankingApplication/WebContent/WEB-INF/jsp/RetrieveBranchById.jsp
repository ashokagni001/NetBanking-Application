<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<html>
    <head>
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body bgcolor="#99a38f">
        <center>
            <c:if test="${branch != null}">
            <h1>Fetching Data From A Branch Database</h1> 
            <table cellpadding="0" cellspacing="2" bordercolor=#125610 border="3">
                <tr>
	            <th align="center" height="30" width="100">PROJECT ID</th>
	            <th align="center" height="30" width="100">PROJECT NAME</th>
	            <th align="center" height="30" width="100">CLIENT NAME</th>
                </tr>
                <c:set value="${branch}" var="branch"/>
                <tr>
                    <td><c:out value="${branch.getIFSCode()}" /></td>
                    <td><c:out value="${branch.getEmailId()}" /></td>
                    <td><c:out value="${branch.getAddress()}" /></td>
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
        <b>Go to main page </b><a href="BranchIndex" style="font-sise:18px"> click here</a></br></br>
        <br/><a href="logoutController" style="width:300px;"> LOGOUT</a><br/><br/>
    </body>
</html>
