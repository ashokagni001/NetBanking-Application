<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
        .actionform {
        float: right;
        }
        .index {
        float: left;
        }
        .head {
         background-color:  #f06b6f ;
        }
        </style>
    </head>
        <body>
            <div class = "well">
                <font size="20"><marquee behavior="alternate">NET BANKING</marquee></font>
            </div>
        
            <h1>VIEW BRANCH BY ID</h1>
        
        
        <center> 
        <div class = "actionform">
            <h2>Choose Branch Id</h3>
            <form action="getBranch" method="get">
                <tr>
                    <td><input type="text" name="ifsc" placeholder="IFSC" required></td>
                    <td><input type="submit" name="view" value="view"></td>
                </tr>
                </br></br>
                </form>
            </div>
            <c:if test="${message != null}">
            <script type="text/javascript">
                alert('CLICK OK THE PAGE WILL BE REFRESHED...' + "<c:out value='${message}'/>");
                windows.location.reload();
            </script>
        </c:if>
         <c:if test="${branch != null}">
            <div class = "head">
            <h3>Fetching Data From A Branch Database</h3> 
            <table class = " table table-bordered">
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
                            <td><a href="viewBranchAddress?addressId=<c:out value="${address.addressId}"/>" style="color:blue">VIEW</a></td>  
                        </c:otherwise>
                    </c:choose>
                    <td><a href="deleteBranchById?ifsc=<c:out value="${branches.getIFSCode()}"/>" style="color:blue">Delete</a></td>  
                </tr>                               
            </table>
            </div>
            </c:if>
            <c:if test="${branches != null}">
            <div class = "head">
            <h3>Fetching Data From A Project Management System</h3> 
            <table class = " table table-bordered">
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
                            <td><a href="viewBranchAddress?addressId=<c:out value="${address.addressId}"/>" style="color:blue">VIEW</a></td>  
                        </c:otherwise>
                    </c:choose>
                    <td><a href="deleteBranchById?ifsc=<c:out value="${branches.getIFSCode()}"/>" style="color:blue">Delete</a></td>  
                </tr>                   
                    </tr>                   
                <%    
                    sno++;   
                %> 
                </c:forEach>
            </table>
            </div>
            </c:if>
            <div class ="index">
                <b>Go to main page </b><a href="BranchIndex" style="font-sise:18px"> click here</a>
            </div>
    </center>
</html>