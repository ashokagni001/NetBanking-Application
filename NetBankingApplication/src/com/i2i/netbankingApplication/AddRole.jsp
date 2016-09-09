<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
    <style type="text/css">
    input[type=submit] {
             width: 90%;
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
    <body bgcolor="#99a38f">
        <font size="20"><marquee behavior="alternate">NET BANKING</marquee></font>
        <h1>ADD ROLE</h1>
        <center> 
            <h3> INSERT DETAIL</h3>
            <form action="insertRole" method="get">
            <table>
            <form: method="get" action="insertRole" />
                <tr>
                    <td>
                        <input type="text" name="customerId" placeholder="Enter the customerId" required>
                    </td>
                </tr>
                <tr>
                    <td>
                      SELECT ROLE : <select name="role" >
                           <c:forEach items="${roles}" var="role">
                                 <option value="${role.id}">${role.roleName}</option>
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
            
            
            <b>Go to main page </b><a href="roleIndex" style="font-sise:18px"> click here</a>
    </center>
</html>