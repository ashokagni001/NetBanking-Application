<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<html>
    <head>
        <link rel="stylesheet" href="resource/css/bootstrap.css">
        <script src="resource/js/bootstrap.js"></script>
        <script src="resource/js/bootstrap1.js"></script>
        <style type="text/css">
        table,th,td {
        border: 1px solid #000;
        }
        .well {
         background-color:  #8ddfe1 ;
        }
        .actionform {
        float: right;
        }
        .index {
        float: left;
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
            <table class = "table table-striped">
                <tr>
	            <th>IFSC CODE</th>
	            <th>EMAIL ID</th>
	            <th>ADDRESS ID</th>
	            <th>ADDRESS</th>
	            <th>DALETE</th>
                </tr>
                <c:set value="${branch}" var="branch"/>
                <tr>
                    <td><c:out value="${branch.getIFSCode()}" /></td><br/><br/>
                    <td><c:out value="${branch.getEmailId()}" /></td><br/><br/>
                   <c:choose>
                        <c:when test="${null == branch.getAddress()}">
                             <td><c:out value="${'No Address Allocated'}"/></td><br/><br/>
                        </c:when>
                        <c:otherwise>
                            <td><c:set value="${branch.getAddress()}" var="address" /></td>
                            <td><a href="viewBranchAddress?addressId=<c:out value="${address.addressId}"/>" style="color:blue">VIEW</a></td><br/><br/>  
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
                    <th>S.NO</th>
	            <th>IFSC CODE</th>
	            <th>EMAIL</th>
	            <th>ADDRESS</th>
	            <th>ACTION</th>
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