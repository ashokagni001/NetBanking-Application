 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<html>
    <head>
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body bgcolor="#E9967A">
        <center>
            <c:if test="${branches != null}">
            <h1>Fetching Data From A Project Management System</h1> 
            <table cellpadding="0" cellspacing="2" bordercolor=#125610 border="3">
                <tr>
                    <th align="center" height="30" width="100">S.NO</th>
	            <th align="center" height="30" width="100">IFSC CODE</th>
	            <th align="center" height="30" width="100">EMAIL</th>
	            <th colspan="1" align="center" height="30" width="100">ADDRESS</th>
	            <th colspan="1" align="center" height="30" width="100" >ADDRESS</th>
	            <th colspan="1" align="center" height="30" width="100" >ACTION</th>
                </tr> 
                <% int sno =1; %>
               <c:forEach items="${branches}" var="branches" >
                    <tr>
                        <td><c:out value="<%= sno %>" /></td>
                        <td><c:out value="${branches.getIFSCode()}" /></td>
                        <td><c:out value="${branches.getEmailId()}" /></td>
                        <c:choose>
                        <c:when test="${null == branches.getAddress()}">
                             <td><c:out value="${'No Address Allocated'}"/></td>
                        </c:when>
                        <c:otherwise>
                            <td><c:set value="${branches.getAddress()}" var="address" /></td>
                            <td align="center" height="30" width="100"><a href="viewBranchAddress?addressId=<c:out value="${address.addressId}"/>" style="color:blue">VIEW</a></td>  
                        </c:otherwise>
                    </c:choose>
                    <td align="center" height="30" width="100"><a href="deleteBranchById?ifsc=<c:out value="${branches.getIFSCode()}"/>" style="color:blue">Delete</a></td>  
                </tr>                   
                    </tr>                   
                <%    
                    sno++;   
                %> 
                </c:forEach>
            </table>
            </c:if>
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
