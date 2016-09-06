<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
    <head>
    <h2>NET BANKING APPLICATION</h2>
    <h4>Registeration</h4>
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
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registeration</title>
</head>
<body>
    <center>
          <form:form method="post" action="register" modelAttribute="User"><br/><br/>
                <form:input path="name" type="text" placeholder="Enter the name" /><br/><br/>
                <form:input path="dob" type="text" placeholder="Enter the dob" /><br/><br/>
                <form:input path="gender" type="text" placeholder="Enter the gender" /><br/><br/>
                <form:input path="mobileNumber" type="text" placeholder="Enter the mobile_number" /><br/><br/>
                <form:input path="accountNumber" type="text" placeholder="Enter the account_number" /><br/><br/>
                <form:input path="email" type="text" placeholder="Enter the email" /><br/><br/>
                <br/>
                <input type="submit" path="submit" value="ADD" />
            </form:form>
     </center>
</body>
</html>
