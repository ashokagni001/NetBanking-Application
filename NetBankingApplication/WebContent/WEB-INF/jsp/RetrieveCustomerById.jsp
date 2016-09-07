<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body bgcolor="#E9967A">
        <center>
            <c:if test="${customer != null}">
            <h1>Fetching Data From A Customer Management System</h1> 
            <table cellpadding="0" cellspacing="2" bordercolor=#125610 border="3">
                <tr>
	                <th align="center" height="30" width="100">CUSTOMER ID</th>
	                <th align="center" height="30" width="100">NAME</th>
	                <th align="center" height="30" width="100">AGE</th>
	                <th align="center" height="30" width="100">DOB</th>
	                <th align="center" height="30" width="100">GENDER</th>
	                <th align="center" height="30" width="100">MOB.NUMBER</th>
	                <th align="center" height="30" width="100">EMAIL</th>
	                <th align="center" height="30" width="100">PASSWORD</th>
	                <th align="center" height="30" width="100">ACCOUNT NUMBER</th>
	                <th align="center" height="30" width="100">ADDRESS ID</th>
	                <th colspan="2" align="center" height="30" width="100" >ADDRESS</th>
                </tr> 
                <c:set value="${customer}" var="customer"/>
                    <tr>
                        <td>
                            <c:out value="${customer.getCustomerId()}"/>
                        </td>
                        <td>
                            <c:out value="${customer.getName()}"/>
                        </td>
                        <td>
                            <c:out value="${customer.getAge()}"/>
                        </td>
                        <td>
                            <c:out value="${customer.getDob()}"/>
                        </td>
                        <td>
                            <c:out value="${customer.getGender()}"/>
                        </td>
                        <td>
                            <c:out value="${customer.getMobileNumber()}"/>
                        </td>
                        <td>
                            <c:out value="${customer.getEmail()}"/>
                        </td>
                        <td>
                            <c:out value="${customer.getPassWord()}"/>
                        </td>
                        <td>
                            <c:out value="${customer.getAccountNumber()}"/>
                        </td>
                         <c:choose>
                        <c:when test="${null == customer.getAddress()}">
                             <td><c:out value="${'No Address Allocated'}"/></td>
                        </c:when>
                        <c:otherwise>
                            <td><c:set value="${customer.getAddress()}" var="address"/>
                            <c:out value="${address.addressId}" /></td>
                        </c:otherwise>
                    </c:choose>
                    <td align="center" height="30" width="100"><a href="viewCustomerAddress?addressId=<c:out value="${address.addressId}"/>" style="color:blue">VIEW</a></td>  
                    </tr> 
            </table>
            </c:if>
            <br/><br/>
            <b>Go to main page </b><a href="CustomerIndex" style="font-sise:18px"> click here</a>
	        <br/><br/>
	            <b>Do you want to insert a new Branch</b><a href="CustomerRegistration" style="color:blue"> Insert here</a>  
	        <br/><br/>
             <c:if test="${message != null}">
                 <script type="text/javascript">
                     alert('CLICK OK THE PAGE WILL BE REFRESHED...' + "<c:out value='${message}'/>" );
                     windows.location.reload();
                 </script>
            </c:if>
    </body>
</html>
