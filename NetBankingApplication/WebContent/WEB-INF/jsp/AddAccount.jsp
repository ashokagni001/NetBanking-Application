<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<html>
    <head>
        <h2>NET BANKING APPLICATION</h2>
        <h4>INSERT Account Detail</h4>
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
                width: 20%;
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
        <center>
            <c:if test="${message != null}">
                <script type="text/javascript">
                    alert('CLICK OK THE PAGE WILL BE REFRESHED...' + "<c:out value='${message}'/>");
                    windows.location.reload();
                </script>
            </c:if>
            <form method="post" action="addAccount">
                <input name="accountNumber" type="text" placeholder="Enter the accountNumber" /><br/>
                <input name="balance" type="text" placeholder="Enter the account balance" /><br/>
                <b>Account Type</b>>
                <select name="accounttype">
                    <option value="Current">Current</option>
                    <option value="Saving">Saving</option>
                </select><br/>
                <input name="ifscode" type="text" placeholder="Enter the ifscode" /><br/>
                <input type="submit" name="addaccount" value="ADD" />
            </form>
            <b>Go to main page </b><a href="BranchIndex" style="font-sise:18px"> click here</a></br></br>
        </center>
    </body>
</html>