<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
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
        <h1>VIEW BRANCH BY ID</h1>
        <center> 
            <h2>Insert Id</h2>
            <form action="getBranchById" method="get">
            <table>
                <tr>
                    <td>
                        <input type="text" name="ifsc" placeholder="IFSC" required>
                    </td>
                </tr>
            </table>
            </br></br>
            <input type="submit" name="view" value="VIEW">
            </form>
            <c:if test="${message != null}">
            <script type="text/javascript">
                alert('CLICK OK THE PAGE WILL BE REFRESHED...' + "<c:out value='${message}'/>");
                windows.location.reload();
            </script>
        </c:if>
            <b>Go to main page </b><a href="BranchIndex" style="font-sise:18px"> click here</a>
    </center>
</html>