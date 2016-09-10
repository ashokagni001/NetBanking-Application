<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<c:if test="${sessionScope['id']== null}">
    <c:redirect url="login"/>
</c:if>
<c:if test="${sessionScope['role']!= 'approver'}">
    <c:redirect url="userHomePage"/>
</c:if>
<html>
    <head>
        <link rel="stylesheet" href="css/bootstrap.css">
        <script src="js/bootstrap.js"></script>
        <script src="js/bootstrap.min.js"></script>
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
        <h2>INSERT BRANCH</h2>
    <center>
          <form:form method="post" action="insertBranch" modelAttribute="Branch">
                <form:input path="emailId" class="form-control" type="text" placeholder="EMAIL ID" />
                <br/>
                <input type="submit" path="addBranch" value="ADD" class="btn btn-success" />
            </form:form>
         <c:if test="${message != null}">
            <script type="text/javascript">
                alert('CLICK OK THE PAGE WILL BE REFRESHED...' + "<c:out value='${message}'/>");
                windows.location.reload();
            </script>
        </c:if>
     <b>Go to main page </b><a href="BranchIndex" style="font-sise:18px"> click here</a></br></br>
     <br/><a href="logoutController" style="width:300px;"> LOGOUT</a>
     </center>
</body>
</html>