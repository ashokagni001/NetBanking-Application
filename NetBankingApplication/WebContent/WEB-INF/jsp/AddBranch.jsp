<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    <center>
          <form:form method="post" action="insertBranch" modelAttribute="Branch">
                <form:input path="emailId" placeholder="EMAIL ID" />
                <br/>
                <input type="submit" path="addBranch" value="ADD" />
            </form:form>
     </center>
</body>
</html>