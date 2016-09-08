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
	            <th align="center" height="30" width="100">IFSC CODE</th>
	            <th align="center" height="30" width="100">EMAIL ID</th>
	            <th align="center" height="30" width="100">ADDRESS ID</th>
	            <th align="center" height="30" width="100">ADDRESS</th>
	            <th colspan="2" align="center" height="30" width="100" >DALETE</th>
                </tr>
                <c:set value="${branch}" var="branch"/>
                <tr>
                    <td><c:out value="${branch.getIFSCode()}" /></td>
                    <td><c:out value="${branch.getEmailId()}" /></td>
                   <c:choose>
                        <c:when test="${null == branch.getAddress()}">
                             <td><c:out value="${'No Address Allocated'}"/></td>
                        </c:when>
                        <c:otherwise>
                            <td><c:set value="${branch.getAddress()}" var="address" /></td>
                            <td align="center" height="30" width="100"><a href="viewBranchAddress?addressId=<c:out value="${address.addressId}"/>" style="color:blue">VIEW</a></td>  
                        </c:otherwise>
                    </c:choose>
                    <td align="center" height="30" width="100"><a href="deleteBranchById?ifsc=<c:out value="${branches.getIFSCode()}"/>" style="color:blue">Delete</a></td>  
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
