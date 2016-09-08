<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<html>
    <head>
    <style type="text/css">
    input[type=submit] {
             width: 100%;
             background-color: #4CAF50;
             color: white;
             padding: 14px 20px;
             margin: 8px 0;
             border: none;
             border-radius: 100px;
             cursor: pointer;
             outline:none;
         }
         input[type=text] {
             width: 90%;
             padding: 12px 20px;
             margin: 8px 0;
             display: inline-block;
             border: 1px solid #ccc;
             border-radius: 100px;
             box-sizing: border-box;
             outline:none;
         }
    </style>
    </head>
    <body bgcolor="#99a3ff">
        <font size="20"><marquee behavior="alternate">NET BANKING</marquee></font>
        <h1>VIEW BRANCH BY ID</h1>
        <center> 
            <c:if test="${message != null}">
            <script type="text/javascript">
                alert('CLICK OK THE PAGE WILL BE REFRESHED...' + "<c:out value='${message}'/>");
                windows.location.reload();
            </script>
        </c:if>
         <c:if test="${branch != null}">
            <h2>Fetching Data From A Branch Database</h2> 
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
            <c:if test="${branches != null}">
            <h2>Fetching Data From A Project Management System</h2> 
            <table cellpadding="0" cellspacing="2" bordercolor=#125610 border="3">
                <tr>
                    <th align="center" height="30" width="100">S.NO</th>
	            <th align="center" height="30" width="100">IFSC CODE</th>
	            <th align="center" height="30" width="100">EMAIL</th>
	            <th colspan="1" align="center" height="30" width="100">ADDRESS</th>
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
                            <c:set value="${branches.getAddress()}" var="address" />
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
            <h3>Insert Id</h3>
            <form action="getBranch" method="get">
            <table>
                <tr>
                    <td><input type="text" name="ifsc" placeholder="IFSC" required></td>
                    <td><input type="submit" name="view" value="view"></td>
                </tr>
            </table>
            </br></br>
            </form>
            <b>Go to main page </b><a href="BranchIndex" style="font-sise:18px"> click here</a>
    </center>
</html>