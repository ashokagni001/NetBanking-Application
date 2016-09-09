<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<html>
    <head>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <style type="text/css">
        body {
        background-color: #99a38f;
        }
        </style>
    </head>
    <body>
        <font size="20"><marquee behavior="alternate">NET BANKING APPLICATION</marquee></font>
        <h1>BANK MANAGEMENT</h1>
        <br/><br/>
        <center>
            <c:if test="${message != null}">
                <script type="text/javascript">
                    alert('CLICK OK THE PAGE WILL BE REFRESHED...' + "<c:out value='${message}'/>" );
                    windows.location.reload();
                </script>
            </c:if>
            <table>
                <tr> 
                    <td align="center" height="40" width="350">
                       <a class = " btn btn-link" href="addBranch"> ADD BRANCH</a>
                    </td>
                </tr>
                <tr>
                    <td align="center" height="40" width="350">
                        <a href="deleteBranch">DELETE BRANCH</a>
                    </td>
                    </tr>
                <tr>
                    <td align="center" height="40" width="350">
                        <a href="GetBranch">FETCH BRANCH</a>
                    </td>
                </tr>
                <tr>
                    <td align="center" height="40" width="350">
                        <a href="AddAccount">ADD ACCOUNT</a>
                    </td>
                </tr>
                <tr>
                    <td align="center" height="40" width="350">
                         <a href="ViewAccountByBranch">View  ACCOUNT By Branch</a>
                    </td>
                </tr>
            </table>
        </center>
        <b>Go to main page </b><a href="index.jsp" style="font-size:18px"> click here</a></br></br>
    </body>
</html>