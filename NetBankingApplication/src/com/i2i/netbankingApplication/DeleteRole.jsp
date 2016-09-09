<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
    <style type="text/css">
    input[type=submit] {
             width: 20%;
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
        <h1>DELETE ROLE</h1>
        <center> 
            <h2>Insert Role Id</h2>
            <form action="deleteRoleById" method="get">
            <table>
                <tr>
                    <td>
                        <input type="text" name="Id" placeholder="Enter the role id" required>
                    </td>
                </tr>
            </table>
            </br></br>
            <c:if test="${message != null}">
            <script type="text/javascript">
                alert('CLICK OK THE PAGE WILL BE REFRESHED...' + "<c:out value='${message}'/>");
                windows.location.reload();
            </script>
        </c:if>
            <input type="submit" name="delete" value="DELETE">
            </form>
    </center>
</html>