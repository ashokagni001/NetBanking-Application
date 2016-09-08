 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<html>
    <head>
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body bgcolor="#E9967A">
        <center>
            <h1>Fetching Data From A Customer Management System</h1> 
            <table cellpadding="0" cellspacing="2" bordercolor=#125610 border="3">
                <tr>
                    <th align="center" height="30" width="100">S.NO</th>
	            <th align="center" height="30" width="100">CUSTOMER ID</th>
	            <th align="center" height="30" width="100">NAME</th>
	            <th align="center" height="30" width="100">AGE</th>
	            <th align="center" height="30" width="100">DOB</th>
	            <th align="center" height="30" width="100">GENDER</th>
	            <th align="center" height="30" width="100">MOB.NUMBER</th>
	            <th align="center" height="30" width="100">EMAIL</th>
	            <th align="center" height="30" width="100">PASSWORD</th>
	            <th align="center" height="30" width="100">ACCOUNT NUMBER</th>
	            <th align="center" height="30" width="100">STATUS</th>
	            <th align="center" height="30" width="100">ADDRESS ID</th>
	            <th colspan="2" align="center" height="30" width="100" >ADDRESS</th>
                </tr> 
                <% int sno =1; %>
               <c:forEach items="${customers}" var="customers" >
                    <tr>
                        <td><c:out value="<%= sno %>" /></td>
                        <td><c:out value="${customers.getCustomerId()}" /></td>
                        <td><c:out value="${customers.getName()}" /></td>
                        <td><c:out value="${customers.getAge()}" /></td>
                        <td><c:out value="${customers.getDob()}" /></td>
                        <td><c:out value="${customers.getGender()}" /></td>
                        <td><c:out value="${customers.getMobileNumber()}" /></td>
                        <td><c:out value="${customers.getEmail()}" /></td>
                        <td><c:out value="${customers.getPassWord()}" /></td>
                        <td><c:out value="${customers.getAccountNumber()}" /></td>
                        <td><c:out value="${customers.getStatus()}" /></td>
                        <c:choose>
                        <c:when test="${null == customers.getAddress()}">
                             <td><c:out value="${'No Address Allocated'}"/></td>
                        </c:when>
                        <c:otherwise>
                            <td><c:set value="${customers.getAddress()}" var="address"/>
                            <c:out value="${address.addressId}" /></td>
                        </c:otherwise>
                    </c:choose>
                    <td align="center" height="30" width="100"><a href="viewCustomerAddress?addressId=<c:out value="${address.addressId}"/>" style="color:blue">VIEW</a></td>  
                    </tr>                   
                <%    
                    sno++;   
                %> 
                </c:forEach>
            </table>
            <br/><br/>
            <b>Go to main page </b><a href="CustomerIndex" style="font-sise:18px"> click here</a>
	    <br/><br/>
	        <b>Do you want to insert a new Customer</b><a href="CustomerRegistration" style="color:blue"> Insert here</a>  
	    <br/><br/>
         <c:if test="${message != null}">
                <script type="text/javascript">
                    alert('CLICK OK THE PAGE WILL BE REFRESHED...' + "<c:out value='${message}'/>" );
                    windows.location.reload();
                </script>
            </c:if>
    </body>
</html>
