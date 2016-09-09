<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
    <body">
        <div class = "well">
            <font size="20"><marquee behavior="alternate">NET BANKING</marquee></font>
        </div>
        <h1>ADD ROLE</h1>
        <center> 
            <h3> INSERT DETAIL</h3>
            <table>
            <form method="get" action="insertRole" />
                <tr>
                    <td>
                        <input type="text" name="customerId" placeholder="Enter the customerId" required>
                    </td>
                </tr>
                <tr>
                    <td>
                      SELECT ROLE : <select name="role" >
                           <c:forEach items="${roles}" var="role">
                                 <option value="${role.roleId}">${role.roleName}</option>
                           </c:forEach>
                       </select>
                    </td>
                </tr>         
                
                <tr>
                 <td>
                <input type="submit" path="add" value="SUBMIT">
                 </td>
                </tr>
            </table>
            </form>
            </br></br>
            <c:if test="${message != null}">
            <script type="text/javascript">
                alert("<c:out value='${message}'/>");
                windows.location.reload();
            </script>
            </c:if>
            <b>Go to main page </b><a href="BranchIndex" style="font-sise:18px"> click here</a></br></br>
    </center>
</html>